package br.com.mrocigno.alicization.network.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MutableResponseFlow<T> : ResponseFlow<T>() {

    fun sync(flow: Flow<T>) = sync(flow) { it }

    fun <R> sync(flow: Flow<R>, map: suspend (input: R) -> T?) = sync(flow, map) { e ->
        stateFlow.value = RequestState.Error(e)
    }

    fun <R> sync(
        flow: Flow<R>,
        map: suspend (input: R) -> T?,
        onError: suspend FlowCollector<R>.(cause: Throwable) -> Unit
    ) = sync(flow, map, onError, responseFlowScope)

    fun <R> sync(
        flow: Flow<R>,
        map: suspend (input: R) -> T?,
        onError: suspend FlowCollector<R>.(cause: Throwable) -> Unit,
        scope: CoroutineScope
    ) = scope.launch {
        stateFlow.value = RequestState.Loading
        flow
            .catch(onError)
            .map(map)
            .collect {
                if (it == null || (it is List<*> && it.isEmpty())) {
                    stateFlow.value = RequestState.Empty
                } else {
                    stateFlow.value = RequestState.Success(it)
                }
            }
    }
}