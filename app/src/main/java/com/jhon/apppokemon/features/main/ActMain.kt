package com.jhon.apppokemon.features.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhon.apppokemon.activity.PrincipalActivity
import com.jhon.apppokemon.databinding.ActivityMainBinding
import com.jhon.apppokemon.features.dialogmygroup.DialogMyGroup
import com.jhon.apppokemon.features.dialogpokemonregion.DialogAllPokemonForRegion
import com.jhon.apppokemon.features.grupos.ActGrupos
import com.jhon.apppokemon.features.main.adapter.AdapterRegions
import com.jhon.data.model.bean.PokedexResponse.PokemonEntry
import com.jhon.data.model.bean.grupo.Grupo
import com.jhon.data.model.bean.region.Region

class ActMain : PrincipalActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val adapter = AdapterRegions(object : AdapterRegions.OnClickListener {
        override fun onClick(regi: Region) {
            //VOY A WEB VIEW

            viewModel.loadInfoRegion(regi.name)
//            val intent = Intent(baseContext, ActGrupos::class.java).apply {
//                putExtra("name_region", regi.name)
//                putExtra("id_location", regi.url.replace("https://pokeapi.co/api/v2/location/", ""))
//            }
//            startActivity(intent)

        }

    })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            //rcv
            apRcvRegions.layoutManager = LinearLayoutManager(baseContext)
            apRcvRegions.setHasFixedSize(true)
            apRcvRegions.adapter = adapter
            apRcvRegions.addItemDecoration(DividerItemDecoration(baseContext, DividerItemDecoration.VERTICAL))

            apSwpRefresh.setOnRefreshListener {
                viewModel.loadRegions()
            }

            btnmygroup.setOnClickListener { viewModel.loadMyGroups()
            showProgress()}
        }


        setupObservers()
        showProgress()
        viewModel.loadRegions()
    }

    fun setupObservers() {
        viewModel.lstRegiones.observe(this) { data ->
            hideProgress()
            binding.apSwpRefresh.isRefreshing = false
            if (data.results.isNotEmpty()) adapter.swap(data.results)
        }

        viewModel.lstGrupos.observe(this) {
            if(it.isNotEmpty()){
                showDialogAddGroup(it)
            }
        }

        viewModel.pokedexResponse.observe(this) {
            it?.let {
                showDialogPokemon(it.pokemon_entries)
            }
        }


    }

    private fun showDialogAddGroup(listgrupo: List<Grupo>) {
        hideProgress()
        DialogMyGroup(
            listgrupo,
            title = "Mis Grupos",
            onClickConfirmCancel = { }

        ).show(supportFragmentManager, "dialog_add")
    }

    private fun showDialogPokemon(listgrupo: List<PokemonEntry>) {
        hideProgress()
        DialogAllPokemonForRegion(
            listgrupo,
            title = "Pokemones ",


        ).show(supportFragmentManager, "dialog_add")
    }


}