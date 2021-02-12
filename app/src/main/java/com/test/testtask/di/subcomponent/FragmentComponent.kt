package com.test.testtask.di.subcomponent

import androidx.fragment.app.Fragment
import com.test.testtask.di.FragmentModule
import com.test.testtask.presentation.authorization.AuthorizationFragment
import com.test.testtask.presentation.images.ImagesFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun with(fragment: Fragment): Builder

        fun build(): FragmentComponent
    }

    fun inject(fragment: ImagesFragment)
    fun inject(fragment: AuthorizationFragment)
}