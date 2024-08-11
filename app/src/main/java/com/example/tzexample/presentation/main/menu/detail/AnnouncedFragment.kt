package com.example.tzexample.presentation.main.menu.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.tzexample.R
import com.example.tzexample.databinding.FragmentAnnouncedBinding
import com.example.tzexample.databinding.FragmentMenuBinding
import com.example.tzexample.presentation.main.menu.MenuViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AnnouncedFragment : Fragment() {

    private var _binding: FragmentAnnouncedBinding? = null
    private val binding get() = _binding!!
    val args: AnnouncedFragmentArgs by navArgs()
    private val viewModel : MenuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnnouncedBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.showAnnounced(args.id).collectLatest { item ->
                Log.d("value","$item")
                binding.titleAnnounced.text = item.name
                binding.contentAnnounced.text = item.contentAnnouncement
                Glide.with(binding.root)
                    .load(
                        if (item.imagesAnnouncement.isEmpty()){
                            ""
                        }else{
                            item.imagesAnnouncement.first().iconUrl
                        }
                    )
                    .transform(CenterCrop(), RoundedCorners(12))
                    .placeholder(R.drawable.error_image)
                    .into(binding.showAnnounced)
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}