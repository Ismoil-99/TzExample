package com.example.tzexample.presentation.ui.main.menu.adapter

import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.tzexample.R
import com.example.tzexample.data.locale.db.FavoriteDbModel
import com.example.tzexample.data.models.Announcement
import com.example.tzexample.databinding.ItemAnnouncedBinding
import com.example.tzexample.presentation.ui.main.search.rubric.adapter.RubricAdapter
import com.example.tzexample.presentation.ui.main.search.rubric.adapter.RubricAdapter.Companion

class AnnouncedAdapter(
    private val onInfoOrder:(idOrder:Long,) -> Unit,
    private val saveFavorite:(fav:FavoriteDbModel) -> Unit
    ) : PagingDataAdapter<Announcement, AnnouncedAdapter.ViewHolders>(
    DataDifferent
) {
    class ViewHolders(val binding: ItemAnnouncedBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        const val VIEW_TYPE = 2222
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE
    }

    override fun onBindViewHolder(holder: ViewHolders, position: Int) {
        with(holder) {
            binding.apply {
                titleAnnounced.text = getItem(position)?.name
                priceAnnounced.text = "${getItem(position)?.priceAnnouncement} с."
                Glide.with(binding.root)
                    .load(
                        if (getItem(position)!!.imagesAnnouncement.isEmpty()){
                            ""
                        }else{
                            getItem(position)!!.imagesAnnouncement.first().iconUrl
                        }
                        )
                    .transform(CenterCrop(),RoundedCorners(12))
                    .placeholder(R.drawable.error_image)
                    .into(iconAnnounced)
                root.setOnClickListener {
                    onInfoOrder.invoke(getItem(position)?.idAnnouncement ?: 0)
                }

                saveAnnounced.setOnClickListener {
                    val convert = FavoriteDbModel(
                        id = 0,
                        nameFavoriteAnnounced = getItem(position)?.name!!,
                        contentFavoriteAnnounced = getItem(position)?.contentAnnouncement!!,
                        priceFavoriteAnnounced = getItem(position)?.priceAnnouncement!!,
                        imageFavoriteAnnounced = if (getItem(position)!!.imagesAnnouncement.isEmpty()){
                            ""
                        }else{
                            getItem(position)!!.imagesAnnouncement.first().iconUrl
                        }
                    )
                    saveFavorite.invoke(convert)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolders {
        return ViewHolders(
            ItemAnnouncedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    private object DataDifferent : DiffUtil.ItemCallback<Announcement>() {
        override fun areContentsTheSame(oldItem: Announcement, newItem: Announcement): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areItemsTheSame(oldItem: Announcement, newItem: Announcement): Boolean {
            return oldItem == newItem
        }
    }
}