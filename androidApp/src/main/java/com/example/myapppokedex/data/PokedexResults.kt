package com.example.myapppokedex.data

import com.google.gson.annotations.SerializedName


data class PokedexResults(
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "url")
    val url: String
)