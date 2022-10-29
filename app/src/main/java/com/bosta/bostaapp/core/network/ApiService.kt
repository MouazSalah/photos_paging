package com.bosta.bostaapp.core.network

import com.bosta.bostaapp.core.response.ErrorResponse
import com.bosta.bostaapp.core.response.NetworkResponse
import com.bosta.bostaapp.core.usecase.BaseResponse
import com.bosta.bostaapp.features.fragments.photos.data.PhotosResponseItem
import com.techlife.winwin.core.base.pagination.BasePagingResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    companion object {
        private const val PHOTOS = "photos"
    }

    @GET(PHOTOS)
    suspend fun getPhotos(@QueryMap param: HashMap<String, String?>): NetworkResponse<BaseResponse<BasePagingResponse<PhotosResponseItem>>, ErrorResponse>
}