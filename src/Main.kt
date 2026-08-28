import library_for_Koribsky.Library_full
import library_for_Koribsky.Type_of_user

val library = Library_full()

fun main() {
    while (true) {
        showMainMenu()
        when (getIntInput("Enter choice: ")) {
            1 -> handleBookManagement()
            2 -> handleUserManagement()
            3 -> handleBorrowingOperations()
            0 -> return
            else -> println("Invalid choice")
        }
    }
}

fun showMainMenu() {
    println("\n=== Library Management ===")
    println("1. Book Management")
    println("2. User Management")
    println("3. Borrowing Operations")
    println("0. Exit")
}

fun handleBookManagement() {
    while (true) {
        println("\n=== Book Management ===")
        println("1. Add Book")
        println("2. Remove Book")
        println("3. Find Book")
        println("4. Search Books")
        println("0. Back")

        when (getIntInput("Enter choice: ")) {
            1 -> addBookMenu()
            2 -> removeBookMenu()
            3 -> findBookMenu()
            4 -> searchBooksMenu()
            0 -> return
            else -> println("Invalid choice")
        }
    }
}


fun addBookMenu() {
    print("Title: ")
    val title = readlnOrNull() ?: ""
    print("Author: ")
    val author = readlnOrNull() ?: ""
    print("ISBN: ")
    val isbn = readlnOrNull() ?: ""
    print("Genre: ")
    val genre = readlnOrNull() ?: ""

    library.add_Book(title, author, isbn, genre)
    println("Book added.")
}

fun removeBookMenu() {
    print("ISBN: ")
    val isbn = readlnOrNull() ?: ""

    if (library.remove_Book(isbn)) {
        println("Book removed.")
    } else {
        println("Book not found.")
    }
}

fun findBookMenu() {
    print("ISBN: ")
    val isbn = readlnOrNull() ?: ""

    val book = library.find_Book(isbn)
    if (book != null) {
        println(book)
    } else {
        println("Book not found.")
    }
}

fun searchBooksMenu() {
    print("Search query: ")
    val query = readlnOrNull() ?: ""

    val books = library.search_forBooks(query)
    if (books.isEmpty()) {
        println("No books found.")
    } else {
        books.forEach { println(it) }
    }
}

fun handleUserManagement() {
    while (true) {
        println("\n=== User Management ===")
        println("1. Register User")
        println("2. Find User")
        println("0. Back")

        when (getIntInput("Enter choice: ")) {
            1 -> registerUserMenu()
            2 -> findUserMenu()
            0 -> return
            else -> println("Invalid choice")
        }
    }
}

fun registerUserMenu() {
    print("Name: ")
    val name = readlnOrNull() ?: ""
    print("User ID: ")
    val userId = readlnOrNull() ?: ""
    print("Email: ")
    val email = readlnOrNull() ?: ""



    println("1. Student")
    println("2. Teacher")
    println("3. Guest")
    val type = when (getIntInput("Type: ")) {
        1 -> Type_of_user.USER
        2 -> Type_of_user.TEACHER
        3 -> Type_of_user.GEAST
        else -> null
    }

    if (type == null) {
        println("Invalid type.")
        return
    }

    library.addUserToBD(name, userId, email, type)
    println("User registered.")
}

fun findUserMenu() {
    print("User ID: ")
    val userId = readlnOrNull() ?: ""

    val user = library.find_User(userId)
    if (user != null) {
        println("${user.name}, ${user.userId}, ${user.email}")
    } else {
        println("User not found.")
    }
}

fun handleBorrowingOperations() {
    while (true) {
        println("\n=== Borrowing Operations ===")
        println("1. Take Book")
        println("2. Return Book")
        println("3. Overdue Books")
        println("0. Back")

        when (getIntInput("Enter choice: ")) {
            1 -> takeBookMenu()
            2 -> returnBookMenu()
            3 -> overdueBooksMenu()
            0 -> return
            else -> println("Invalid choice")
        }
    }
}

fun takeBookMenu() {
    print("User ID: ")
    val userId = readlnOrNull() ?: ""
    print("ISBN: ")
    val isbn = readlnOrNull() ?: ""

    if (library.take_Book(userId, isbn)) {
        println("Book taken.")
    } else {
        println("Cannot take book.")
    }
}

fun returnBookMenu() {
    print("User ID: ")
    val userId = readlnOrNull() ?: ""
    print("ISBN: ")
    val isbn = readlnOrNull() ?: ""

    if (library.return_Book(userId, isbn)) {
        println("Book returned.")
    } else {
        println("Cannot return book.")
    }
}

fun overdueBooksMenu() {
    val overdue = library.getTakenBooks()
    if (overdue.isEmpty()) {
        println("No overdue books.")
    } else {
        overdue.forEach { println(it) }
    }
}

fun getIntInput(prompt: String): Int {
    while (true) {
        print(prompt)
        val input = readlnOrNull()?.trim()
        val number = input?.toIntOrNull()
        if (number != null) {
            return number
        }
        println("Please enter a valid number.")
    }
}