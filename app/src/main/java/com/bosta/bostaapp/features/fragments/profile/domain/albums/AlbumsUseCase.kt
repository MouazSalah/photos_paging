package com.bosta.bostaapp.features.fragments.profile.domain.albums

import com.bosta.bostaapp.core.network.MainRepository
import com.bosta.bostaapp.core.response.ErrorResponse
import com.bosta.bostaapp.core.response.NetworkResponse
import com.bosta.bostaapp.core.usecase.BaseUseCase
import com.bosta.bostaapp.features.fragments.profile.data.albums.AlbumItem
import com.bosta.bostaapp.features.fragments.profile.data.albums.AlbumsParams
import com.bosta.bostaapp.features.fragments.profile.data.albums.AlbumsResponseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumsUseCase @Inject constructor(private val mainRepo: MainRepository) :
    BaseUseCase<List<AlbumsResponseItem>, List<AlbumItem>, AlbumsParams?>() {
    override fun mapper(req: List<AlbumsResponseItem>): List<AlbumItem> =
        AlbumsMapper.mapListData(req)

    override fun executeRemote(params: AlbumsParams?): Flow<NetworkResponse<List<AlbumsResponseItem>, ErrorResponse>> =
        flow {
            emit(mainRepo.getAlbums(params))
        }
}