package com.bosta.bostaapp.features.fragments.photos.data

import com.bosta.bostaapp.base.BaseParcelable

@kotlinx.parcelize.Parcelize
data class PhotoItem(
    var photoId: Int,
    var albumId: Int,
    var photoTitle: String,
    var url: String,
    var thumbnailUrl: String,
) : BaseParcelable {
    override fun unique(): Any = photoId
}