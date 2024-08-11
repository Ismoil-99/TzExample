package com.example.tzexample.presentation.main.menu.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.tzexample.R
import com.example.tzexample.databinding.FragmentAnnouncedBinding
import com.example.tzexample.databinding.FragmentMenuBinding
import com.example.tzexample.presentation.extensions.UIState
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
                when(item){
                    is UIState.Loading -> {
                        binding.apply {
                            shimmerViewContainer.startShimmer()
                            scroll.visibility = View.GONE
                            boxCalling.visibility = View.GONE
                            view1.visibility = View.GONE
                        }
                    }
                    is UIState.Success -> {
                        binding.apply {
                            shimmerViewContainer.stopShimmer()
                            shimmerViewContainer.visibility = View.GONE
                            scroll.visibility = View.VISIBLE
                            boxCalling.visibility = View.VISIBLE
                            view1.visibility = View.VISIBLE
                            val imageList = ArrayList<SlideModel>()
                            if (item.data != null){
                                if ( item.data.imagesAnnouncement.isNotEmpty()){
                                    item.data.imagesAnnouncement.map {image ->
                                        imageList.add(SlideModel(image.iconUrl))
                                    }
                                   imageSlider.setImageList(imageList,ScaleTypes.FIT)
                                }
                                titleAnnounced.text = item.data.name
                                if (item.data.priceAnnouncement != 0L){
                                    priceAnnounced.text = "${item.data.priceAnnouncement} с."
                                }
                                contentAnnounced.text = item.data.contentAnnouncement
                                call.setOnClickListener {
                                    val intent = Intent(Intent.ACTION_DIAL)
                                    val number = "000000001"
                                    intent.data = Uri.parse("tel: $number")
                                   startActivity(intent)
                                }
                            }
                        }
                    }
                    is UIState.Error -> {
                        Toast.makeText(requireContext(),R.string.error,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}