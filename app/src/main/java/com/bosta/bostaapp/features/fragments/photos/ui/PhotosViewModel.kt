package com.bosta.bostaapp.features.fragments.photos.ui

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.bosta.bostaapp.base.AndroidBaseViewModel
import com.bosta.bostaapp.core.network.Resource
import com.bosta.bostaapp.features.fragments.photos.data.PhotosParams
import com.bosta.bostaapp.features.fragments.photos.domain.PhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    app: Application,
    private val photosUseCase: PhotosUseCase,
) : AndroidBaseViewModel<PhotosAction>(app) {
    fun getPhotos(albumId: Int) {
        photosUseCase.invoke(viewModelScope, PhotosParams(albumId = albumId)) { res ->
            when (res) {
                is Resource.Failure -> produce(PhotosAction.ShowMessage(res.message.toString()))
                is Resource.Progress -> produce(PhotosAction.ShowLoading(res.loading))
                is Resource.Success -> produce(PhotosAction.PhotosList(res.data))
            }
        }
    }
}