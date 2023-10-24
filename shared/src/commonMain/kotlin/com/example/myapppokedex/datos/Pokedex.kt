package com.example.myapppokedex.datos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokedex(
    @SerialName(value = "results")
    val results: List<PokedexResult>
)
