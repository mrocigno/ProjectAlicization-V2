package br.com.mrocigno.alicization.local.data

import br.com.mrocigno.alicization.data.model.Book
import br.com.mrocigno.alicization.data.repository.HomeData

class HomeDataLocalImpl : HomeData {

    override fun getHomeItems(): List<Book> {
        return listOf(Book("local", "", "", ""))
    }
}