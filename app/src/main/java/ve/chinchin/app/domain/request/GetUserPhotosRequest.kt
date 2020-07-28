package gt.tribal.app.domain.request

data class GetUserPhotosRequest(
    val clientId: String,
    val username: String
)