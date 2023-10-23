package com.example.myapppokedex.api

import retrofit2.Response
import retrofit2.http.GET

interface PokedexClient {

    @GET("pokemon/?limit=800")
    suspend fun get() : Response<Pokedex>

}