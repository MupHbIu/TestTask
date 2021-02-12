package com.test.testtask.data.repository

import com.test.testtask.data.source.RetrofitService
import com.test.testtask.domain.entities.Image
import com.test.testtask.domain.repository.ImagesRepository
import io.reactivex.Single

class NetworkImagesRepository(private val retrofitService: RetrofitService): ImagesRepository {

    override fun getImages(): Single<List<Image>> = retrofitService.getImages()
    override fun getPage(page: Int): Single<List<Image>> = retrofitService.getPage(page)

}