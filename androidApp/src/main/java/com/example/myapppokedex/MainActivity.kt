package com.example.myapppokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapppokedex.ScreemStates.PokedexScreenState
import com.example.myapppokedex.adapter.PokedexAdapter
import com.example.myapppokedex.android.databinding.ActivityMainBinding
import com.example.myapppokedex.datos.Pokedex
import com.example.myapppokedex.datos.Repository.RepositoryDB.RepositoryPokedexBD
import com.example.myapppokedex.viewmodel.PokedexViewModel
import com.example.myapppokedex.viewmodel.PokedexViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var pokedexAdapter: PokedexAdapter
    private lateinit var viewModel: PokedexViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(com.example.myapppokedex.android.R.style.AppTheme)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        // Listen to Retrofit response
        viewModel = ViewModelProvider(this, PokedexViewModelFactory())[PokedexViewModel::class.java]
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.screenState.collect {
                    when (it) {
                        PokedexScreenState.Loading -> showLoading()
                        PokedexScreenState.Error -> handlerError()
                        is PokedexScreenState.ShowPokedex -> showPokedex(it.pokedex)
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        pokedexAdapter = PokedexAdapter()

        val gridLayoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        with(binding.rvPokedex) {
            this.layoutManager = gridLayoutManager
            this.setHasFixedSize(true)
            this.adapter = pokedexAdapter
        }
    }

    private fun showPokedex(pokedex: Pokedex) {
        binding.pokedexProgressBar.visibility = View.GONE
        pokedexAdapter.updatePokedex(pokedex.results)

        val repositoryPokedexBD  = RepositoryPokedexBD (databaseDriverFactory = DatabaseDriverFactory(this))

        for (result in pokedex.results){
            repositoryPokedexBD.insert(result.name, result.url)
        }

    }

    private fun handlerError() {

        val repositoryPokedexBD  = RepositoryPokedexBD (databaseDriverFactory = DatabaseDriverFactory(this))
        val pokemon = repositoryPokedexBD.get()

        if (pokemon.isEmpty()){
          
        }else{
            binding.pokedexProgressBar.visibility = View.GONE
            pokedexAdapter.updatePokedex(pokemon)
            Toast.makeText(this, "Error buscando la informacion", Toast.LENGTH_LONG).show()
        }
    }

        private fun showLoading() {
        binding.pokedexProgressBar.visibility = View.VISIBLE
    }

}