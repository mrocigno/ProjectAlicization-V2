package br.com.mrocigno.alicization.remote

import br.com.mrocigno.alicization.common.ModuleInitializer
import br.com.mrocigno.alicization.data.repository.home.declareHomeDataRemote
import br.com.mrocigno.alicization.data.repository.search.declareSearchDataRemote
import br.com.mrocigno.alicization.remote.Network.retrofit
import br.com.mrocigno.alicization.remote.api.SearchApi
import br.com.mrocigno.alicization.remote.data.HomeDataRemoteImpl
import br.com.mrocigno.alicization.remote.data.SearchDataRemoteImpl
import br.com.mrocigno.alicization.remote.scrapper.mangatown.MangaTownApi
import br.com.mrocigno.alicization.remote.scrapper.mangatown.MangaTownScrapper
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class RemoteInitialization : ModuleInitializer() {

    override val modules: Array<Module> get() = arrayOf(
        module {
            declareHomeDataRemote { HomeDataRemoteImpl(get()) }
            declareSearchDataRemote { SearchDataRemoteImpl(get()) }
        },
        module {
            singleOf(::MangaTownScrapper)
            single { retrofit.create(MangaTownApi::class.java) }
            single { retrofit.create(SearchApi::class.java) }
        }
    )
}