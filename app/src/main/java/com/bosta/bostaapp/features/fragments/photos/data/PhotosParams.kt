package com.bosta.bostaapp.features.fragments.photos.data

import com.bosta.bostaapp.core.extension.HashMapParams
import com.google.gson.annotations.SerializedName

data class PhotosParams(
    @SerializedName("albumId")
    var albumId: Int,
) : HashMapParams {
    override fun dataClass(): Any = this
}