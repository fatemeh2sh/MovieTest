package co.sharif.namava.data.api

import co.sharif.namava.data.model.AuthInputModel
import co.sharif.namava.data.model.AuthModel
import co.sharif.namava.data.model.VideoModel
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(var apiService: ApiService): ApiHelper {

    override suspend fun getAuthorize(input: AuthInputModel): Response<AuthModel> =
        apiService.getAuthorize(input)

    override suspend fun getVideo(query: String): Response<VideoModel> =
        apiService.getVideo(query)
}