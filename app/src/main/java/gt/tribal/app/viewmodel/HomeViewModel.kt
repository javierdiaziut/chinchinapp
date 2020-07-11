package gt.tribal.app.viewmodel

import androidx.lifecycle.ViewModel
import gt.tribal.app.domain.request.HomeRequest
import gt.tribal.app.domain.response.PhotoItemHome
import gt.tribal.app.domain.usecase.HomeUseCase
import gt.tribal.app.domain.usecase.SaveFavoritePhotoUseCase
import gt.tribal.app.ui.PhotoEntity
import gt.tribal.core.extension.LiveCompletable
import gt.tribal.core.extension.LiveResult

class HomeViewModel (private val homeUseCase: HomeUseCase,
                     private val saveFavoritePhotoUseCase: SaveFavoritePhotoUseCase
): ViewModel() {
    val homeLiveData =LiveResult<List<PhotoItemHome>>()
    val saveFavoriteLiveData = LiveCompletable()
    var request = HomeRequest("7U-0X9jtaA4AF46MkMXNbVVECfcG1SAQx8zFkZ8f_xQ")

    fun getHomePhotos(){
        homeUseCase.execute(homeLiveData, request)
    }

    fun savePhoto(photo: PhotoEntity) {
        saveFavoritePhotoUseCase.execute(saveFavoriteLiveData, photo)
    }
}