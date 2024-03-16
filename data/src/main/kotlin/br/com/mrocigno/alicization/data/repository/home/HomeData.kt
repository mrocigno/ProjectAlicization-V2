package br.com.mrocigno.alicization.data.repository.home

import br.com.mrocigno.alicization.data.model.Book
import br.com.mrocigno.alicization.koingenerator.data.DataKoinAdapter

@DataKoinAdapter
interface HomeData {

    suspend fun getHomeItems(): List<Book>
}