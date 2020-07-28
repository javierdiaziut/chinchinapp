package gt.tribal.app.domain.repository

import gt.tribal.app.domain.response.RateResponse
import gt.tribal.app.domain.source.HomeRemoteDataSource

class HomeRepository(private val homeRemoteDataSource: HomeRemoteDataSource) {

    suspend fun getRates(): RateResponse {
        return homeRemoteDataSource.getRates()
    }
}