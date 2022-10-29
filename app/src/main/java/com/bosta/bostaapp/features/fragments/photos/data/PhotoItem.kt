package com.bosta.bostaapp.features.fragments.photos.data

import com.bosta.bostaapp.features.fragments.photos.ui.BasePaginationParcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoItem (
    val albumId : Int,
    val id : Int,
    val title : String,
    val url : String,
    val thumbnailUrl : String,
    override var viewType: Int
) : BasePaginationParcelable {
    override fun unique(): Any = id
}