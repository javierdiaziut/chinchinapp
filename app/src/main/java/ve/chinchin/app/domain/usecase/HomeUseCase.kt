package gt.tribal.app.domain.usecase

import gt.tribal.app.domain.repository.HomeRepository
import gt.tribal.app.domain.response.RateResponse
import gt.tribal.core.coroutines.ResultUnitUseCase
import kotlinx.coroutines.Dispatchers

open class HomeUseCase(private val homeRepository: HomeRepository): ResultUnitUseCase< RateResponse>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {

    override suspend fun executeOnBackground(): RateResponse? {
        return homeRepository.getRates()
    }
}