package br.com.mrocigno.alicization.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object Network {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.mangatown.com/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(
            GsonBuilder().apply {
                setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            }.create()
        ))
        .client(OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build())
        .build()
}