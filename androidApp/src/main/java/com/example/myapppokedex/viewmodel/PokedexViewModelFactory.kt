package com.example.myapppokedex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapppokedex.datos.Repository.PokedexRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokedexViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val pokedexRepository = PokedexRepository()

        return PokedexViewModel(pokedexRepository) as T
    }
}