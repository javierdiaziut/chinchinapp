package gt.tribal.app.domain.usecase

import gt.tribal.app.domain.repository.PhotoLocalRepository
import gt.tribal.app.ui.PhotoEntity
import gt.tribal.core.coroutines.ResultUnitUseCase
import kotlinx.coroutines.Dispatchers

class GetPhotoLocalUseCase(
    private val photoLocalRepository: PhotoLocalRepository
) : ResultUnitUseCase<List<PhotoEntity>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(): List<PhotoEntity>? {
        return photoLocalRepository.getPhotos()
    }
}