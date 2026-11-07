package app.luxion.bookshelf

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BookRepositoryTest {

    private val repository = BookRepository()

    @Test
    fun listsEveryBook() {
        assertEquals(20, repository.all().size)
    }

    @Test
    fun listsBooksByTitle() {
        val titles = repository.all().map { it.title }

        assertEquals(titles.sorted(), titles)
        assertEquals("A Philosophy of Software Design", titles.first())
    }

    @Test
    fun findsABookById() {
        assertEquals("Refactoring", repository.find(2)?.title)
    }

    @Test
    fun findsNothingForAnUnknownId() {
        assertNull(repository.find(99))
    }
}
