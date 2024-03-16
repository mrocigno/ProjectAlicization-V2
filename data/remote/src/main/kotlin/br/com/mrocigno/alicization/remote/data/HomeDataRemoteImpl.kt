package br.com.mrocigno.alicization.remote.data

import br.com.mrocigno.alicization.data.model.Book
import br.com.mrocigno.alicization.data.repository.home.HomeData
import br.com.mrocigno.alicization.remote.scrapper.mangatown.MangaTownScrapper

class HomeDataRemoteImpl(
    private val mangaTownScrapper: MangaTownScrapper
) : HomeData {

    override suspend fun getHomeItems(): List<Book> {
        return mangaTownScrapper.getHome()
    }
}