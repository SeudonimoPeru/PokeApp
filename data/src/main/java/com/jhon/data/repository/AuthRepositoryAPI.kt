package com.jhon.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthCredential
import com.jhon.data.http.AuthService
import com.jhon.data.http.ResponseState
import com.jhon.data.model.bean.user.User

class AuthRepositoryAPI {

    private val api by lazy { AuthService() }

    fun authentication(googleAuthCredential: AuthCredential): MutableLiveData<ResponseState<User>> {
        return api.firebaseSignInWithGoogle(googleAuthCredential)
    }

}