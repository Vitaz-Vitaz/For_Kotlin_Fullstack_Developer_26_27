package library_for_Koribsky

data class Book(
    val title: String,
    val author: String,
    val is_available: Boolean = true,
    val ISBN: String,
    val genre: String
)