package com.example.swing_assignment.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.swing_assignment.R
import com.example.swing_assignment.data.model.BookmarkDataModel
import com.example.swing_assignment.databinding.BookmarkFragmentBinding
import com.example.swing_assignment.databinding.FeedFragmentBinding

class BookmarkFragment : Fragment() {
    private var _binding: BookmarkFragmentBinding? = null
    private val binding get() = _binding!!

    private val listAdapter by lazy {
        ImageListAdapter(onBookmarkClick = {item ->
            sharedViewModel.deleteBookmarkForBookmark(item)
        })
    }

    private val sharedViewModel: ImageViewModel by activityViewModels {
        ImageViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BookmarkFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        val manager = GridLayoutManager(requireContext(), 2)

        rv.adapter = listAdapter
        rv.layoutManager = manager

    }

    private fun initViewModel() = with(sharedViewModel) {
        bookmarkList.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}