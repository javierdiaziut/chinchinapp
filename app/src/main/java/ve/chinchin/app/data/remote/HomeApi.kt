package gt.tribal.app.data.remote

import gt.tribal.app.domain.response.RateResponse
import retrofit2.Call
import retrofit2.http.GET

interface HomeApi {

    @GET("/api/v3/exchange_rates")
    fun getRates(): Call<RateResponse>

}