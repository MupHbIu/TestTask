package com.test.testtask

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.test.testtask.di.AppComponent
import com.test.testtask.di.DaggerAppComponent
import com.test.testtask.di.subcomponent.FragmentComponent

class MainApplication : Application() {
    companion object {
        lateinit var appContext: Context
        lateinit var appComponent: AppComponent

        fun withViewModelOwner(fragment: Fragment): FragmentComponent =
            appComponent.fragmentsComponentBuilder().with(fragment).build()

    }
    override fun onCreate() {
        super.onCreate()

        appContext = this
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.create()
    }

}