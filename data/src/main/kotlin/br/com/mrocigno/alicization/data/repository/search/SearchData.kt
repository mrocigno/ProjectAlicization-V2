package br.com.mrocigno.alicization.data.repository.search

import br.com.mrocigno.alicization.data.model.Book
import br.com.mrocigno.alicization.koingenerator.data.DataKoinAdapter

@DataKoinAdapter
interface SearchData {

    suspend fun search(query: String): List<Book>
}