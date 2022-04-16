package com.bosta.bostaapp.core.network

import com.bosta.bostaapp.core.extension.toHashMapParams
import com.bosta.bostaapp.core.response.ErrorResponse
import com.bosta.bostaapp.core.response.NetworkResponse
import com.bosta.bostaapp.features.fragments.photos.data.PhotosParams
import com.bosta.bostaapp.features.fragments.photos.data.PhotosResponseItem
import com.bosta.bostaapp.features.fragments.profile.data.albums.AlbumsParams
import com.bosta.bostaapp.features.fragments.profile.data.albums.AlbumsResponseItem
import com.bosta.bostaapp.features.fragments.profile.data.users.UsersResponseItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllUsers(): NetworkResponse<List<UsersResponseItem>, ErrorResponse> =
        apiService.getAllUsers()

    suspend fun getAlbums(params: AlbumsParams?): NetworkResponse<List<AlbumsResponseItem>, ErrorResponse> =
        apiService.getAlbums(params.toHashMapParams()!!)

    suspend fun getPhotos(params: PhotosParams?): NetworkResponse<List<PhotosResponseItem>, ErrorResponse> =
        apiService.getPhotos(params.toHashMapParams()!!)
}