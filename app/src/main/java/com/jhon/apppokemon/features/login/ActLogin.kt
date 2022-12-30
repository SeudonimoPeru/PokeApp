package com.jhon.apppokemon.features.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.jhon.apppokemon.activity.PrincipalActivity
import com.jhon.apppokemon.databinding.ActivityLoginBinding
import com.jhon.apppokemon.features.main.ActMain
import com.jhon.data.http.ResponseState

class ActLogin : PrincipalActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var googleSignInClient: GoogleSignInClient
    private val loginViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }
    private val RC_SIGN_IN = 100
    private var idToken: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnLogin.setOnClickListener {
                initGoogleSignInClient()
            }
        }
    }


    private fun initGoogleSignInClient() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(com.firebase.ui.auth.R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        startActivityForResult(googleSignInClient.signInIntent, RC_SIGN_IN)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            showProgress()
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                getGoogleAuthCredential(account)


            } catch (e: ApiException) {
                Log.i(TAG, "onActivityResult: ${e.printStackTrace()}")
            }
        }
    }

    private fun getGoogleAuthCredential(account: GoogleSignInAccount) {

        val googleTokeId = account.idToken
        idToken = account.idToken
        val googleAuthCredential = GoogleAuthProvider.getCredential(googleTokeId, null)
        signInWithGoogleAuthCredential(googleAuthCredential)
    }

    private fun signInWithGoogleAuthCredential(googleAuthCredential: AuthCredential) {

        loginViewModel.signInWithGoogle(googleAuthCredential)
        loginViewModel.authenticateUserLiveData.observe(this) { authenticatedUser ->
            hideProgress()
            when (authenticatedUser) {

                is ResponseState.Error -> {
                    authenticatedUser.message?.let {
                        Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()
                    }
                }
                is ResponseState.Success -> {
                    if (authenticatedUser.data != null) {

                        goToMain()
                    }

                }
                is ResponseState.Loading -> {
                    //show progress
                }
            }
        }

    }


    private fun goToMain() {
        val intentGotoMenu = Intent(applicationContext, ActMain::class.java);
        startActivity(intentGotoMenu)
        finish()
    }


}
