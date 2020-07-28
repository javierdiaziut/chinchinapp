package ve.chinchin.app.domain.repository

import ve.chinchin.app.domain.response.RateResponse
import ve.chinchin.app.domain.source.HomeRemoteDataSource

class HomeRepository(private val homeRemoteDataSource: HomeRemoteDataSource) {

    suspend fun getRates(): RateResponse {
        return homeRemoteDataSource.getRates()
    }
}