package br.com.mrocigno.alicization.remote.data

import br.com.mrocigno.alicization.data.model.Book
import br.com.mrocigno.alicization.data.repository.search.SearchData
import br.com.mrocigno.alicization.remote.api.SearchApi

class SearchDataRemoteImpl(
    private val searchApi: SearchApi
) : SearchData {

    override suspend fun search(query: String) = searchApi.fetchSearch(query).let {
        it.categories.first().items.map { manga ->
            Book(
                name = manga.name,
                thumb = manga.imageUrl,
                link = "",
                source = "myanimelist"
            )
        }
    }
}