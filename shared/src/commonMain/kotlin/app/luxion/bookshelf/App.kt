package app.luxion.bookshelf

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val repository = remember { BookRepository() }
    MaterialTheme {
        BookListScreen(repository.all())
    }
}
