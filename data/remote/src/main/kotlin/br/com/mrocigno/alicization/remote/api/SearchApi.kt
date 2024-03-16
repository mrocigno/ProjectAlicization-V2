package br.com.mrocigno.alicization.remote.api

import br.com.mrocigno.alicization.remote.model.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("https://myanimelist.net/search/prefix.json?type=manga&v=1")
    suspend fun fetchSearch(
        @Query("keyword") keyword: String
    ) : SearchResponse
}