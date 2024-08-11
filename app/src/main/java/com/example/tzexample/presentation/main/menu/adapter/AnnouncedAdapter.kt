package com.example.tzexample.presentation.main.menu.adapter

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
import com.example.tzexample.data.models.Announcement
import com.example.tzexample.databinding.ItemAnnouncedBinding

class AnnouncedAdapter(private val onInfoOrder:(idOrder:Long,) -> Unit ) : PagingDataAdapter<Announcement, AnnouncedAdapter.ViewHolders>(DataDifferent) {
    class ViewHolders(val binding: ItemAnnouncedBinding) : RecyclerView.ViewHolder(binding.root)


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