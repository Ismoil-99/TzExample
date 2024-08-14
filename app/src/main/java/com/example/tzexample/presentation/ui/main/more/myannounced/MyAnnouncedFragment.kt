package com.example.tzexample.presentation.ui.main.more.myannounced

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tzexample.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyAnnouncedFragment : Fragment() {
    private val viewModel : MyAnnouncedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_announced, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            view.findViewById<RecyclerView>(R.id.list_my_announced).apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = MyAnnouncedAdapter()
            }
        viewModel.getAnnounced().observe(viewLifecycleOwner){ item ->
            (view.findViewById<RecyclerView>(R.id.list_my_announced).adapter as MyAnnouncedAdapter).submitList(item)
        }

    }

}