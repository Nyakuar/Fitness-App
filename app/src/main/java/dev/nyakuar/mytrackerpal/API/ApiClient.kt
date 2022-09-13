package dev.Lavyne.mytrackerpal.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    var retrofit= Retrofit
        .Builder()
        .baseUrl("http://192.81.215.35")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> buldApiClient(apiInterface:Class<T>):T{
        return retrofit.create(apiInterface)
    }

}