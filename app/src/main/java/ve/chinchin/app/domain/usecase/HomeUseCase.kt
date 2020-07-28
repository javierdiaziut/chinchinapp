package ve.chinchin.app.domain.usecase

import ve.chinchin.app.domain.repository.HomeRepository
import ve.chinchin.app.domain.response.RateResponse
import gt.tribal.core.coroutines.ResultUnitUseCase
import kotlinx.coroutines.Dispatchers

open class HomeUseCase(private val homeRepository: HomeRepository): ResultUnitUseCase<RateResponse>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {

    override suspend fun executeOnBackground(): RateResponse? {
        return homeRepository.getRates()
    }
}