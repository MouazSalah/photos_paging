package com.bosta.bostaapp.features.fragments.profile.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.bosta.bostaapp.R
import com.bosta.bostaapp.base.BaseFragment
import com.bosta.bostaapp.core.extension.navigateSafe
import com.bosta.bostaapp.core.extension.observe
import com.bosta.bostaapp.databinding.FragmentProfileBinding
import com.bosta.bostaapp.features.fragments.profile.data.albums.AlbumItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate)
{
    private val mViewModel: ProfileViewModel by viewModels()
    override fun mPageTitle(): String = getString(R.string.title_profile)
    override fun mShowBackBtn(): Boolean = false
    private var adapter = AlbumsAdapter(::onAlbumClicked)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mViewModel.getAllUsers()

        observe(mViewModel.viewState) {
            when (it) {
                ProfileAction.OnBackButton -> closeFragment()
                is ProfileAction.ShowLoading -> showProgress(it.show)
                is ProfileAction.ShowMessage -> showToast(it.message)
                is ProfileAction.UserDetails -> {
                    binding.tvUserName.text = it.userDetail.name
                    binding.tvUserAddress.text = it.userDetail.address
                    mViewModel.getUserAlbums(it.userDetail.userId)
                }
                is ProfileAction.ListOfAlbums -> {
                    binding.rvAlbums.adapter = adapter
                    adapter.submitList(it.albumsList)
                }
            }
        }
    }

    private fun onAlbumClicked(item: AlbumItem) {
        navigateSafe(ProfileFragmentDirections.actionProfileFragmentToPhotosFragment(albumId = item.albumId,
            albumName = item.albumName))
    }
}
