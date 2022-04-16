package com.bosta.bostaapp.features.fragments.profile.domain.albums

import com.bosta.bostaapp.core.mapper.BaseListMapper
import com.bosta.bostaapp.features.fragments.profile.data.albums.AlbumItem
import com.bosta.bostaapp.features.fragments.profile.data.albums.AlbumsResponseItem

object AlbumsMapper : BaseListMapper<List<AlbumsResponseItem>, AlbumsResponseItem, AlbumItem> {
    override fun mapListData(res: List<AlbumsResponseItem>): List<AlbumItem> = res.map {
        mapItem(it)
    }

    override fun mapItem(res: AlbumsResponseItem?): AlbumItem =
        AlbumItem(
            userId = res?.userId ?: -1,
            albumId = res?.id ?: -1,
            albumName = res?.albumName ?: "",
        )
}