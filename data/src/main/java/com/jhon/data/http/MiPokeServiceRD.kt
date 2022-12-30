package com.jhon.data.http

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.jhon.data.model.bean.grupo.Grupo


class MiPokeServiceRD {
    private val firebaseAuth: String? = FirebaseAuth.getInstance().uid
    var database = FirebaseDatabase.getInstance()
    var myRefGrupos = database.getReference("Grupos")
    var myRefPokemones = database.getReference("Pokemones")


    /// get all pokemones


    suspend fun lstMyGrupos(list: MutableLiveData<List<Grupo>>) {
        myRefGrupos.get().addOnSuccessListener {
            val listGrupos: List<Grupo> = it.children.map { dataSnapshot ->
                dataSnapshot.getValue(Grupo::class.java)!!.copy(nombreGrupo = dataSnapshot.key!!)
            }.filter { it.leader.equals(firebaseAuth) }

            for (i in listGrupos) {
                i.isAuthor = true
            }

            list.postValue(listGrupos)

        }.addOnFailureListener {
            Log.i(TAG, "fetchNewsFeed: Lista Vacia")
        }
    }

    suspend fun lstGruposFilterWithRegion(list: MutableLiveData<List<Grupo>>, region: String) {
        myRefGrupos.get().addOnSuccessListener { grupos ->
            var listGrupos: List<Grupo> = grupos.children.map { dataSnapshot ->
                dataSnapshot.getValue(Grupo::class.java)!!.copy(nombreGrupo = dataSnapshot.key!!)
            }.filter { it.region.equals(region) }

            for (i in listGrupos) {
                if (i.leader.equals(firebaseAuth)) i.isAuthor = true
            }

            list.postValue(listGrupos)

        }.addOnFailureListener {
            Log.i(TAG, "fetchNewsFeed: Lista Vacia")
        }
    }


    //delete

    suspend fun deleteGrupo(id: String) {
        myRefGrupos.child(id).removeValue()
    }


    suspend fun insertGrupos(grupo: Grupo) {
        grupo.leader = firebaseAuth
        myRefGrupos.child(grupo.nombreGrupo!!).setValue(grupo)
    }


}