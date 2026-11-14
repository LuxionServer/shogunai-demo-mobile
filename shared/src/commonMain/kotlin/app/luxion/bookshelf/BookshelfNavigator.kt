package app.luxion.bookshelf

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed interface Screen {
    data object List : Screen
    data class Detail(val book: Book) : Screen
}

class BookshelfNavigator(private val repository: BookRepository) {

    var screen: Screen by mutableStateOf(Screen.List)
        private set

    fun openBook(id: Int) {
        repository.find(id)?.let { screen = Screen.Detail(it) }
    }

    fun back() {
        screen = Screen.List
    }
}
