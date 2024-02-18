package br.com.mrocigno.alicization.remote.scrapper.mangatown

import retrofit2.http.GET

interface MangaTownApi {

    @GET("https://www.mangatown.com/")
    suspend fun getHomeItems(): String
}