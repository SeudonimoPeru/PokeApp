package com.jhon.apppokemon.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhon.data.model.bean.InfoRegionResponse.InfoRegionResponse
import com.jhon.data.model.bean.PokedexResponse.PokedexResponse
import com.jhon.data.model.bean.grupo.Grupo
import com.jhon.data.model.bean.region.RegionsResponse
import com.jhon.domain.MiPokeUseCase
import com.jhon.domain.PokeUseCase
import kotlinx.coroutines.launch


class MainViewModel() : ViewModel() {

    var pokeUseCase = PokeUseCase()
    var myPokeUseCase = MiPokeUseCase()


    private val _lstRegiones = MutableLiveData<RegionsResponse>()
    val lstRegiones: LiveData<RegionsResponse> = _lstRegiones

    private val _lstGrupos = MutableLiveData<List<Grupo>>(listOf())
    val lstGrupos: LiveData<List<Grupo>> = _lstGrupos

    private val _infoRegionResponse = MutableLiveData<InfoRegionResponse>()
    val infoRegionResponse: LiveData<InfoRegionResponse> = _infoRegionResponse


    private val _pokedexResponse = MutableLiveData<PokedexResponse>()
    val pokedexResponse: LiveData<PokedexResponse> = _pokedexResponse


    fun loadRegions() {
        //COROUTINE
        viewModelScope.launch {


            runCatching {
                //solicita las regiones  al servicio
                pokeUseCase.repoOnline.getAllRegion()
            }.onSuccess {
                it?.let {
                    //lo consigue
                    _lstRegiones.value = it
                }
            }.onFailure {
                // no trajo nada
            }

        }
    }


    fun loadInfoRegion(region: String) {
        //COROUTINE
        viewModelScope.launch {


            runCatching {
                //solicita las regiones  al servicio
                pokeUseCase.repoOnline.getInfoRegion(region)
            }.onSuccess {
                it?.let {
                    //lo consigue
                    _infoRegionResponse.value = it
                    loadPokedex()
                }
            }.onFailure {
                // no trajo nada
            }

        }
    }

    fun loadPokedex() {
        //COROUTINE
        viewModelScope.launch {


            runCatching {
                //solicita las regiones  al servicio
                pokeUseCase.repoOnline.getPokedex(infoRegionResponse.value!!.pokedexes[0].url.replace("https://pokeapi.co/api/v2/pokedex/",""))
            }.onSuccess {
                it?.let {
                    //lo consigue
                    _pokedexResponse.value = it
                }
            }.onFailure {
                // no trajo nada
            }

        }
    }


    fun loadMyGroups() {
        //COROUTINE

        viewModelScope.launch {


            runCatching {
                //solicita las grupos  al servicio
                myPokeUseCase.miPokeRepositoryRD.getAllMyGrupos(_lstGrupos)
            }.onSuccess {
                it?.let {
                    //lo consigue

                }
            }.onFailure {
                // no trajo nada
            }

        }
    }


}
