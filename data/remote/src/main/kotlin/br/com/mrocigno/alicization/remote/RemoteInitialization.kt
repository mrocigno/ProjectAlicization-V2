package br.com.mrocigno.alicization.remote

import br.com.mrocigno.alicization.common.ModuleInitializer
import br.com.mrocigno.alicization.data.repository.declareHomeDataRemote
import br.com.mrocigno.alicization.remote.data.HomeDataRemoteImpl
import org.koin.core.module.Module
import org.koin.dsl.module

class RemoteInitialization : ModuleInitializer() {

    override val modules: Array<Module> get() = arrayOf(
        module {
            declareHomeDataRemote { HomeDataRemoteImpl() }
        }
    )
}