package com.bosta.bostaapp.features.fragments.photos.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.bosta.bostaapp.base.AndroidBaseViewModel
import com.bosta.bostaapp.core.network.Resource
import com.bosta.bostaapp.features.fragments.photos.data.PhotoItem
import com.bosta.bostaapp.features.fragments.photos.data.PhotosParams
import com.bosta.bostaapp.features.fragments.photos.domain.PhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    app: Application,
    private val photosUseCase: PhotosUseCase,
) : AndroidBaseViewModel<PhotosAction>(app) {

    var params = PhotosParams(type = 1)
    private var adapter = PhotosAdapter(::onItemClicked)

    fun getPhotos() {
        photosUseCase.invoke(viewModelScope, params) { data ->
            viewModelScope.launch {
                adapter.submitData(data)
            }
        }
    }
    private fun onItemClicked(item : PhotoItem){
        viewModelScope.launch {
            produce(PhotosAction.PhotoItemClicked(item))
        }
    }
}