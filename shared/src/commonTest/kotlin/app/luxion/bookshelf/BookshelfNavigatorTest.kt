package app.luxion.bookshelf

import kotlin.test.Test
import kotlin.test.assertEquals

class BookshelfNavigatorTest {

    private val navigator = BookshelfNavigator(BookRepository())

    @Test
    fun startsOnTheList() {
        assertEquals(Screen.List, navigator.screen)
    }

    @Test
    fun opensABook() {
        navigator.openBook(3)

        assertEquals(Screen.Detail(Book(3, "Domain-Driven Design", "Eric Evans", 2003)), navigator.screen)
    }

    @Test
    fun goesBackToTheList() {
        navigator.openBook(3)

        navigator.back()

        assertEquals(Screen.List, navigator.screen)
    }

    @Test
    fun staysOnTheListWhenOpeningAnUnknownBook() {
        navigator.openBook(99)

        assertEquals(Screen.List, navigator.screen)
    }
}
