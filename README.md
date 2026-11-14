# ShogunAi demo: three agents, one mobile app

A small Kotlin Multiplatform app for Android and iOS for running several AI coding agents on the same project at once, with and without [ShogunAi](https://github.com/LuxionServer/ShogunAi).

## The problem it shows

Agents check a mobile change by installing the app on a device and looking at it. With one emulator and one simulator running, as on most laptops, three agents in three worktrees install the same app on the same devices: each install replaces the previous one, and an agent can end up looking at another agent's code without any error. If they start more devices on their own, nothing bounds how many, and when they finish, emulators, simulators and Gradle daemons stay behind eating memory.

With ShogunAi, the repository declares once what each worktree needs in `.shogunai.json`:

```json
{
  "link": ["/local.properties"],
  "providers": {
    "gradle": { "daemonMaxHeap": "3g", "kotlinDaemonMaxHeap": "2g", "idleTimeoutMinutes": 30 },
    "emulator": { "avd": "Medium_Phone_API_35" },
    "ios-simulator": { "device": "iPhone 17", "runtime": "iOS 26.5" },
    "xcode": { "compilationCache": true }
  }
}
```

Every worktree then links the main checkout's `local.properties`, gets its own emulator (`ANDROID_SERIAL`) and its own simulator (`IOS_SIMULATOR_UDID`) within what the machine can hold, waits in line when there's no room for another, builds with bounded Gradle and Kotlin daemons, and shares Xcode's compilation cache with the other worktrees. Removing the worktree stops what it started.

## Requirements

- macOS with Xcode and an iOS simulator runtime, and the Android SDK with an emulator image, as Android Studio installs them. The AVD, device and runtime above are preferences: ShogunAi uses them when the machine has them.
- [ShogunAi](https://github.com/LuxionServer/ShogunAi#installation) with the `shogun` command.
- [AXe](https://github.com/cameroncooke/AXe), which agents use to tap and type on the iOS simulator: `brew install cameroncooke/axe/axe`.
- A JDK to start Gradle, such as the one bundled with Android Studio; Gradle downloads the JDK 21 it builds with.
- An agent such as [Claude Code](https://claude.com/claude-code).

## Running it

```bash
git clone https://github.com/LuxionServer/shogunai-demo-mobile.git
cd shogunai-demo-mobile
echo "sdk.dir=$HOME/Library/Android/sdk" > local.properties
```

Add the folder as a project in the ShogunAi app. The hooks in `.claude/settings.json` make `claude --worktree` create its worktrees through ShogunAi, so start one session per task:

```bash
claude --worktree search "Do the task in tasks/search.md"
claude --worktree favorites "Do the task in tasks/favorites.md"
claude --worktree detail "Do the task in tasks/detail.md"
```

Each session starts its own emulator and simulator with `shogun emulator start` and `shogun simulator start`; `shogun pool status` shows who holds each device and who is waiting, and the ShogunAi app shows the same next to each agent. `shogun gradle` lists the Gradle daemons with their memory. When a task is merged, `shogun remove <task>` removes its worktree and stops its emulator, simulator and idle Gradle daemons.

Without Claude Code, create the worktrees with `shogun new <task>`, start the devices with `shogun emulator start` and `shogun simulator start`, and load the variables with `eval "$(shogun env)"` before following the steps in [AGENTS.md](./AGENTS.md).

## Without ShogunAi

The `without-shogunai` branch has no `.shogunai.json` and no hooks, and its `AGENTS.md` uses whichever device is running. Start one emulator and one simulator and run the same three sessions from it to see the agents step on each other.

## The app

A list of 20 books, in memory, shared between Android and iOS with Compose Multiplatform:

| Folder | What it has |
|---|---|
| `shared/` | The app's code and UI (`commonMain`) and its tests (`commonTest`) |
| `androidApp/` | The Android app hosting it |
| `iosApp/` | The Xcode project hosting it |

The tasks in `tasks/` add search, favorites and a detail screen.

## License

[MIT](./LICENSE)
