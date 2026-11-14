package app.luxion.bookshelf

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

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

    @Test
    fun searchesByTitle() {
        assertEquals(listOf("Refactoring"), repository.search("Refactor").map { it.title })
    }

    @Test
    fun searchesByAuthor() {
        assertEquals(
            listOf("Patterns of Enterprise Application Architecture", "Refactoring"),
            repository.search("Martin Fowler").map { it.title },
        )
    }

    @Test
    fun searchesIgnoringCase() {
        assertEquals(
            listOf("Patterns of Enterprise Application Architecture", "Refactoring"),
            repository.search("fOWLER").map { it.title },
        )
    }

    @Test
    fun findsNothingWhenNoBookMatches() {
        assertTrue(repository.search("Tolkien").isEmpty())
    }

    @Test
    fun listsEveryBookForAnEmptyQuery() {
        assertEquals(repository.all(), repository.search(""))
    }
}
