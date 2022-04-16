package com.bosta.bostaapp.core.network

import com.bosta.bostaapp.core.response.ErrorResponse
import com.bosta.bostaapp.core.response.NetworkResponse
import com.bosta.bostaapp.features.fragments.photos.data.PhotosResponseItem
import com.bosta.bostaapp.features.fragments.profile.data.albums.AlbumsResponseItem
import com.bosta.bostaapp.features.fragments.profile.data.users.UsersResponseItem
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    companion object {
        private const val USERS = "users"
        private const val ALBUMS = "albums"
        private const val PHOTOS = "photos"
    }

    @GET(USERS)
    suspend fun getAllUsers(): NetworkResponse<List<UsersResponseItem>, ErrorResponse>

    /*
    * The params of this Api Call can be implemented as Query only but I implement it with QueryMap to avoid more edition in the future
    * if Api need one extra params >> I will edit it in Use Case nd Repository and ViewModel ans so on ....
    * I Think this way is more clean
    * */
    @GET(ALBUMS)
    suspend fun getAlbums(@QueryMap param: HashMap<String, String?>): NetworkResponse<List<AlbumsResponseItem>, ErrorResponse>

    @GET(PHOTOS)
    suspend fun getPhotos(@QueryMap param: HashMap<String, String?>): NetworkResponse<List<PhotosResponseItem>, ErrorResponse>
}