package gt.tribal.app.domain.usecase

import gt.tribal.app.domain.repository.PhotoLocalRepository
import gt.tribal.app.ui.PhotoEntity
import gt.tribal.core.coroutines.CompletableUseCase
import gt.tribal.core.coroutines.ResultUnitUseCase
import kotlinx.coroutines.Dispatchers

class SaveFavoritePhotoUseCase(
    private val photoLocalRepository: PhotoLocalRepository
): CompletableUseCase<PhotoEntity>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
)  {
    override suspend fun executeOnBackground(params: PhotoEntity) {
        photoLocalRepository.savePhotoFavorite(params)
    }
}