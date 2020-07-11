package gt.tribal.app.viewmodel

import androidx.lifecycle.ViewModel
import gt.tribal.app.domain.request.GetUserPhotosRequest
import gt.tribal.app.domain.response.PhotoItemHome
import gt.tribal.app.domain.usecase.UserPhotosUseCase
import gt.tribal.core.extension.LiveResult

class ProfileUserViewModel(
    private val userUseCase: UserPhotosUseCase
): ViewModel() {

    val getUserPhotosLiveData =LiveResult<List<PhotoItemHome>>()

    fun getUserPhotos(username: String){
        var request = GetUserPhotosRequest("7U-0X9jtaA4AF46MkMXNbVVECfcG1SAQx8zFkZ8f_xQ",username)
        userUseCase.execute(getUserPhotosLiveData, request)
    }

}