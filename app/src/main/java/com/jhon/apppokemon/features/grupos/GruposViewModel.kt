package com.jhon.apppokemon.features.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhon.data.model.bean.grupo.Grupo
import com.jhon.domain.MiPokeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GruposViewModel : ViewModel() {

    var PokeUseCase = MiPokeUseCase()


    private val _lstGrupos = MutableLiveData<List<Grupo>>()
    val lstGrupos: LiveData<List<Grupo>> = _lstGrupos

    private val _region = MutableLiveData<String>("")
    val region: LiveData<String> = _region

    private val _isloading = MutableLiveData<Boolean>(false)
    val isloading: LiveData<Boolean> = _isloading


    fun insertGrupo(nombreGrupo: String) {
        //COROUTINE
        viewModelScope.launch(Dispatchers.IO) {
            launch(Dispatchers.Main) {
                _isloading.value = true
            }


            runCatching {
                //solicita insertar nuevo grupo
                PokeUseCase.miPokeRepositoryRD.insertGrupo(Grupo(nombreGrupo, region.value))
            }.onSuccess {
                it?.let {
                    //lo consigue
                    getAllListGroups()
                }
            }.onFailure {
                // no insertó
                launch(Dispatchers.Main) {
                    _isloading.value = false
                }
            }

        }
    }

    fun setupRegion(region: String) {
        _region.value = region
        getAllListGroups()
    }

    fun getAllListGroups() {
        //COROUTINE
        viewModelScope.launch(Dispatchers.IO) {


            runCatching {
                //solicita noticia de servicio
                PokeUseCase.miPokeRepositoryRD.getAllMyGruposFilterWithRegion(_lstGrupos, region.value!!)
            }.onSuccess {
                it?.let {
                    //lo consigue
                    Log.i("Se trajo", "No se trajo")
                }
            }.onFailure {
                // no trajo nada

            }

        }
    }


}
