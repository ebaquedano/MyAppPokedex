package com.example.myapppokedex.datos.Repository.RepositoryDB

import com.example.myapppokedex.DatabaseDriverFactory
import com.example.myapppokedex.datos.NameData
import com.example.myapppokedex.datos.PictureData
import com.example.myapppokedex.datos.RandomData
import com.mypokedex.db.AppDatabase

class RepositoryPokedexBD (databaseDriverFactory : DatabaseDriverFactory) {

    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.mypokedexQueries

    fun insert(name:String, url:String){
        dbQuery.transaction {
            dbQuery.insertPokemon(name = name, url = url)
        }
    }
    fun get(): List <RandomData> {
        val results: List <RandomData> = dbQuery.selectAllPokemon(){
                name, url -> RandomData(NameData(name), PictureData(url))
        }.executeAsList()

        return results
    }
}