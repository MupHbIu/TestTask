package com.test.testtask.di

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.testtask.MainApplication
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideAppContext(): Context = MainApplication.appContext

    @Provides
    fun provideLinearLayoutManager(context: Context) : LinearLayoutManager = LinearLayoutManager(context)

}