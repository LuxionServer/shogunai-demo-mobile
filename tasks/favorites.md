# Favorite books

Add a star to every book in the list that marks or unmarks it as a favorite, and a bottom navigation bar with two tabs, Books and Favorites. Favorites shows only the favorite books, in the same order as the list. Favorites live in memory.

Done when:

- `./gradlew :shared:testAndroidHostTest` passes with `commonTest` tests for marking a book, unmarking it and listing only the favorites in order.
- The app is installed and running on Android and iOS, and `screenshots/favorites-android.png` and `screenshots/favorites-ios.png` show the Favorites tab with Clean Code and Refactoring.
