package com.bosta.bostaapp.features.fragments.photos.domain

import androidx.paging.PagingSource
import com.bosta.bostaapp.core.network.MainRepository
import com.bosta.bostaapp.core.pagination.BasePagingUseCase
import com.bosta.bostaapp.features.fragments.photos.data.PhotoItem
import com.bosta.bostaapp.features.fragments.photos.data.PhotosParams
import com.bosta.bostaapp.features.fragments.photos.data.PhotosResponseItem
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class PhotosUseCase @Inject constructor(private val repo: MainRepository) : BasePagingUseCase<PhotosResponseItem, PhotoItem, PhotosParams>()
{
    override fun mapper(res: PhotosResponseItem) = PhotoItem(
        albumId = res.id ?: -1,
        url = res.url ?: "", // In Case of error will load Bosta Logo instead of it
        thumbnailUrl = res.thumbnailUrl ?: "",
        id = res.id ?: 0,
        title = res.title ?: "",
        viewType = 1,
    )

    override fun getPagingSource(params: PhotosParams?): PagingSource<Int, PhotosResponseItem> =
        PhotosSource(repo, params!!)
}