package gt.tribal.app.domain.repository

import gt.tribal.app.domain.response.PhotoItemHome
import gt.tribal.app.domain.source.UserRemoteDataSource

class UserPhotosRepository(private val userDataSource: UserRemoteDataSource) {

    suspend fun getUserPhotos(clientId :String, username: String): List<PhotoItemHome> {
        return userDataSource.getUserPhotos(clientId, username)
    }
}