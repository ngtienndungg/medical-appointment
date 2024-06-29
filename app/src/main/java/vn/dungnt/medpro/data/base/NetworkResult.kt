package vn.dungnt.medpro.data.base

sealed class NetworkResult<out T> {
    class Loading<out T> : NetworkResult<T>()

    data class Failure<out T>(
        val message: String?,
        val data: T? = null,
        val exception: Exception? = null
    ) : NetworkResult<T>()

    data class Success<out T>(val message: String? = null, val data: T?) :
        NetworkResult<T>()
}

fun <M, E> NetworkResult<M?>.transformToEntity(mapper: Mapper<M, E>?): NetworkResult<E> {
    return when (this) {
        is NetworkResult.Loading -> NetworkResult.Loading()
        is NetworkResult.Failure -> NetworkResult.Failure(
            message = message,
            exception = exception,
            data = data?.let { mapper?.toEntity(it) })

        is NetworkResult.Success -> NetworkResult.Success(
            message = message,
            data?.let { mapper?.toEntity(it) })
    }
}

fun <T> NetworkResult<T>.handleNetworkResult(
    onSuccess: (NetworkResult.Success<T>) -> Unit = {},
    onFailure: (NetworkResult.Failure<T>) -> Unit = {},
    onLoading: (Boolean) -> Unit = {}
) {
    when (this) {
        is NetworkResult.Loading -> onLoading(true)
        is NetworkResult.Failure -> {
            onLoading(false)
            onFailure(this)
        }

        is NetworkResult.Success -> {
            onLoading(false)
            onSuccess(this)
        }
    }
}