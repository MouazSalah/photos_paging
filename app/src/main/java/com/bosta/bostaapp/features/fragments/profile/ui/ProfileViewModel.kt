package com.bosta.bostaapp.features.fragments.profile.ui

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.bosta.bostaapp.base.AndroidBaseViewModel
import com.bosta.bostaapp.core.network.Resource
import com.bosta.bostaapp.features.fragments.profile.data.albums.AlbumsParams
import com.bosta.bostaapp.features.fragments.profile.data.users.UserDetails
import com.bosta.bostaapp.features.fragments.profile.domain.albums.AlbumsUseCase
import com.bosta.bostaapp.features.fragments.profile.domain.users.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ProfileViewModel @Inject constructor(
    app: Application,
    private val usersUseCase: UsersUseCase,
    private val albumsUseCase: AlbumsUseCase,
) : AndroidBaseViewModel<ProfileAction>(app) {

    fun getAllUsers() {
        usersUseCase.invoke(viewModelScope) { res ->
            when (res) {
                is Resource.Failure -> produce(ProfileAction.ShowMessage(res.message.toString()))
                is Resource.Progress -> produce(ProfileAction.ShowLoading(res.loading))
                is Resource.Success -> {
                    getRandomUserFromList(res.data)
                }
            }
        }
    }

    private fun getRandomUserFromList(usersList: List<UserDetails>) {
        val randomIndex = Random.nextInt(usersList.size);
        val randomUser = usersList[randomIndex]
        produce(ProfileAction.UserDetails(randomUser))
    }

    fun getUserAlbums(userId: Int) {
        albumsUseCase.invoke(viewModelScope, AlbumsParams(userId = userId)) { res ->
            when (res) {
                is Resource.Failure -> produce(ProfileAction.ShowMessage(res.message.toString()))
                is Resource.Progress -> produce(ProfileAction.ShowLoading(res.loading))
                is Resource.Success -> produce(ProfileAction.ListOfAlbums(res.data))
            }
        }
    }
}