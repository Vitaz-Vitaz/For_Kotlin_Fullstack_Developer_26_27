package library_for_Koribsky

abstract class User (
    val name: String,
    val userId: String,
    val email: String
    )
{
    private val taken_books: MutableList<String> = mutableListOf()
    val show_taken_books: List<String> get() = taken_books

    abstract fun getMaximumBooks(): Int
    abstract fun getYourDays(): Int
}