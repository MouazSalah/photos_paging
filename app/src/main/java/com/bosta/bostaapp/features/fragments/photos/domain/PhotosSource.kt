package com.bosta.bostaapp.features.fragments.photos.domain

import com.bosta.bostaapp.core.network.MainRepository
import com.bosta.bostaapp.core.pagination.BasePagingDataSource
import com.techlife.winwin.core.base.pagination.BasePagingResponse
import com.bosta.bostaapp.core.pagination.PagingParams
import com.bosta.bostaapp.core.response.ErrorResponse
import com.bosta.bostaapp.core.response.NetworkResponse
import com.bosta.bostaapp.core.usecase.BaseResponse
import com.bosta.bostaapp.features.fragments.photos.data.PhotosParams
import com.bosta.bostaapp.features.fragments.photos.data.PhotosResponseItem

class PhotosSource(
    private val homeRepo: MainRepository,
    private val params: PhotosParams
) : BasePagingDataSource<PhotosResponseItem>() {

    override val queryParams: PagingParams = params
    override suspend fun execute(): NetworkResponse<BaseResponse<BasePagingResponse<PhotosResponseItem>>, ErrorResponse> {
        return homeRepo.getPhotos(params)
    }
}