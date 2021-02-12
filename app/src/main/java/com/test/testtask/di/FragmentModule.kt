package com.test.testtask.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.testtask.presentation.images.ImagesAdapter
import com.test.testtask.presentation.images.ImagesViewModel
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideImagesAdapter(): ImagesAdapter = ImagesAdapter()

    @Provides
    fun provideImagesFragmentViewModel(fragment: Fragment): ImagesViewModel =
        ViewModelProvider(fragment).get(ImagesViewModel::class.java)
}