package br.com.mrocigno.alicization.local

import br.com.mrocigno.alicization.common.ModuleInitializer
import br.com.mrocigno.alicization.data.repository.HomeData
import br.com.mrocigno.alicization.data.repository.declareHomeDataLocal
import br.com.mrocigno.alicization.local.data.HomeDataLocalImpl
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

class LocalInitialization : ModuleInitializer() {

    override val modules: Array<Module> get() = arrayOf(
        module {
            declareHomeDataLocal { HomeDataLocalImpl() }
        }
    )
}