package com.bosta.bostaapp.features.fragments.profile.ui

import com.bosta.bostaapp.base.Action
import com.bosta.bostaapp.features.fragments.profile.data.albums.AlbumItem

sealed class ProfileAction : Action {
    object OnBackButton : ProfileAction()
    data class UserDetails(val userDetail: com.bosta.bostaapp.features.fragments.profile.data.users.UserDetails) :
        ProfileAction()

    data class ListOfAlbums(val albumsList: List<AlbumItem>) : ProfileAction()
    data class ShowLoading(val show: Boolean) : ProfileAction()
    data class ShowMessage(val message: String) : ProfileAction()
}
