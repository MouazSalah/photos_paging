package com.bosta.bostaapp.features.fragments.photos.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.bosta.bostaapp.base.ViewBindingVH
import com.bosta.bostaapp.databinding.ItemPhotoBinding
import com.bosta.bostaapp.features.fragments.photos.data.PhotoItem
import java.util.*

class PhotosAdapter(
    private val itemClick: (PhotoItem) -> Unit,
    private val isNoResult: (Boolean) -> Unit,
) : ListAdapter<PhotoItem, ViewBindingVH<ItemPhotoBinding>>(DiffUtils()) {
    var allItemsList = arrayListOf<PhotoItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewBindingVH<ItemPhotoBinding> {
        return ViewBindingVH.create(parent, ItemPhotoBinding::inflate)
    }

    override fun onBindViewHolder(holder: ViewBindingVH<ItemPhotoBinding>, position: Int) {
        val item = currentList[position]
        holder.binding.apply {
            imageView.load(item.url)
            itemLayout.setOnClickListener {
                itemClick.invoke(item)
            }
        }
    }

    class DiffUtils : DiffUtil.ItemCallback<PhotoItem>() {
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem.photoId == newItem.photoId
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem == newItem
        }
    }

    fun filterWithQuery(query: String) {
        if (query.isNotEmpty()) {
            val filteredList: List<PhotoItem> = onFilterChanged(query)
            submitList(filteredList)
            toggleRecyclerView(filteredList)
        } else if (query.isEmpty()) {
            submitList(allItemsList)
            isNoResult.invoke(false)
        }
    }

    private fun onFilterChanged(filterQuery: String): List<PhotoItem> {
        val filteredList = ArrayList<PhotoItem>()
        for (currentSport in allItemsList) {
            if (currentSport.photoTitle.lowercase(Locale.getDefault()).contains(filterQuery)) {
                filteredList.add(currentSport)
            }
        }
        return filteredList
    }

    private fun toggleRecyclerView(membersList: List<PhotoItem>) {
        if (membersList.isEmpty()) {
            isNoResult.invoke(true)
        } else {
            isNoResult.invoke(false)
        }
    }
}