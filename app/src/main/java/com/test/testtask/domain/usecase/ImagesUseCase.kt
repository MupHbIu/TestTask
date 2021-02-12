package com.test.testtask.domain.usecase

import com.test.testtask.domain.entities.Image
import com.test.testtask.domain.repository.ImagesRepository
import io.reactivex.Single

class ImagesUseCase(private val repository: ImagesRepository) {

    fun loadImages(): Single<List<Image>> = repository.getImages()
    fun loadNextPage(page: Int): Single<List<Image>> = repository.getPage(page)
}