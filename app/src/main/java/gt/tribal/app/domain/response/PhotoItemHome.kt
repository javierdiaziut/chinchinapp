package gt.tribal.app.domain.response

data class HomeResponse(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val promoted_at: String,
    val width: String,
    val height: String,
    val color: String,
    val description: String,
    val alt_description: String,
    val urls: UrlData,
    val likes: Int,
    val user: UserData

)


