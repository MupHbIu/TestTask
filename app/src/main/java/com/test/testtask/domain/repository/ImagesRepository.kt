package com.test.testtask.domain.repository

import com.test.testtask.domain.entities.Image
import io.reactivex.Single

interface ImagesRepository {

    fun getImages(): Single<List<Image>>
    fun getPage(page: Int): Single<List<Image>>
}