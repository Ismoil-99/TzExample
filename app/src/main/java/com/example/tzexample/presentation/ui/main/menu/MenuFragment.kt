package com.example.tzexample.presentation.ui.main.menu

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tzexample.R
import com.example.tzexample.presentation.extensions.OrderLoadStateAdapter
import com.example.tzexample.presentation.extensions.UIState
import com.example.tzexample.presentation.extensions.hideActionBar
import com.example.tzexample.presentation.extensions.navigateSafely
import com.example.tzexample.presentation.extensions.showActionBar
import com.example.tzexample.presentation.ui.main.menu.adapter.AnnouncedAdapter
import com.example.tzexample.presentation.ui.main.menu.detail.AnnouncedFragment
import com.example.tzexample.presentation.ui.main.search.rubric.RubricFragmentDirections
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu) {
    private val viewModel : MenuViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_menu, container, false)

    }

    @SuppressLint("CutPasteId")
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AnnouncedAdapter(
            onInfoOrder = { id ->
                val direction = MenuFragmentDirections.actionMenuFragmentToAnnouncedFragment2("$id")
                findNavController().navigate(direction)
                          },
            saveFavorite = {fav ->
                lifecycleScope.launch (Dispatchers.IO){
                    viewModel.insertFavorite(fav)
                }
               Toast.makeText(requireContext(),getString(R.string.succsess_fav),Toast.LENGTH_SHORT).show()
            }
        )
        view.findViewById<RecyclerView>(R.id.announced_list).adapter = adapter
            .withLoadStateHeaderAndFooter(
                header = OrderLoadStateAdapter(),
                footer = OrderLoadStateAdapter()
            )
        lifecycleScope.launch{
            viewModel.getCountAnnounced().collectLatest { item ->
                when(item){
                    is UIState.Loading ->{}
                    is UIState.Success ->{
                        withContext(Dispatchers.Main){
                            view.findViewById<LinearLayout>(R.id.to_search).setOnClickListener {
                                val direction = RubricFragmentDirections.toSearchFromMain(item.data?.countAnnouncement ?: 0L)
                                findNavController().navigateSafely(direction)
                            }
                            view.findViewById<TextView>(R.id.count_text).text = "${item.data?.countAnnouncement} объявление"
                        }
                    }
                    is UIState.Error ->{}
                }
            }
            viewModel.listData.collectLatest{
                launch(Dispatchers.Main){
                    adapter.loadStateFlow.collectLatest { loadStates ->
                        if (loadStates.refresh is LoadState.Loading ){
                            view.findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container).apply {
                                visibility = View.VISIBLE
                                startShimmer()
                            }
                        }
                        else{
                            view.findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container).apply {
                                stopShimmer()
                                visibility = View.GONE
                            }
                            if (adapter.itemCount == 0){
                                view.findViewById<RecyclerView>(R.id.announced_list).visibility = View.GONE
                            }else{
                                view.findViewById<RecyclerView>(R.id.announced_list).visibility = View.VISIBLE
                            }
                        }
                    }
                }
                adapter.submitData(it)
            }
        }
        view.findViewById<RecyclerView>(R.id.announced_list).layoutManager = GridLayoutManager(requireContext(), 2)
    }

}