package app.luxion.bookshelf

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val repository = remember { BookRepository() }
    val navigator = remember { BookshelfNavigator(repository) }
    MaterialTheme {
        when (val screen = navigator.screen) {
            Screen.List -> BookListScreen(repository.all(), onBookClick = { navigator.openBook(it.id) })
            is Screen.Detail -> BookDetailScreen(screen.book, onBack = navigator::back)
        }
    }
}
