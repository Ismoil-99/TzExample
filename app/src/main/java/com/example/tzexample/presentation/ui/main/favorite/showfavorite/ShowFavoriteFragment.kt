package com.example.tzexample.presentation.ui.main.favorite.showfavorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.tzexample.R
import com.example.tzexample.presentation.ui.main.add.addannounced.AddAnnouncedViewModel
import com.example.tzexample.presentation.ui.main.favorite.FavoriteViewModel
import com.example.tzexample.presentation.ui.main.menu.detail.AnnouncedFragmentArgs
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShowFavoriteFragment : Fragment() {
    private val viewModel : FavoriteViewModel by viewModels()
    private val args: ShowFavoriteFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.showAnnouncedFavorite(args.id).observe(viewLifecycleOwner){item ->
            view.findViewById<TextView>(R.id.price_announced).text = "${item.priceFavoriteAnnounced} с."
            view.findViewById<TextView>(R.id.title_announced).text = item.nameFavoriteAnnounced
            view.findViewById<TextView>(R.id.content_announced).text = item.contentFavoriteAnnounced
            Glide.with(requireContext())
                .load(
                   item.imageFavoriteAnnounced
                )
                .transform(CenterCrop(), RoundedCorners(12))
                .placeholder(R.drawable.error_image)
                .into(view.findViewById(R.id.image_slider))
        }
    }
}