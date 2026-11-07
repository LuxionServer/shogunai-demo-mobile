# Book detail

Tapping a book in the list opens a detail screen with its title, author and year of publication, and a back arrow in the top bar that returns to the list.

Done when:

- `./gradlew :shared:testAndroidHostTest` passes with `commonTest` tests for opening a book, going back to the list and opening an unknown book, which stays on the list.
- The app is installed and running on Android and iOS, and `screenshots/detail-android.png` and `screenshots/detail-ios.png` show the detail of Domain-Driven Design.
