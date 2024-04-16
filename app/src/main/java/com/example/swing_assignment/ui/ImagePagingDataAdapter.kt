package com.example.swing_assignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.swing_assignment.R
import com.example.swing_assignment.data.model.ImageDataModel
import com.example.swing_assignment.data.model.RetrofitDataModel
import com.example.swing_assignment.databinding.ImageItemBinding

class ImagePagingDataAdapter(private val onBookmarkClick: (ImageDataModel) -> Unit) : PagingDataAdapter<ImageDataModel, ImagePagingDataAdapter.PictureViewHolder>(object : DiffUtil.ItemCallback<ImageDataModel>() {
    override fun areItemsTheSame(oldItem: ImageDataModel, newItem: ImageDataModel): Boolean =
        oldItem.result.id == newItem.result.id

    override fun areContentsTheSame(oldItem: ImageDataModel, newItem: ImageDataModel): Boolean =
        oldItem == newItem

}
) {

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onBookmarkClick) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder(
            ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class PictureViewHolder(
        private val binding: ImageItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ImageDataModel, onBookmarkClick: (ImageDataModel) -> Unit) = with(binding) {
            ivImage.load(item.result.urls.regular)

            if (item.isLiked) btnLike.load(R.drawable.ic_heart_filled)
            else btnLike.load(R.drawable.ic_heart)

            btnLike.setOnClickListener {
                if (item.isLiked) btnLike.load(R.drawable.ic_heart)
                else btnLike.load(R.drawable.ic_heart_filled)
                onBookmarkClick (item)
            }
        }
    }

}

