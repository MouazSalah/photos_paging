package com.bosta.bostaapp.features.fragments.photos.domain

import com.bosta.bostaapp.core.network.MainRepository
import com.bosta.bostaapp.core.response.ErrorResponse
import com.bosta.bostaapp.core.response.NetworkResponse
import com.bosta.bostaapp.core.usecase.BaseUseCase
import com.bosta.bostaapp.features.fragments.photos.data.PhotoItem
import com.bosta.bostaapp.features.fragments.photos.data.PhotosParams
import com.bosta.bostaapp.features.fragments.photos.data.PhotosResponseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotosUseCase @Inject constructor(private val mainRepo: MainRepository) :
    BaseUseCase<List<PhotosResponseItem>, List<PhotoItem>, PhotosParams?>() {
    override fun mapper(req: List<PhotosResponseItem>): List<PhotoItem> =
        PhotosMapper.mapListData(req)

    override fun executeRemote(params: PhotosParams?): Flow<NetworkResponse<List<PhotosResponseItem>, ErrorResponse>> =
        flow {
            emit(mainRepo.getPhotos(params))
        }
}