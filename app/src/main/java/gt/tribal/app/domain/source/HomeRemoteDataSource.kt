package gt.tribal.app.domain.source

import gt.tribal.app.data.remote.HomeApi
import gt.tribal.app.domain.response.PhotoItemHome
import gt.tribal.core.extension.await


class HomeRemoteDataSource(
    private val homeRemoteApi: HomeApi
) {

    suspend fun getHomePhotos(clientId: String): List<PhotoItemHome> {
        return homeRemoteApi.getPhotos(clientId).await()!!
    }

}