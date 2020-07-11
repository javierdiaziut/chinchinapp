package gt.tribal.app.viewmodel

import androidx.lifecycle.ViewModel
import gt.tribal.app.domain.usecase.DeletePhotoUseCase
import gt.tribal.app.domain.usecase.GetPhotoLocalUseCase
import gt.tribal.app.domain.usecase.SaveFavoritePhotoUseCase
import gt.tribal.app.ui.PhotoEntity
import gt.tribal.core.extension.LiveCompletable
import gt.tribal.core.extension.LiveResult

class PhotoViewModel(
    private val getPhotoLocalUseCase: GetPhotoLocalUseCase,
    private val deletePhotoUseCase: DeletePhotoUseCase
) : ViewModel() {

    val getPhotoLiveData = LiveResult<List<PhotoEntity>>()
    val deletePhotoLiveData = LiveCompletable()


    fun getPhotos() {
        return getPhotoLocalUseCase.execute(getPhotoLiveData)
    }

    fun deletePhoto(id: String) {
        deletePhotoUseCase.execute(deletePhotoLiveData, id)
    }

}