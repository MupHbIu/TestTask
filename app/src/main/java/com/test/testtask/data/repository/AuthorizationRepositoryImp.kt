package com.test.testtask.data.repository

import com.test.testtask.data.source.RetrofitServiceAuthorization
import com.test.testtask.domain.entities.Weather
import com.test.testtask.domain.repository.AuthorizationRepository
import io.reactivex.Single

class AuthorizationRepositoryImp(private val retrofitAuthorizationService: RetrofitServiceAuthorization): AuthorizationRepository {

    override fun authorize(id: Int, lang: String, units: String): Single<Weather> =
            retrofitAuthorizationService.authorization(id, lang, units)
}