package br.com.mrocigno.alicization.network.flow

class ComposeResponseFlowWrapper<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val isEmpty: Boolean = true,
    val error: Throwable? = null
)