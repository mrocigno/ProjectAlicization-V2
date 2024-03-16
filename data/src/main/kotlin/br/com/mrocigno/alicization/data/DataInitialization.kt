package br.com.mrocigno.alicization.data

import br.com.mrocigno.alicization.common.ModuleInitializer
import br.com.mrocigno.alicization.data.repository.home.HomeRepository
import br.com.mrocigno.alicization.data.repository.home.getHomeDataLocal
import br.com.mrocigno.alicization.data.repository.home.getHomeDataRemote
import br.com.mrocigno.alicization.data.repository.search.SearchRepository
import br.com.mrocigno.alicization.data.repository.search.getSearchDataRemote
import org.koin.core.module.Module
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
            single {
                SearchRepository(
                    remoteSearchData = getSearchDataRemote()
                )
            }
        }
    )
}
