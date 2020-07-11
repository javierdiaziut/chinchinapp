package gt.tribal.app.domain.source

import gt.tribal.app.data.remote.HomeApi
import gt.tribal.app.domain.response.PhotoItemHome
import gt.tribal.core.extension.await

class UserRemoteDataSource(
    private val homeRemoteApi: HomeApi
) {

    suspend fun getUserPhotos(clientId: String, username: String): List<PhotoItemHome> {
        return homeRemoteApi.getUserPhotos(username, clientId).await()!!
    }
}