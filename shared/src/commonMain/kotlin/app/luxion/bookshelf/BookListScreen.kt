package app.luxion.bookshelf

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    books: List<Book>,
    query: String,
    onQueryChange: (String) -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Bookshelf") }) },
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            OutlinedTextField(
                value = query,
                onValueChange = onQueryChange,
                placeholder = { Text("Search by title or author") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            )
            LazyColumn {
                items(books, key = { it.id }) { book ->
                    BookRow(book)
                    HorizontalDivider()
                }
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
