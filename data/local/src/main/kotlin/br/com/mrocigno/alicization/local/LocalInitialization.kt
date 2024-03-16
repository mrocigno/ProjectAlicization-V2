package br.com.mrocigno.alicization.local

import br.com.mrocigno.alicization.common.ModuleInitializer
import br.com.mrocigno.alicization.data.repository.home.declareHomeDataLocal
import br.com.mrocigno.alicization.local.data.HomeDataLocalImpl
import org.koin.core.module.Module
import org.koin.dsl.module

class LocalInitialization : ModuleInitializer() {

    override val modules: Array<Module> get() = arrayOf(
        module {
            declareHomeDataLocal { HomeDataLocalImpl() }
        }
    )
}