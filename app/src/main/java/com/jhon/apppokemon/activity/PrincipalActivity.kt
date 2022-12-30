package com.jhon.apppokemon.activity

import androidx.appcompat.app.AppCompatActivity
import com.jhon.apppokemon.utils.componentes.loading.Loading

open class PrincipalActivity : AppCompatActivity() {

    private var loadingSplash: Loading = Loading()


    fun showProgress() {
        loadingSplash.show(supportFragmentManager, "loadingDialog")
    }

    fun hideProgress() {
        loadingSplash.dismiss()
    }
}