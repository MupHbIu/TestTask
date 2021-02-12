package com.test.testtask.di

import com.test.testtask.di.subcomponent.FragmentComponent
import com.test.testtask.presentation.authorization.AuthorizationViewModel
import com.test.testtask.presentation.images.ImagesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, PresentationModule::class, DomainModule::class])
interface AppComponent {

    fun fragmentsComponentBuilder(): FragmentComponent.Builder

    fun inject(viewModel: ImagesViewModel)
    fun inject(viewModel: AuthorizationViewModel)


}