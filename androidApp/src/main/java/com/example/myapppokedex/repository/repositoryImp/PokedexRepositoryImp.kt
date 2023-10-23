package com.example.myapppokedex.repository.repositoryImp

import com.example.myapppokedex.api.Pokedex
import com.example.myapppokedex.api.PokedexClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.myapppokedex.repository.PokedexRepository
import retrofit2.Response

class PokedexRepositoryImp(private val pokedexClient: PokedexClient) : PokedexRepository {

    override suspend fun getPokedex(): Response<Pokedex> {
        return withContext(Dispatchers.IO) {
            pokedexClient.get()
        }
    }
}