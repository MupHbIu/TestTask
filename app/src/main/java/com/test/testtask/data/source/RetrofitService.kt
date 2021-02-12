package com.test.testtask.data.source

import com.test.testtask.domain.entities.Image
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("v2/list?page=1&limit=20")
    fun getImages(): Single<List<Image>>

    @GET("v2/list?limit=10")
    fun getPage(@Query("page") page: Int): Single<List<Image>>
}