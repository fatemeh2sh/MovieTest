package co.sharif.namava.utils.networkHelper

import retrofit2.Response
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

object CallBackNet {
    suspend fun <T> apiCall(call: suspend () -> Response<T>): ResultNet<T> {
        val response: Response<T>
        try {
            response = call.invoke()
        }catch (t:UnknownError){
            return ResultNet.ErrorNetwork(HandleErrorNet.mapNetworkThrowable(t))
        } catch (t:UnknownHostException){
            return ResultNet.ErrorNetwork(HandleErrorNet.mapNetworkThrowable(t))
        } catch (t:TimeoutException){
            return ResultNet.ErrorNetwork(HandleErrorNet.mapNetworkThrowable(t))
        } catch (t: Throwable) {
            return ResultNet.ErrorException(t.message)
        }

        return if (!response.isSuccessful) {
            val errorBody = response.errorBody()
            ResultNet.ErrorApi(null)
        }
        else {
            return if (response.body() == null) {
                ResultNet.ErrorApi(null)
            } else {
                ResultNet.Success(response.body()!!)
            }
        }
    }
}