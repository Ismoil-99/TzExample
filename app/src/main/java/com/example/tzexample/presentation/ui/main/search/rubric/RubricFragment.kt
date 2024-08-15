package com.example.tzexample.presentation.ui.main.search.rubric

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tzexample.R
import com.example.tzexample.databinding.FragmentRubricsBinding
import com.example.tzexample.presentation.extensions.UIState
import com.example.tzexample.presentation.ui.main.search.category.CategoryFragmentDirections
import com.example.tzexample.presentation.ui.main.search.rubric.adapter.RubricAdapter
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RubricFragment : Fragment() {
    private val viewModel : RubricsViewModel by viewModels()
    private var _binding: FragmentRubricsBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar).visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_rubrics, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.rubrics).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RubricAdapter {idCategory,nameCategory ->
                val directions = CategoryFragmentDirections.toCategory(idCategory,nameCategory)
                findNavController().navigate(directions)

            }
        }
        lifecycleScope.launch {
            viewModel.rubrics().collectLatest {rubrics ->
                when(rubrics){
                    is UIState.Loading -> {
                        view.findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container).startShimmer()
                    }
                    is UIState.Success -> {
                        view.findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container).apply {
                            stopShimmer()
                            visibility = View.GONE
                        }
                        view.findViewById<RecyclerView>(R.id.rubrics).visibility = View.VISIBLE
                        (view.findViewById<RecyclerView>(R.id.rubrics).adapter as RubricAdapter).submitList(rubrics.data)
                    }
                    is UIState.Error ->{
                        Toast.makeText(requireContext(),R.string.error,Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar).visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar).visibility = View.VISIBLE
    }
}