package br.com.mrocigno.alicization.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object Network {

    private val gsonConverter =
        GsonConverterFactory.create(GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .create())

    private val okhttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.project-alicization-v2.com.br/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(gsonConverter)
        .client(okhttpClient)
        .build()
}