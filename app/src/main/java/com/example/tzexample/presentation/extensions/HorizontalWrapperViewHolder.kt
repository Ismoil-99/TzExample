package com.example.tzexample.presentation.extensions

import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tzexample.databinding.ViewHorizontalWrapperBinding
import com.example.tzexample.presentation.ui.main.search.rubric.adapter.RubricAdapter

class HorizontalWrapperViewHolder(
    private val binding: ViewHorizontalWrapperBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(adapter: RubricAdapter, lastScrollX: Int, onScrolled: (Int) -> Unit) {
        val context = binding.root.context
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.doOnPreDraw {
            binding.recyclerView.scrollBy(lastScrollX, 0)
        }
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                onScrolled(recyclerView.computeHorizontalScrollOffset())
            }
        })
    }
}