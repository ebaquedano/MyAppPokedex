package com.example.myapppokedex.datos

import kotlinx.serialization.Serializable


@Serializable
data class RandomData(
    val name: NameData,
    val picture: PictureData
)
