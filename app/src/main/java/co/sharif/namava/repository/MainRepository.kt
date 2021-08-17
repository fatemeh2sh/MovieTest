package co.sharif.namava.repository

import co.sharif.namava.data.api.ApiHelper
import co.sharif.namava.data.model.AuthInputModel
import co.sharif.namava.utils.networkHelper.CallBackNet
import javax.inject.Inject

class SearchRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getVideo(query:String) =
        apiHelper.getVideo(query)

    suspend fun getVideo1(q:String) =
        CallBackNet.apiCall { apiHelper.getVideo(q) }
}
