package gt.tribal.app.data.remote

import gt.tribal.app.domain.response.PhotoItemHome
import gt.tribal.core.base.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApi {

    @GET("/photos")
    fun getPhotos(@Query("client_id") clientId: String): Call<List<PhotoItemHome>>

    @GET("/users/{username}/photos")
    fun getUserPhotos(@Path("username") username: String, @Query("client_id") clientId: String ): Call<List<PhotoItemHome>>

}