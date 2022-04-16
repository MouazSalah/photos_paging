package com.bosta.bostaapp.features.fragments.profile.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bosta.bostaapp.base.ViewBindingVH
import com.bosta.bostaapp.databinding.ItemAlbumBinding
import com.bosta.bostaapp.features.fragments.profile.data.albums.AlbumItem

class AlbumsAdapter(private val itemClick: (AlbumItem) -> Unit) :
    ListAdapter<AlbumItem, ViewBindingVH<ItemAlbumBinding>>(DiffUtils()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewBindingVH<ItemAlbumBinding> {
        return ViewBindingVH.create(parent, ItemAlbumBinding::inflate)
    }

    override fun onBindViewHolder(holder: ViewBindingVH<ItemAlbumBinding>, position: Int) {
        val item = currentList[position]
        holder.binding.apply {
            textAlbumTitle.text = item.albumName
            itemLayout.setOnClickListener {
                itemClick.invoke(item)
            }
        }
    }

    class DiffUtils : DiffUtil.ItemCallback<AlbumItem>() {
        override fun areItemsTheSame(oldItem: AlbumItem, newItem: AlbumItem): Boolean {
            return oldItem.albumId == newItem.albumId
        }

        override fun areContentsTheSame(oldItem: AlbumItem, newItem: AlbumItem): Boolean {
            return oldItem == newItem
        }
    }
}
