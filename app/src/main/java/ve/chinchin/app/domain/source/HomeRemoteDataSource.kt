package ve.chinchin.app.domain.source

import ve.chinchin.app.data.remote.HomeApi
import ve.chinchin.app.domain.response.RateResponse
import gt.tribal.core.extension.await


class HomeRemoteDataSource(
    private val homeRemoteApi: HomeApi
) {

    suspend fun getRates(): RateResponse {
        return homeRemoteApi.getRates().await()!!
    }

}