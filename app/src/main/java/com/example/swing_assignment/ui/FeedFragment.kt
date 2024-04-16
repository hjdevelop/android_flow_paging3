package com.example.swing_assignment.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.swing_assignment.R
import com.example.swing_assignment.databinding.FeedFragmentBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FeedFragment : Fragment() {
    private var _binding: FeedFragmentBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel : ImageViewModel by activityViewModels {
        ImageViewModelFactory()
    }

    private val pagingDataAdapter by lazy {
        ImagePagingDataAdapter(
            onBookmarkClick = {item ->
                if (item.isLiked) {
                    sharedViewModel.deleteBookmarkForFeed(item)

                }
                else {
                    sharedViewModel.addBookmark(item)
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FeedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        val manager = GridLayoutManager(requireContext(), 2)

        rv.adapter = pagingDataAdapter
        rv.layoutManager = manager

        btnSearch.setOnClickListener {
            sharedViewModel.getImageList(edtSearch.text.toString())
        }
    }

    private fun initViewModel() = with(sharedViewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    imageList.collectLatest { image ->
                        pagingDataAdapter.submitData(image)
                    }
                }
            }
        }
        bookmarkList.observe(viewLifecycleOwner, Observer {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    launch {
                        imageList.collectLatest { image ->
                            pagingDataAdapter.submitData(image)
                        }
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}