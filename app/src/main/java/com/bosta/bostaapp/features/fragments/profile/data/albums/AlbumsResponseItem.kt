package com.bosta.bostaapp.features.fragments.profile.data.albums

import com.google.gson.annotations.SerializedName

data class AlbumsResponseItem(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val albumName: String? = null,

    @field:SerializedName("userId")
    val userId: Int? = null,
)
