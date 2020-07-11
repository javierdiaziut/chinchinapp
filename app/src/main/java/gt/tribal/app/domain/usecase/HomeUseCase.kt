package gt.tribal.app.domain.usecase

import gt.tribal.app.domain.repository.HomeRepository
import gt.tribal.app.domain.request.HomeRequest
import gt.tribal.app.domain.response.PhotoItemHome
import gt.tribal.core.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers

open class HomeUseCase(private val homeRepository: HomeRepository): ResultUseCase<HomeRequest, List<PhotoItemHome>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: HomeRequest): List<PhotoItemHome>? {
        return homeRepository.homePhotos(params.clientId)
    }
}