package com.test.testtask.presentation

import android.Manifest.permission.INTERNET
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.test.testtask.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigation()
        verifyStoragePermissions()
    }

    private fun initNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_images,
            R.id.navigation_authorization
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
    }

    private fun verifyStoragePermissions() {
        val permissions = arrayOf(INTERNET)
        val permission: Int = checkSelfPermission(INTERNET)

        if(permission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permissions, 1)
        }

    }
}