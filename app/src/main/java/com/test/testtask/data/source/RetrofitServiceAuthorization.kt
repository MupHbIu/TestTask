package com.test.testtask.data.source

import com.test.testtask.domain.entities.Weather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServiceAuthorization {

    @GET("data/2.5/weather")
    fun authorization(
            @Query("id") id: Int,
            @Query("lang") lang: String,
            @Query("units") units: String
    ): Single<Weather>

}