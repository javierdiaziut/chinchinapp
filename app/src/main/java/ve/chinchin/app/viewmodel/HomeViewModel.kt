package gt.tribal.app.viewmodel

import androidx.lifecycle.ViewModel
import gt.tribal.app.domain.response.RateResponse
import gt.tribal.app.domain.usecase.HomeUseCase
import gt.tribal.core.extension.LiveResult

class HomeViewModel (private val homeUseCase: HomeUseCase
): ViewModel() {
    val homeLiveData =LiveResult<RateResponse>()

    fun getHomeRates(){
        homeUseCase.execute(homeLiveData)
    }

}