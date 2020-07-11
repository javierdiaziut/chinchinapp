package gt.tribal.app.domain.repository

import gt.tribal.app.domain.response.PhotoItemHome
import gt.tribal.app.domain.source.HomeRemoteDataSource

class HomeRepository(private val homeRemoteDataSource: HomeRemoteDataSource) {

    suspend fun homePhotos(clientId :String): List<PhotoItemHome> {
        return homeRemoteDataSource.getHomePhotos(clientId)
    }
}