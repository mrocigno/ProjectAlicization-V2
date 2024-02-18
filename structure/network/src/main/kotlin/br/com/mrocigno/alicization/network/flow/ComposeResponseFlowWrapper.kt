package br.com.mrocigno.alicization.network.flow

class ComposeResponseFlowWrapper<T>(
    private val _data: T? = null,
    private val _error: Throwable? = null,
    val isLoading: Boolean = false,
    val isEmpty: Boolean = true
) {

    val hasError: Boolean get() = _error != null
    val hasData: Boolean get() = _data != null

    val data: T get() = _data!!
    val error: Throwable get() = _error!!
}