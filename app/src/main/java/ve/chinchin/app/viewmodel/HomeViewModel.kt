package ve.chinchin.app.viewmodel

import androidx.lifecycle.ViewModel
import ve.chinchin.app.domain.response.RateResponse
import ve.chinchin.app.domain.usecase.HomeUseCase
import gt.tribal.core.extension.LiveResult

class HomeViewModel (private val homeUseCase: HomeUseCase
): ViewModel() {
    val homeLiveData =LiveResult<RateResponse>()

    fun getHomeRates(){
        homeUseCase.execute(homeLiveData)
    }

}