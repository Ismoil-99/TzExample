package com.example.tzexample.presentation.main.menu

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresExtension
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tzexample.R
import com.example.tzexample.databinding.FragmentMenuBinding
import com.example.tzexample.presentation.extensions.OrderLoadStateAdapter
import com.example.tzexample.presentation.main.menu.adapter.AnnouncedAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu) {
    private val binding by viewBinding(FragmentMenuBinding::bind)
    private val viewModel : MenuViewModel by viewModels()

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AnnouncedAdapter{ id, title->

        }
        binding.annouucedList.adapter = adapter
            .withLoadStateHeaderAndFooter(
                header = OrderLoadStateAdapter(),
                footer = OrderLoadStateAdapter()
            )

        lifecycleScope.launch{
            viewModel.listData.collectLatest{
                launch(Dispatchers.Main){
                    adapter.loadStateFlow.collectLatest { loadStates ->
                        if (loadStates.refresh is LoadState.Loading ){
                            //binding.show.visibility  = View.VISIBLE
                            //binding.empty.visibility = View.GONE
                        }
                        else{
                            Log.d("value","$adapter")
                            //binding.show.visibility  = View.GONE
                            if (adapter.itemCount == 0){
//                                binding.listnews.visibility = View.GONE
//                                binding.empty.visibility = View.VISIBLE

                            }else{

                            }
                        }
                    }
                }
                adapter.submitData(it)
            }
        }
        binding.annouucedList.layoutManager = GridLayoutManager(requireContext(), 2)
    }
}