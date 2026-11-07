package app.luxion.bookshelf

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(books: List<Book>) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Bookshelf") }) },
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(books, key = { it.id }) { book ->
                BookRow(book)
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun BookRow(book: Book) {
    ListItem(
        headlineContent = { Text(book.title) },
        supportingContent = { Text(book.author) },
        trailingContent = { Text(book.publishedYear.toString()) },
    )
}
