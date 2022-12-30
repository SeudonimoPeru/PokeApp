package com.jhon.apppokemon.features.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jhon.apppokemon.R
import com.jhon.apppokemon.features.login.ActLogin


class ActSplash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashScreen.setKeepOnScreenCondition { true }
        gotoLogin()

    }

    private fun gotoLogin() {
        val intentGotoMenu = Intent(applicationContext, ActLogin::class.java);
        startActivity(intentGotoMenu)
        finish()
    }
}