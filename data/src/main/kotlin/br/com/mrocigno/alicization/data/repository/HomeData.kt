package br.com.mrocigno.alicization.data.repository

import br.com.mrocigno.alicization.data.model.Book
import br.com.mrocigno.alicization.koingenerator.data.DataKoinAdapter

@DataKoinAdapter
interface HomeData {

    fun getHomeItems(): List<Book>
}