package com.bosta.bostaapp.features.fragments.profile.data.albums

import com.bosta.bostaapp.base.BaseParcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumItem(
    var userId: Int,
    var albumId: Int,
    var albumName: String,
) : BaseParcelable {
    override fun unique(): Any = albumId
}