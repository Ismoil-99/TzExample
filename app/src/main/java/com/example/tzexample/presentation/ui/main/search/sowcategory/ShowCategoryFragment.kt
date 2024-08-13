package com.example.tzexample.presentation.ui.main.search.sowcategory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tzexample.R
import com.example.tzexample.presentation.extensions.UIState
import com.example.tzexample.presentation.ui.main.search.category.CategoryViewModel
import com.example.tzexample.presentation.ui.main.search.category.adapter.CategoryAdapter
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ShowCategoryFragment : Fragment() {
    private val args: ShowCategoryFragmentArgs by navArgs()
    private val viewModel : CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar).title = args.nameTitle
        view.findViewById<RecyclerView>(R.id.show_category).apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = CategoryAdapter { idAnnounced ->

            }
            lifecycleScope.launch {
                viewModel.showAnnounced().collectLatest { state ->
                    when (state) {
                        is UIState.Loading -> {

                        }

                        is UIState.Success -> {
                            if (state.data != null)
                                (view.findViewById<RecyclerView>(R.id.show_category).adapter as CategoryAdapter).submitList(
                                    state.data.announcement
                                )
                        }

                        is UIState.Error -> {

                        }
                    }
                }
            }

        }

    }}