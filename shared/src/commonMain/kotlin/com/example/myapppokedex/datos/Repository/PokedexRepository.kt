package com.example.myapppokedex.datos.Repository

import com.example.myapppokedex.datos.Pokedex
import com.example.myapppokedex.initLogger
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get

class PokedexRepository {

    private val httpClient = HttpClient {
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger{
                    override fun log(message: String) {
                        TODO("Not yet implemented")
                        Napier.v(tag = "HttpCliente", message = message)
                    }
                }
            }

    }.also {
        initLogger()
    }

    suspend fun get() : Pokedex{
        val result = httpClient.get("https://pokeapi.co/api/v2/pokemon/?limit=800"){

        }.body<Pokedex>()
        return result
    }

}