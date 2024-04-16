package com.example.swing_assignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.swing_assignment.R
import com.example.swing_assignment.data.model.BookmarkDataModel
import com.example.swing_assignment.data.model.ImageDataModel
import com.example.swing_assignment.data.model.RetrofitDataModel
import com.example.swing_assignment.databinding.ImageItemBinding

class ImageListAdapter (private val onBookmarkClick: (BookmarkDataModel) -> Unit) : ListAdapter<BookmarkDataModel, ImageListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<BookmarkDataModel>() {
        override fun areItemsTheSame(oldItem: BookmarkDataModel, newItem: BookmarkDataModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BookmarkDataModel, newItem: BookmarkDataModel): Boolean {
            return oldItem == newItem
        }
    }
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onBookmarkClick)
    }

    class ViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item : BookmarkDataModel, onBookmarkClick: (BookmarkDataModel) -> Unit) = with(binding) {
            ivImage.load(item.url)
            btnLike.load(R.drawable.ic_heart_filled)

            btnLike.setOnClickListener {
                onBookmarkClick
            }
        }
    }
}