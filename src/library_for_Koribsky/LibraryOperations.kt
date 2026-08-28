package library_for_Koribsky

 interface LibraryOperations {
     fun add_Book(title: String, author: String, ISBN: String, genre: String)
     fun remove_Book(ISBN: String): Boolean
     fun find_Book(ISBN: String): Book?
     fun search_forBooks(query: String): List<Book>
     fun addUserToBD(name: String, userId: String, email: String, type: Type_of_user)
     fun find_User(userId: String) : User?
     fun take_Book(userId: String, ISBN: String): Boolean
     fun return_Book(userId: String, isbn: String): Boolean
     fun getTakenBooks(): List<TakedBooksRecording>
}