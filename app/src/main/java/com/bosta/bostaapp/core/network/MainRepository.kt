package com.bosta.bostaapp.core.network

import com.bosta.bostaapp.core.extension.toHashMapParams
import com.bosta.bostaapp.core.response.ErrorResponse
import com.bosta.bostaapp.core.response.NetworkResponse
import com.bosta.bostaapp.core.usecase.BaseResponse
import com.bosta.bostaapp.features.fragments.photos.data.PhotosParams
import com.bosta.bostaapp.features.fragments.photos.data.PhotosResponseItem
import com.techlife.winwin.core.base.pagination.BasePagingResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPhotos(params : PhotosParams?): NetworkResponse<BaseResponse<BasePagingResponse<PhotosResponseItem>>, ErrorResponse> =
        apiService.getPhotos(params.toHashMapParams()!!)
}