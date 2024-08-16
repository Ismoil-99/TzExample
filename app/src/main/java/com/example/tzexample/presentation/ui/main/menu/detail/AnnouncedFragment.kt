package com.example.tzexample.presentation.ui.main.menu.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.tzexample.R
import com.example.tzexample.presentation.extensions.UIState
import com.example.tzexample.presentation.ui.main.menu.MenuViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AnnouncedFragment : Fragment() {

    private val args: AnnouncedFragmentArgs by navArgs()
    private val viewModel : MenuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_announced, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.showAnnounced(args.id).collectLatest { item ->
                when(item){
                    is UIState.Loading -> {
                        view.findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container).startShimmer()
                        view.findViewById<NestedScrollView>(R.id.scroll).visibility = View.GONE
                        view.findViewById<LinearLayout>(R.id.box_calling).visibility = View.GONE
                        view.findViewById<View>(R.id.view1).visibility = View.GONE
                    }
                    is UIState.Success -> {

                            view.findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container).apply {
                                stopShimmer()
                                visibility = View.GONE
                            }
                            view.findViewById<NestedScrollView>(R.id.scroll).visibility = View.VISIBLE
                            view.findViewById<LinearLayout>(R.id.box_calling).visibility = View.VISIBLE
                            view.findViewById<View>(R.id.view1).visibility = View.VISIBLE
                            val imageList = ArrayList<SlideModel>()
                            if (item.data != null){
                                if ( item.data.imagesAnnouncement.isNotEmpty()){
                                    item.data.imagesAnnouncement.map {image ->
                                        imageList.add(SlideModel(image.iconUrl))
                                    }
                                   view.findViewById<ImageSlider>(R.id.image_slider).setImageList(imageList,ScaleTypes.FIT)
                                }
                                view.findViewById<TextView>(R.id.title_announced).text = item.data.name
                                view.findViewById<TextView>(R.id.date_announced_text).text = "${getString(R.string.date_announced_text)} ${item.data.createdAnnouncement}"
                                if (item.data.priceAnnouncement != 0L){
                                    view.findViewById<TextView>(R.id.price_announced).text = "${item.data.priceAnnouncement} с."
                                }
                                view.findViewById<TextView>(R.id.content_announced).text =  item.data.contentAnnouncement
                                view.findViewById<MaterialButton>(R.id.call).setOnClickListener {
                                    val intent = Intent(Intent.ACTION_DIAL)
                                    val number = "000000001"
                                    intent.data = Uri.parse("tel: $number")
                                    startActivity(intent)
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

}