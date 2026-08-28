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

class Student(name: String, userId: String, email: String) : User(name, userId, email){
    override fun getMaximumBooks() = 3
    override fun getYourDays() = 14
}

class Teacher(name: String, userId: String, email: String): User(name, userId, email){
    override fun getMaximumBooks() = 10
    override fun getYourDays() = 30

}

class Geast(name: String, userId: String, email: String): User(name, userId, email){
    override fun getMaximumBooks() = 1
    override fun getYourDays() = 7

}