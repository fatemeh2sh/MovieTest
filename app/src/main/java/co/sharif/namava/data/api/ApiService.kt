package co.sharif.namava.data.api

import co.sharif.namava.data.model.AuthInputModel
import co.sharif.namava.data.model.AuthModel
import co.sharif.namava.data.model.VideoModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("oauth/authorize/client")
    suspend fun getAuthorize(@Body body: AuthInputModel) :
            Response<AuthModel>

    @GET("videos")
    suspend fun getVideo(@Query("query") query:String) :
            Response<VideoModel>

}