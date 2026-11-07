# AGENTS.md

Bookshelf is a small Kotlin Multiplatform app for Android and iOS, with Compose Multiplatform and its books in memory, used to demonstrate [ShogunAi](https://github.com/LuxionServer/ShogunAi): several agents working on it in parallel, each in its own worktree with its own Android emulator and iOS simulator.

## Working on a task

- Tasks are in `tasks/`; each one says when it's done.
- The shared code, UI included, is in `shared/src/commonMain`; its tests are in `shared/src/commonTest`. `androidApp/` and `iosApp/` only host it.
- Run the tests with `./gradlew :shared:testAndroidHostTest`.
- Check every change on both platforms, on the devices named by `ANDROID_SERIAL` and `IOS_SIMULATOR_UDID`. In a worktree created through ShogunAi, `shogun emulator start` and `shogun simulator start` start the worktree's own devices and print those variables; never use another device.
- Save one screenshot per platform in `screenshots/` (ignored by Git), named as the task says.

## Checking a change on Android

`installDebug` and `adb` go to the device in `ANDROID_SERIAL`.

```bash
./gradlew :androidApp:installDebug
adb shell am start -n app.luxion.bookshelf/.MainActivity
adb exec-out screencap -p > screenshots/<task>-android.png
```

## Checking a change on iOS

```bash
xcodebuild -project iosApp/iosApp.xcodeproj -scheme iosApp -configuration Debug -destination "id=$IOS_SIMULATOR_UDID" build
APP_DIR="$(xcodebuild -project iosApp/iosApp.xcodeproj -scheme iosApp -configuration Debug -destination "id=$IOS_SIMULATOR_UDID" -showBuildSettings | awk '$1 == "BUILT_PRODUCTS_DIR" { print $3 }')"
xcrun simctl install "$IOS_SIMULATOR_UDID" "$APP_DIR/Bookshelf.app"
xcrun simctl launch "$IOS_SIMULATOR_UDID" app.luxion.bookshelf
xcrun simctl io "$IOS_SIMULATOR_UDID" screenshot screenshots/<task>-ios.png
```

The Xcode build compiles the shared code through Gradle, so the first one takes a while.

## Rules

- No comments in code.
- Code, commits and docs in English.
- This repository is **public**: never commit `local.properties`, signing files, credentials, machine-specific paths or local tool settings.
- `develop` is the default branch; changes go through pull requests against it.
