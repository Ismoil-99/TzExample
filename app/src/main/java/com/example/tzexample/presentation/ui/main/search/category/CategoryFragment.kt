package com.example.tzexample.presentation.ui.main.search.category

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tzexample.R
import com.example.tzexample.presentation.extensions.UIState
import com.example.tzexample.presentation.ui.main.search.category.adapter.CategoryAdapter
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class CategoryFragment : Fragment() {
    private val args: CategoryFragmentArgs by navArgs()
    private val viewModel : CategoryViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar).title = args.title
        view.findViewById<RecyclerView>(R.id.show_announced).apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = CategoryAdapter{idAnnounced ->
                val direction = CategoryFragmentDirections.toAnnounced("$idAnnounced")
                findNavController().navigate(direction)
            }
        }
        lifecycleScope.launch {
            viewModel.categoryRubrics(args.id).collectLatest{state ->
                when(state){
                    is UIState.Loading ->{

                    }
                    is UIState.Success ->{
                        view.findViewById<GridLayout>(R.id.category).apply {
                            rowCount = state.data?.size ?: 0
                            columnCount = 2
                        }
                        withContext(Dispatchers.Main){
                            state.data?.map {category ->
                                val textView = TextView(requireContext())
                                textView.width = 350
                                textView.text = "${category.nameCategory}"
                                textView.setTextColor(Color.parseColor("#1A89C4"))
                                textView.textSize = 16f
                                val layoutParams = GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f))
                                layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                                layoutParams.width = 0
                                textView.setOnClickListener {
                                    val direction = CategoryFragmentDirections.showCategory(category.nameCategory)
                                    findNavController().navigate(direction)
                                }
                                view.findViewById<GridLayout>(R.id.category).addView(textView,layoutParams)

                            }
                        }
                    }
                    is UIState.Error ->{}
                }
            }
            viewModel.showAnnounced().collectLatest {state ->
                when(state){
                    is UIState.Loading ->{

                    }
                    is UIState.Success ->{
                        if (state.data != null)
                            (view.findViewById<RecyclerView>(R.id.show_announced).adapter as CategoryAdapter).submitList(state.data.announcement)
                    }
                    is UIState.Error -> {

                    }
                }
            }
        }
    }

}