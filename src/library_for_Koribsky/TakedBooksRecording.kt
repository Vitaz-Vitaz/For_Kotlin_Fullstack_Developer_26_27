package library_for_Koribsky

import java.time.LocalDate
data class TakedBooksRecording (
    val userId : String,
    val ISBN: String,
    val takenDate: LocalDate,
    val dueDate: LocalDate,
    var returnDate:LocalDate? = null
)
{
    val isReturned: Boolean
        get(){
            if (returnDate != null){
                return true
            }
            else {
                return false
            }
        }
    fun isOverTaken(): Boolean{
        if (isReturned) {
            return false
        }

        val t = LocalDate.now()
        if (t.isAfter(dueDate)) {
            return true
        }
        else{
            return false
        }
    }
}