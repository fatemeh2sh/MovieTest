package co.sharif.namava.utils.networkHelper

object HandleErrorNet {

    fun mapNetworkThrowable(throwable: Throwable): String? {
        return throwable.message
    }
}