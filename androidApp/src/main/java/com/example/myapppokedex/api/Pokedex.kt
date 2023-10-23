package com.example.myapppokedex.api

import com.google.gson.annotations.SerializedName
import com.example.myapppokedex.data.PokedexResults

data class Pokedex(
    @SerializedName(value = "count")
    val count: Int,
    @SerializedName(value = "next")
    val next: String,
    @SerializedName(value = "previous")
    val previous: String,
    @SerializedName(value = "results")
    val results: List<PokedexResults>
)