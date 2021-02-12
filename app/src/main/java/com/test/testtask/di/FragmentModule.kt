package com.test.testtask.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.testtask.presentation.authorization.AuthorizationViewModel
import com.test.testtask.presentation.authorization.LoadDialogFragment
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

    @Provides
    fun provideAuthorizationFragmentViewModel(fragment: Fragment): AuthorizationViewModel =
            ViewModelProvider(fragment).get(AuthorizationViewModel::class.java)

    @Provides
    fun provideLoadDialogFragment(): LoadDialogFragment = LoadDialogFragment()
}