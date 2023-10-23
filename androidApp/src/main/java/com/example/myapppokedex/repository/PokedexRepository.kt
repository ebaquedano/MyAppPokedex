package com.example.myapppokedex.repository

import com.example.myapppokedex.api.Pokedex
import retrofit2.Response

interface PokedexRepository {

    suspend fun getPokedex() : Response<Pokedex>
}