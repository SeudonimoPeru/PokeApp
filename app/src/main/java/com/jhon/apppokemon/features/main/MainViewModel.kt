package com.jhon.apppokemon.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
