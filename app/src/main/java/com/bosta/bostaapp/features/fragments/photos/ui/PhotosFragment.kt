package com.bosta.bostaapp.features.fragments.photos.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bosta.bostaapp.base.BaseFragment
import com.bosta.bostaapp.core.extension.afterTextChanged
import com.bosta.bostaapp.core.extension.navigateSafe
import com.bosta.bostaapp.core.extension.observe
import com.bosta.bostaapp.databinding.FragmentPhotosBinding
import com.bosta.bostaapp.features.fragments.photos.data.PhotoItem
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding>(FragmentPhotosBinding::inflate) {
    private val mViewModel: PhotosViewModel by viewModels()
    private val args by navArgs<PhotosFragmentArgs>()
    override fun mShowBackBtn(): Boolean = true
    override fun mPageTitle(): String = args.albumName
    private val adapter = PhotosAdapter(::onItemClicked, ::toggleRecyclerView)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.getPhotos(args.albumId)
        observe(mViewModel.viewState) {
            when (it) {
                PhotosAction.OnBackButton -> closeFragment()
                is PhotosAction.ShowLoading -> showProgress(it.show)
                is PhotosAction.ShowMessage -> showToast(it.message)
                is PhotosAction.PhotosList -> {
                    binding.rvPhotos.adapter = adapter
                    adapter.allItemsList = it.photosList as ArrayList<PhotoItem>
                    adapter.submitList(it.photosList)
                }
            }
        }

        binding.etSearch.afterTextChanged { text ->
            val query = text.lowercase(Locale.getDefault())
            if (query.isEmpty()) {
                binding.rvPhotos.smoothScrollToPosition(0)
            }
            adapter.filterWithQuery(query)
        }
    }

    private fun onItemClicked(item: PhotoItem) {
        navigateSafe(PhotosFragmentDirections.actionPhotosFragmentToPhotoViewFragment(item.thumbnailUrl))
    }

    private fun toggleRecyclerView(isNoResult: Boolean) {
        if (isNoResult) {
            binding.rvPhotos.visibility = View.GONE
            binding.tvNoResults.visibility = View.VISIBLE
        } else {
            binding.rvPhotos.visibility = View.VISIBLE
            binding.tvNoResults.visibility = View.GONE
        }
    }
}
