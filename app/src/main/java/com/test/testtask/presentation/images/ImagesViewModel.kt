package com.test.testtask.presentation.images

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.testtask.MainApplication
import com.test.testtask.domain.entities.Image
import com.test.testtask.domain.usecase.ImagesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ImagesViewModel : ViewModel() {

    @Inject lateinit var imagesUseCase: ImagesUseCase

    init {
        MainApplication.appComponent.inject(this)
    }

    private var imageList = mutableListOf<Image>()
    private var currentPage = 0
    private var disposable: Disposable? = null
    val lastLoadedImageList = MutableLiveData<List<Image>>()
    val error = MutableLiveData<String>()

    fun loadImages() {
        disposable = if(currentPage == 0)
            loadImagesFirst()
        else
            loadImagesNext()
    }

    private fun loadImagesFirst(): Disposable  = imagesUseCase.loadImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            imageList = it.toMutableList()
                            lastLoadedImageList.value = it
                            currentPage = 1
                        },
                        { e ->
                            error.value = e.message
                            e.printStackTrace() }
                )
    private fun loadImagesNext(): Disposable = imagesUseCase.loadNextPage(currentPage+1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    {
                        imageList.addAll(it)
                        lastLoadedImageList.value = it
                        currentPage++
                    },
                    { e ->
                        error.value = e.message
                        e.printStackTrace()
                    }
            )

    fun clear() {
        disposable?.dispose()
        currentPage = 0
    }

}