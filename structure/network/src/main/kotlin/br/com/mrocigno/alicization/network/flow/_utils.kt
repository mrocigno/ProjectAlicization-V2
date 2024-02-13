package br.com.mrocigno.alicization.network.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

internal val responseFlowScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
internal val collectFlowScope = CoroutineScope(Dispatchers.Main + SupervisorJob())