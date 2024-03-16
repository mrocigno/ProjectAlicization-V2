package br.com.mrocigno.alicization.network.flow

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class ResponseFlow<T> {

    protected val stateFlow: MutableStateFlow<RequestState<T>> = MutableStateFlow(RequestState.Initial)

    var value: T? = null

    fun collect(
        wrapper: ResponseFlowCollectWrapper<T>.() -> Unit
    ) = collect(collectFlowScope, wrapper)

    fun collect(
        scope: CoroutineScope,
        wrapper: ResponseFlowCollectWrapper<T>.() -> Unit
    ) = scope.launch {
        stateFlow.collect {
            val actions = ResponseFlowCollectWrapper<T>().apply(wrapper)
            when (it) {
                is RequestState.Empty -> {
                    actions.onLoadingState?.invoke(false)
                    actions.onEmptyState?.invoke()
                }
                is RequestState.Loading -> {
                    actions.onLoadingState?.invoke(true)
                }
                is RequestState.Success -> {
                    actions.onLoadingState?.invoke(false)
                    actions.onDataState?.invoke(it.data)
                    value = it.data
                }
                is RequestState.Error -> {
                    actions.onLoadingState?.invoke(false)
                    actions.onErrorState?.invoke(it.t)
                }
                else -> { /* do nothing */ }
            }
        }
    }

    @Composable
    @SuppressLint("StateFlowValueCalledInComposition")
    fun collectAsState(
        initial: T? = null,
        lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
        context: CoroutineContext = EmptyCoroutineContext
    ): State<ComposeResponseFlowWrapper<T>> {
        if (initial != null) stateFlow.compareAndSet(stateFlow.value, RequestState.Success(initial))
        return produceState(ComposeResponseFlowWrapper(initial), this, lifecycleOwner.lifecycle, minActiveState, context) {
            lifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
                if (context == EmptyCoroutineContext) {
                    stateFlow.collect { this@produceState.value = it.toComposeResponseFlowWrapper() }
                } else withContext(context) {
                    stateFlow.collect { this@produceState.value = it.toComposeResponseFlowWrapper() }
                }
            }
        }
    }
}


