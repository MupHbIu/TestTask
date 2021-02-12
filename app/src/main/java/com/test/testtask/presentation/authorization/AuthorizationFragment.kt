package com.test.testtask.presentation.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.testtask.R

class AuthorizationFragment : Fragment() {

    private lateinit var dashboardViewModel: AuthorizationViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(AuthorizationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_authorization, container, false)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }
}