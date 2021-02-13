package com.test.testtask.presentation.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.test.testtask.MainApplication
import com.test.testtask.R
import kotlinx.android.synthetic.main.fragment_authorization.*
import javax.inject.Inject

class AuthorizationFragment : Fragment() {

    @Inject lateinit var viewModel: AuthorizationViewModel
    @Inject lateinit var loadDialog: LoadDialogFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        MainApplication.withViewModelOwner(this).inject(this)
        return inflater.inflate(R.layout.fragment_authorization, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        viewModel.message.observe(viewLifecycleOwner, {
            hideDialog()
            showMessage(it)
        })
        viewModel.error.observe(viewLifecycleOwner, {
            hideDialog()
            showMessage(it)
        })
    }

    private fun initViews() {
        imageView_logo.setImageResource(R.drawable.logo)
        loadDialog.isCancelable = false

        button_login.setOnClickListener {
            showDialog()
            viewModel.authorize(inputText_login.text.toString(), inputText_password.text.toString())
        }
    }

    private fun showDialog() { loadDialog.show(childFragmentManager, "") }

    private fun hideDialog() { loadDialog.dismiss() }

    private fun showMessage(message: String) {
        this.view?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }
}