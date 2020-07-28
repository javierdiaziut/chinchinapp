package ve.chinchin.app.domain.request

data class GetUserPhotosRequest(
    val clientId: String,
    val username: String
)