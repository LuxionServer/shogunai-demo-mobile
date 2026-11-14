package app.luxion.bookshelf

class BookRepository(private val books: List<Book> = seedBooks) {

    fun all(): List<Book> = books.sortedBy { it.title }

    fun find(id: Int): Book? = books.find { it.id == id }

    fun search(query: String): List<Book> {
        val text = query.trim()
        return all().filter {
            it.title.contains(text, ignoreCase = true) || it.author.contains(text, ignoreCase = true)
        }
    }
}

val seedBooks = listOf(
    Book(1, "The Pragmatic Programmer", "Andrew Hunt, David Thomas", 1999),
    Book(2, "Refactoring", "Martin Fowler", 1999),
    Book(3, "Domain-Driven Design", "Eric Evans", 2003),
    Book(4, "Working Effectively with Legacy Code", "Michael Feathers", 2004),
    Book(5, "Clean Code", "Robert C. Martin", 2008),
    Book(6, "Growing Object-Oriented Software, Guided by Tests", "Steve Freeman, Nat Pryce", 2009),
    Book(7, "Continuous Delivery", "Jez Humble, David Farley", 2010),
    Book(8, "Designing Data-Intensive Applications", "Martin Kleppmann", 2017),
    Book(9, "Accelerate", "Nicole Forsgren, Jez Humble, Gene Kim", 2018),
    Book(10, "A Philosophy of Software Design", "John Ousterhout", 2018),
    Book(11, "Team Topologies", "Matthew Skelton, Manuel Pais", 2019),
    Book(12, "Software Engineering at Google", "Titus Winters, Tom Manshreck, Hyrum Wright", 2020),
    Book(13, "The Mythical Man-Month", "Frederick P. Brooks Jr.", 1975),
    Book(14, "Structure and Interpretation of Computer Programs", "Harold Abelson, Gerald Jay Sussman", 1985),
    Book(15, "Design Patterns", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", 1994),
    Book(16, "Patterns of Enterprise Application Architecture", "Martin Fowler", 2002),
    Book(17, "Release It!", "Michael T. Nygard", 2007),
    Book(18, "Building Microservices", "Sam Newman", 2015),
    Book(19, "Site Reliability Engineering", "Betsy Beyer, Chris Jones, Jennifer Petoff, Niall Richard Murphy", 2016),
    Book(20, "Fundamentals of Software Architecture", "Mark Richards, Neal Ford", 2020),
)
