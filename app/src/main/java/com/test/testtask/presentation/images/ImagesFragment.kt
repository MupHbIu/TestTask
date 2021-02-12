package com.test.testtask.presentation.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.testtask.MainApplication
import com.test.testtask.R
import com.test.testtask.domain.entities.Image
import kotlinx.android.synthetic.main.fragment_images.*
import javax.inject.Inject

class ImagesFragment : Fragment(), ImagesAdapter.ImagesAdapterListener {

    @Inject lateinit var viewModel: ImagesViewModel
    @Inject lateinit var adapter: ImagesAdapter
    @Inject lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        MainApplication.withViewModelOwner(this).inject(this)
        return inflater.inflate(R.layout.fragment_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        viewModel.loadImages()
        viewModel.lastLoadedImageList.observe(viewLifecycleOwner, Observer {
            adapter.removeLoading()
            adapter.addData(it)
            adapter.addLoading()
        })
    }

    private fun initViews() {
        recyclerView_images.layoutManager = linearLayoutManager
        recyclerView_images.adapter = adapter
        adapter.listener = this
        adapter.addLoading()
    }

    private fun checkLoaded(data: List<Image>): Boolean = data.isNotEmpty()

    override fun loadNextPage() = viewModel.loadNextPage()

}