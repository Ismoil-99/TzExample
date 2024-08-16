package com.example.tzexample.presentation.ui.main.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tzexample.R
import com.example.tzexample.presentation.ui.main.favorite.adapter.FavoriteAdapter
import com.example.tzexample.presentation.ui.main.more.myannounced.showannounced.ShowMyAnnouncedFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private val viewModel : FavoriteViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.item_recyclerview).apply {
            layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL ,false)
            adapter = FavoriteAdapter(
                onInfoAnnounced = {id ->
                    val direction = ShowMyAnnouncedFragmentDirections.toFavoriteShow(id)
                    findNavController().navigate(direction)
                },
                deleteFavAnnounce = {id ->
                    lifecycleScope.launch (Dispatchers.IO){
                        viewModel.deleteFavorite(id)
                    }
                }
            )
        }
        viewModel.getCountAnnouncedFavorite().observe(viewLifecycleOwner){ item ->

                if (item.isEmpty()){
                    view.findViewById<TextView>(R.id.text_favorite).visibility = View.VISIBLE
                    view.findViewById<RecyclerView>(R.id.item_recyclerview).visibility = View.GONE
                }else{
                    view.findViewById<TextView>(R.id.text_favorite).visibility = View.GONE
                    view.findViewById<RecyclerView>(R.id.item_recyclerview).visibility = View.VISIBLE
                    (view.findViewById<RecyclerView>(R.id.item_recyclerview).adapter as FavoriteAdapter).submitList(item)
                }
        }
    }
}