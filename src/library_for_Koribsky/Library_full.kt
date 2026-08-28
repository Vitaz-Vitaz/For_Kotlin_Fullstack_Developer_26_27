package library_for_Koribsky

import java.time.LocalDate

class Library_full : LibraryOperations {

    private val books: HashMap<String, Book> = HashMap()
    private val users: HashMap<String, User> = HashMap()
    private val history: MutableList<TakedBooksRecording> = mutableListOf()
    private val genres: MutableSet<String> = mutableSetOf()

    override fun add_Book(title: String, author: String, ISBN: String, genre: String) {
        val book = Book(title = title, author = author, ISBN = ISBN, genre = genre)
        books[ISBN] = book
        genres.add(genre)
    }

    override fun remove_Book(ISBN: String): Boolean {
        return books.remove(ISBN) != null
    }

    override fun find_Book(ISBN: String): Book? {
        return books[ISBN]
    }

    override fun search_forBooks(query: String): List<Book> {
        val q = query.lowercase()
        return books.values.filter {
            it.title.lowercase().contains(q) ||
                    it.author.lowercase().contains(q) ||
                    it.ISBN.lowercase().contains(q)
        }
    }

    override fun addUserToBD(name: String, userId: String, email: String, type: Type_of_user) {
        val user: User = when (type) {
            Type_of_user.USER -> Student(name, userId, email)
            Type_of_user.TEACHER -> Teacher(name, userId, email)
            Type_of_user.GEAST -> Geast(name, userId, email)
        }
        users[userId] = user
    }

    override fun find_User(userId: String): User? {
        return users[userId]
    }

    override fun take_Book(userId: String, ISBN: String): Boolean {
        val user = users[userId] ?: return false
        val book = books[ISBN] ?: return false

        if (!book.is_available) return false
        if (!user.canTake()) return false

        book.is_available = false
        user.addTakenBook(ISBN)

        val takenDate = LocalDate.now()
        val dueDate = takenDate.plusDays(user.getYourDays().toLong())
        history.add(TakedBooksRecording(userId, ISBN, takenDate, dueDate))

        return true
    }

    override fun return_Book(userId: String, isbn: String): Boolean {
        val user = users[userId] ?: return false
        val book = books[isbn] ?: return false

        val record = history.lastOrNull {
            it.userId == userId && it.ISBN == isbn && !it.isReturned
        } ?: return false

        record.returnDate = LocalDate.now()
        book.is_available = true
        user.removeTakenBook(isbn)

        return true
    }

    override fun getTakenBooks(): List<TakedBooksRecording> {
        return history.filter { it.isOverTaken() }
    }
}