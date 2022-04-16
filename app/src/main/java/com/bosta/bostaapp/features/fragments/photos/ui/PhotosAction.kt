package com.bosta.bostaapp.features.fragments.photos.ui

import com.bosta.bostaapp.base.Action
import com.bosta.bostaapp.features.fragments.photos.data.PhotoItem

sealed class PhotosAction : Action {
    object OnBackButton : PhotosAction()
    data class PhotosList(val photosList: List<PhotoItem>) : PhotosAction()
    data class ShowLoading(val show: Boolean) : PhotosAction()
    data class ShowMessage(val message: String) : PhotosAction()
}
