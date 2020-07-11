package gt.tribal.app.domain.response

class UserData(
    val id: String,
    val username: String,
    val name: String,
    val profile_image: ProfileImageData,
    val total_likes: Int,
    val total_photos: Int
)