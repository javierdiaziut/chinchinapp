package gt.tribal.app.domain.source

import gt.tribal.app.data.remote.HomeApi
import gt.tribal.app.domain.response.RateResponse
import gt.tribal.core.extension.await


class HomeRemoteDataSource(
    private val homeRemoteApi: HomeApi
) {

    suspend fun getRates(): RateResponse {
        return homeRemoteApi.getRates().await()!!
    }

}