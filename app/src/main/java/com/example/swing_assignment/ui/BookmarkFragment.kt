package com.example.swing_assignment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.swing_assignment.databinding.BookmarkFragmentBinding
import com.example.swing_assignment.ui.adapter.ImageListAdapter
import com.example.swing_assignment.ui.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment() {
    private var _binding: BookmarkFragmentBinding? = null
    private val binding get() = _binding!!

    private val listAdapter by lazy {
        ImageListAdapter(onBookmarkClick = {item ->
            sharedViewModel.deleteBookmarkForBookmark(item)
        })
    }

    private val sharedViewModel: ImageViewModel by activityViewModels()

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