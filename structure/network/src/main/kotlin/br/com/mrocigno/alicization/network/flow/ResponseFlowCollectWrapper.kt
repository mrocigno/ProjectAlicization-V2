package br.com.mrocigno.alicization.network.flow

class ResponseFlowCollectWrapper<T> {
    internal var onEmptyState: (() -> Unit)? = null
    internal var onLoadingState: ((isLoading: Boolean) -> Unit)? = null
    internal var onDataState: ((data: T) -> Unit)? = null
    internal var onErrorState: ((t: Throwable) -> Unit)? = null

    fun empty(onEmpty: () -> Unit) {
        onEmptyState = onEmpty
    }

    fun loading(onLoading: (isLoading: Boolean) -> Unit) {
        onLoadingState = onLoading
    }

    fun data(onData: (data: T) -> Unit) {
        onDataState = onData
    }

    fun error(onError: (t: Throwable) -> Unit) {
        onErrorState = onError
    }
}

sealed class RequestState<out T> {
    object Initial : RequestState<Nothing>()
    object Empty : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
    class Success<T>(val data: T) : RequestState<T>()
    class Error(val t: Throwable) : RequestState<Nothing>()

    fun <T> toComposeResponseFlowWrapper() = ComposeResponseFlowWrapper(
        isLoading = this is Loading,
        isEmpty = this is Empty || this is Initial,
        error = (this as? Error)?.t,
        data = (this as? Success<T>)?.data
    )
}