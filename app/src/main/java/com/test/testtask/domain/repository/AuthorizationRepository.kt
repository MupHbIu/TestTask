package com.test.testtask.domain.repository

import com.test.testtask.domain.entities.Weather
import io.reactivex.Single

interface AuthorizationRepository {

    fun authorize(id: Int, lang: String, units: String): Single<Weather>
}