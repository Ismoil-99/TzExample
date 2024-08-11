package com.example.tzexample.presentation.main.menu

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresExtension
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tzexample.R
import com.example.tzexample.databinding.FragmentMenuBinding
import com.example.tzexample.presentation.extensions.OrderLoadStateAdapter
import com.example.tzexample.presentation.extensions.navigateSafely
import com.example.tzexample.presentation.main.menu.adapter.AnnouncedAdapter
import com.example.tzexample.presentation.main.menu.detail.AnnouncedFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu) {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MenuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root

    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AnnouncedAdapter{ id, ->
            val direction = MenuFragmentDirections.actionMenuFragmentToAnnouncedFragment2("$id")
            findNavController().navigate(direction)
        }
        binding.announcedList.adapter = adapter
            .withLoadStateHeaderAndFooter(
                header = OrderLoadStateAdapter(),
                footer = OrderLoadStateAdapter()
            )
        lifecycleScope.launch{
            viewModel.listData.collectLatest{
                launch(Dispatchers.Main){
                    delay(500)
                    adapter.loadStateFlow.collectLatest { loadStates ->
                        if (loadStates.refresh is LoadState.Loading ){
                            binding.shimmerViewContainer.visibility  = View.VISIBLE
                            binding.shimmerViewContainer.startShimmer()
                        }
                        else{
                            binding.shimmerViewContainer.stopShimmer()
                            binding.shimmerViewContainer.visibility  = View.GONE
                            if (adapter.itemCount == 0){
                                binding.announcedList.visibility = View.GONE
                            }else{
                                binding.announcedList.visibility = View.VISIBLE
                            }
                        }
                    }
                }
                adapter.submitData(it)
            }
        }
        binding.announcedList.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}