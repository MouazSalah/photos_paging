package com.bosta.bostaapp.features.fragments.photos.data

import com.bosta.bostaapp.core.pagination.INITIAL_PAGE_INDEX
import com.google.gson.annotations.SerializedName
import com.bosta.bostaapp.core.pagination.PagingParams

data class PhotosParams(
    @SerializedName("type")
    var type: Int,
) : PagingParams() {
    override fun dataClass(): Any = this
    override var page: Int = INITIAL_PAGE_INDEX
}
