package app.luxion.bookshelf

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val repository = remember { BookRepository() }
    var query by remember { mutableStateOf("") }
    MaterialTheme {
        BookListScreen(
            books = repository.search(query),
            query = query,
            onQueryChange = { query = it },
        )
    }
}
