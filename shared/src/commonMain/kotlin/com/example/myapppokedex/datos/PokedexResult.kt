package com.example.myapppokedex.datos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokedexResult(
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "url")
    val url: String
)
