package com.bosta.bostaapp.features.fragments.photos.ui

import com.bosta.bostaapp.R
import com.bosta.bostaapp.databinding.ItemPhotoBinding
import com.bosta.bostaapp.features.fragments.photos.data.PhotoItem

class PhotosAdapter(private val itemClicked: (PhotoItem) -> Unit) : BasePagingAdapter<PhotoItem, ItemPhotoBinding>(itemClicked) {
    override fun layoutRes(): Int = R.layout.item_photo

    override fun onViewHolderCreated(viewHolder: BaseViewHolder<PhotoItem>) {
        super.onViewHolderCreated(viewHolder)
        mBinding.itemLayout.setOnClickListener {
            mPosition = viewHolder.bindingAdapterPosition
            getItem(mPosition)?.let { item -> itemClicked.invoke(item) }
        }
    }

    override fun onBindItem(item: PhotoItem, position: Int) {
        super.onBindItem(item, position)
    }
}