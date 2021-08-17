package co.sharif.namava.utils.networkHelper

/**
 * State Management for UI & Data
 */
sealed class ResultNet<T> {

    class Loading<T> : ResultNet<T>()

    data class Success<T>(val data: T) : ResultNet<T>()

    data class ErrorNetwork<T>(val message: String?) : ResultNet<T>()

    data class ErrorException<T>(val message: String?) : ResultNet<T>()

    data class ErrorApi<T>(val error: T? ) : ResultNet<T>()
}

