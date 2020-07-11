package gt.tribal.app.domain.usecase

import gt.tribal.app.domain.repository.PhotoLocalRepository
import gt.tribal.core.coroutines.CompletableUseCase
import kotlinx.coroutines.Dispatchers

class DeletePhotoUseCase(
    private val photoLocalRepository: PhotoLocalRepository
): CompletableUseCase<String>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: String) {
        return photoLocalRepository.deletePhoto(params)
    }
}