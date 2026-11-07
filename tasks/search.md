# Search books

Add a search field at the top of the book list that keeps the books whose title or author contains the text, ignoring case. An empty field shows every book.

Done when:

- `./gradlew :shared:testAndroidHostTest` passes with `commonTest` tests for matches by title, by author, different case, no matches and an empty query.
- The app is installed and running on Android and iOS, and `screenshots/search-android.png` and `screenshots/search-ios.png` show the list filtered by `fowler` with both Martin Fowler books.
