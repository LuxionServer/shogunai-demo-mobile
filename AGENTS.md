# AGENTS.md

Bookshelf is a small Kotlin Multiplatform app for Android and iOS, with Compose Multiplatform and its books in memory, used to demonstrate [ShogunAi](https://github.com/LuxionServer/ShogunAi): several agents working on it in parallel.

## Working on a task

- Tasks are in `tasks/`; each one says when it's done.
- The shared code, UI included, is in `shared/src/commonMain`; its tests are in `shared/src/commonTest`. `androidApp/` and `iosApp/` only host it.
- Run the tests with `./gradlew :shared:testAndroidHostTest`.
- Check every change on both platforms, on the Android emulator and the iOS simulator that are running.
- Save one screenshot per platform in `screenshots/` (ignored by Git), named as the task says.

## Checking a change on Android

```bash
./gradlew :androidApp:installDebug
adb shell am start -n app.luxion.bookshelf/.MainActivity
adb exec-out screencap -p > screenshots/<task>-android.png
```

## Checking a change on iOS

```bash
xcodebuild -project iosApp/iosApp.xcodeproj -scheme iosApp -configuration Debug -destination "generic/platform=iOS Simulator" build
APP_DIR="$(xcodebuild -project iosApp/iosApp.xcodeproj -scheme iosApp -configuration Debug -destination "generic/platform=iOS Simulator" -showBuildSettings | awk '$1 == "BUILT_PRODUCTS_DIR" { print $3 }')"
xcrun simctl install booted "$APP_DIR/Bookshelf.app"
xcrun simctl launch booted app.luxion.bookshelf
xcrun simctl io booted screenshot screenshots/<task>-ios.png
```

The Xcode build compiles the shared code through Gradle, so the first one takes a while.

## Rules

- No comments in code.
- Code, commits and docs in English.
- This repository is **public**: never commit `local.properties`, signing files, credentials, machine-specific paths or local tool settings.
- `develop` is the default branch; changes go through pull requests against it.
