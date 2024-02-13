package br.com.mrocigno.alicization.remote.data

import br.com.mrocigno.alicization.data.model.Book
import br.com.mrocigno.alicization.data.repository.HomeData
import kotlinx.coroutines.delay

class HomeDataRemoteImpl : HomeData {

    override suspend fun getHomeItems(): List<Book> {
        delay(1000L)
        return listOf(Book("remote", "", "", ""))
    }
}