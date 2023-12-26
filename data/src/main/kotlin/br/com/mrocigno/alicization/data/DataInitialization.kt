package br.com.mrocigno.alicization.data

import br.com.mrocigno.alicization.common.ModuleInitializer
import br.com.mrocigno.alicization.data.repository.HomeData
import br.com.mrocigno.alicization.data.repository.HomeRepository
import br.com.mrocigno.alicization.data.repository.getHomeDataLocal
import br.com.mrocigno.alicization.data.repository.getHomeDataRemote
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

class DataInitialization : ModuleInitializer() {

    override val modules: Array<Module> get() = arrayOf(
        module {
            single {
                HomeRepository(
                    localHomeData = getHomeDataLocal(),
                    remoteHomeData = getHomeDataRemote(),
                )
            }
        }
    )
}
