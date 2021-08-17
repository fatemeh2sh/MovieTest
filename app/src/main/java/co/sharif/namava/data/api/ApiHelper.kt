package co.sharif.namava.data.api

import co.sharif.namava.data.model.AuthInputModel
import co.sharif.namava.data.model.AuthModel
import co.sharif.namava.data.model.VideoModel
import retrofit2.Response

interface ApiHelper {
    suspend fun getAuthorize(input: AuthInputModel) : Response<AuthModel>
    suspend fun getVideo(query:String) : Response<VideoModel>
}