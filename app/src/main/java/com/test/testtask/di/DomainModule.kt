package com.test.testtask.di

import com.test.testtask.domain.repository.AuthorizationRepository
import com.test.testtask.domain.repository.ImagesRepository
import com.test.testtask.domain.usecase.AuthorizationUseCase
import com.test.testtask.domain.usecase.ImagesUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideImagesUseCase(repository: ImagesRepository): ImagesUseCase = ImagesUseCase(repository)

    @Provides
    fun provideAuthorizationUseCase(repository: AuthorizationRepository): AuthorizationUseCase = AuthorizationUseCase(repository)
}