package com.test.testtask.presentation.authorization

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.testtask.MainApplication
import com.test.testtask.domain.usecase.AuthorizationUseCase
import com.test.testtask.util.ConstantsPrivate.CITY_ID
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthorizationViewModel : ViewModel() {

    @Inject lateinit var authorizationUseCase: AuthorizationUseCase

    init {
        MainApplication.appComponent.inject(this)
    }


    val message = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    fun authorize(login: String, password: String) {
        if(validateData(login, password))
            authorizationUseCase.authorize(CITY_ID, "ru", "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ weather ->
                    message.value = "Город: ${weather.name}. " +
                            "Температура: ${weather.main.temp}, " +
                            "облачность: ${weather.clouds.all}%, " +
                            "влажность: ${weather.main.humidity}%."
                }, { e ->
                    e.printStackTrace()
                    error.value = e.message
                })
    }

    private fun validateData(login: String, password: String): Boolean {
        val validateLogin: Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(login).matches()
        if(!validateLogin) {
            error.value = "Invalid email."
            return false
        }
        val validatePasswordError = authorizationUseCase.validateData(password)
        val validatePassword = validatePasswordError.isEmpty()
        if(!validatePassword) {
            error.value = validatePasswordError
            return false
        }
        return true
    }
}