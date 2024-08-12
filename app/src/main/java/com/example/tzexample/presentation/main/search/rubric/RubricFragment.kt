package com.example.tzexample.presentation.main.search.rubric

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
import com.example.tzexample.R
import com.example.tzexample.databinding.FragmentRubricsBinding
import com.example.tzexample.presentation.extensions.UIState
import com.example.tzexample.presentation.main.search.category.CategoryFragmentDirections
import com.example.tzexample.presentation.main.search.rubric.adapter.RubricAdapter
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RubricFragment : Fragment(R.layout.fragment_rubrics) {
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
        _binding = FragmentRubricsBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rubrics.apply {
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
                        binding.shimmerViewContainer.startShimmer()
                        Log.d("rubrics","$rubrics")
                    }
                    is UIState.Success -> {
                        binding.shimmerViewContainer.stopShimmer()
                        binding.shimmerViewContainer.visibility = View.GONE
                        binding.rubrics.visibility = View.VISIBLE
                        (binding.rubrics.adapter as RubricAdapter).submitList(rubrics.data)
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