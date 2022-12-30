package com.jhon.apppokemon.features.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthCredential
import com.jhon.data.http.ResponseState
import com.jhon.data.model.bean.user.User
import com.jhon.domain.AuthUseCase



class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var authRepository = AuthUseCase()

    private var _authenticateUserLiveData: MutableLiveData<ResponseState<User>> = MutableLiveData()
    val authenticateUserLiveData: LiveData<ResponseState<User>> get() = _authenticateUserLiveData

    fun signInWithGoogle(googleAuthCredential: AuthCredential) {
        _authenticateUserLiveData = authRepository.repoAuth.authentication(googleAuthCredential)
    }

}
