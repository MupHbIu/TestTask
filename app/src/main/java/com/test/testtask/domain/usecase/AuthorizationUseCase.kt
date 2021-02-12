package com.test.testtask.domain.usecase

import com.test.testtask.domain.entities.Weather
import com.test.testtask.domain.repository.AuthorizationRepository
import io.reactivex.Single

class AuthorizationUseCase(private val repository: AuthorizationRepository) {
    companion object {
        private const val minSixSymbols = """......"""
        private const val minOneLowercaseLetter = """[a-z]"""
        private const val minOneUppercaseLetter = """[A-Z]"""
        private const val minOneNumber = """[0-9]"""

    }

    fun authorize(id: Int, lang: String, units: String): Single<Weather> = repository.authorize(id, lang, units)

    fun validateData(password: String): String = when {
        !password.contains(Regex(minSixSymbols)) -> "Password must contain at least 6 characters."
        !password.contains(Regex(minOneLowercaseLetter)) -> "Password must contain at least 1 lowercase letter."
        !password.contains(Regex(minOneUppercaseLetter)) -> "Password must contain at least 1 uppercase letter."
        !password.contains(Regex(minOneNumber)) -> "Password must contain at least 1 number."
        else -> ""
    }
}