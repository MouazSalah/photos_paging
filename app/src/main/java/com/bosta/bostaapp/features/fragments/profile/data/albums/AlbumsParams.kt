package com.bosta.bostaapp.features.fragments.profile.data.albums

import com.bosta.bostaapp.core.extension.HashMapParams
import com.google.gson.annotations.SerializedName

data class AlbumsParams(
    @SerializedName("userId")
    var userId: Int,
) : HashMapParams {
    override fun dataClass(): Any = this
}
