package com.jhon.data.repository

import androidx.lifecycle.MutableLiveData
import com.jhon.data.http.MiPokeServiceRD
import com.jhon.data.model.bean.grupo.Grupo

class MiPokeRepositoryRD {
    //repo de http
    private val api by lazy { MiPokeServiceRD() }

    suspend fun getAllMyGrupos(list: MutableLiveData<List<Grupo>>) {
        return api.lstMyGrupos(list)
    }

    suspend fun getAllMyGruposFilterWithRegion(list: MutableLiveData<List<Grupo>>, region : String) {
        return api.lstGruposFilterWithRegion(list,region)
    }


    suspend fun deleteGroup(id: String) {
        return api.deleteGrupo(id)
    }

    suspend fun insertGrupo(grupo: Grupo) {
        return api.insertGrupos(grupo)
    }

}