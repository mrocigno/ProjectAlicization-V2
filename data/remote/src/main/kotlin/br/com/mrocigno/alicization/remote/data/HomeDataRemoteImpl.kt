package br.com.mrocigno.alicization.remote.data

import br.com.mrocigno.alicization.data.model.Book
import br.com.mrocigno.alicization.data.repository.HomeData

class HomeDataRemoteImpl : HomeData {

    override fun getHomeItems(): List<Book> {
        return listOf(Book("remote", "", "", ""))
    }
}