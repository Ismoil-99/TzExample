package com.example.tzexample.presentation.ui.main.menu

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tzexample.R
import com.example.tzexample.data.locale.db.AnnouncedCountLocal
import com.example.tzexample.presentation.extensions.HorizontalWrapperAdapter
import com.example.tzexample.presentation.extensions.UIState
import com.example.tzexample.presentation.ui.main.menu.adapter.AnnouncedAdapter
import com.example.tzexample.presentation.ui.main.search.category.CategoryFragmentDirections
import com.example.tzexample.presentation.ui.main.search.rubric.adapter.RubricAdapter
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class MenuFragment : Fragment() {
    private val viewModel : MenuViewModel by viewModels()
    private lateinit var horizontalWrapperAdapter: HorizontalWrapperAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar).visibility = View.GONE
    }


    @SuppressLint("CutPasteId", "MissingInflatedId")
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterAnnounced = AnnouncedAdapter(
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
        val adaptersRubrics = RubricAdapter { idCategory,nameCategory ->
            val directions = CategoryFragmentDirections.toCategory(idCategory,nameCategory)
            findNavController().navigate(directions)
        }
        horizontalWrapperAdapter = HorizontalWrapperAdapter(adaptersRubrics)
        viewModel.getCount().observe(viewLifecycleOwner){count ->
            if (count.isEmpty()){
                lifecycleScope.launch {
                    viewModel.getCountAnnounced().collectLatest {count ->
                        when(count){
                            is UIState.Loading ->{}
                            is UIState.Success ->{
                                viewModel.insertDbCount(AnnouncedCountLocal(1,count.data!!.countAnnouncement.toString()))
                            }
                            is UIState.Error ->{}
                        }

                    }
                }
            }else{
                Log.d("value","value1")
                view.findViewById<TextView>(R.id.count_text).text = "${count[0].countAnnounced} обьявление"
            }
        }
        lifecycleScope.launch{
            viewModel.listData.collectLatest{
                launch(Dispatchers.Main){
                    adapterAnnounced.loadStateFlow.collectLatest { loadStates ->
                        view.findViewById<RecyclerView>(R.id.announced_list).visibility = View.VISIBLE
                    }
                }
                adapterAnnounced.submitData(it)
            }
        }
        lifecycleScope.launch {
            viewModel.rubrics().collectLatest { rubric ->
                when(rubric){
                    is UIState.Loading -> {

                    }
                    is UIState.Success -> {
                        if (rubric.data != null){
                            adaptersRubrics.submitList(rubric.data)
                        }
                        Log.d("rubrics","$rubric")
                    }
                    is UIState.Error ->{
                        Toast.makeText(requireContext(),R.string.error,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        val config = ConcatAdapter.Config.Builder().apply {
            setIsolateViewTypes(false)
        }.build()
        val concatAdapter = ConcatAdapter(config,horizontalWrapperAdapter,adapterAnnounced)
        val layoutManager = GridLayoutManager(requireContext(), 12)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (concatAdapter.getItemViewType(position)) {
                    AnnouncedAdapter.VIEW_TYPE -> 6
                    RubricAdapter.VIEW_TYPE -> 12
                    else -> 12
                }
            }
        }
        view.findViewById<RecyclerView>(R.id.announced_list).layoutManager = layoutManager
        view.findViewById<RecyclerView>( R.id.announced_list).adapter = concatAdapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
       horizontalWrapperAdapter.onSaveState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            horizontalWrapperAdapter.onSaveState(savedInstanceState)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar).visibility = View.VISIBLE
    }

}