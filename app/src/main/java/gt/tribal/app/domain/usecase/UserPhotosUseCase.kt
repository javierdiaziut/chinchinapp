package gt.tribal.app.domain.usecase

import gt.tribal.app.domain.repository.UserPhotosRepository
import gt.tribal.app.domain.request.GetUserPhotosRequest
import gt.tribal.app.domain.response.PhotoItemHome
import gt.tribal.core.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers

class UserPhotosUseCase(private val userPhotosRepository: UserPhotosRepository):
    ResultUseCase<GetUserPhotosRequest, List<PhotoItemHome>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: GetUserPhotosRequest): List<PhotoItemHome>? {
        return userPhotosRepository.getUserPhotos(params.clientId, params.username)
    }
}