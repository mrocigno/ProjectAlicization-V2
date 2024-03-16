package br.com.mrocigno.alicization.data.repository.search

import kotlinx.coroutines.flow.flow

class SearchRepository(
    private val remoteSearchData: SearchData
) {

    fun search(query: String) = flow {
        emit(remoteSearchData.search(query))
    }
}