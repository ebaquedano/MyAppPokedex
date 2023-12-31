package com.example.myapppokedex.ScreemStates

import com.example.myapppokedex.datos.Pokedex

sealed class PokedexScreenState {
    object Loading : PokedexScreenState()

    object Error : PokedexScreenState()

    class ShowPokedex(val pokedex : Pokedex) : PokedexScreenState()
}