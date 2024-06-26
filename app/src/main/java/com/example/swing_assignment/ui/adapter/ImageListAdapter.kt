package com.example.swing_assignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.swing_assignment.R
import com.example.swing_assignment.data.model.BookmarkDataModel
import com.example.swing_assignment.databinding.ImageItemBinding

//북마크 RecyclerView와 데이터를 연결하고 관리하기 위한 ListAdpater 입니다.
class ImageListAdapter (private val onBookmarkClick: (BookmarkDataModel) -> Unit) : ListAdapter<BookmarkDataModel, ImageListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<BookmarkDataModel>() {
        override fun areItemsTheSame(oldItem: BookmarkDataModel, newItem: BookmarkDataModel): Boolean {
            return oldItem.id == newItem.id
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
                onBookmarkClick(item)
            }
        }
    }
}