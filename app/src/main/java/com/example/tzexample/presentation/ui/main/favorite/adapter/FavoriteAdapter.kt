package com.example.tzexample.presentation.ui.main.favorite.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.tzexample.R
import com.example.tzexample.data.locale.db.AnnouncedDbModel
import com.example.tzexample.data.locale.db.FavoriteDbModel
import com.example.tzexample.databinding.ItemAnnouncedBinding
import com.example.tzexample.databinding.ItemFavoriteAnnouncedBinding

class FavoriteAdapter(private val onInfoAnnounced:(idOrder:String,) -> Unit,private val deleteFavAnnounce:(id:Int) -> Unit): ListAdapter<FavoriteDbModel, FavoriteAdapter.MedicineViewHolder>(FinishDiffUtil()) {


    class FinishDiffUtil : DiffUtil.ItemCallback<FavoriteDbModel>(){
        override fun areItemsTheSame(oldItem: FavoriteDbModel, newItem: FavoriteDbModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoriteDbModel, newItem: FavoriteDbModel): Boolean {
            return oldItem == newItem
        }
    }
    inner class MedicineViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemFavoriteAnnouncedBinding.bind(view)
        fun bind(rubric: FavoriteDbModel){
            binding.apply {
                nameAnnounced.text = rubric.nameFavoriteAnnounced
                priceAnnounced.text = "${rubric.priceFavoriteAnnounced} с."
                Glide.with(binding.root)
                    .load(rubric.imageFavoriteAnnounced)
                    .transform(CenterCrop(), RoundedCorners(12))
                    .placeholder(R.drawable.error_image)
                    .into(iconAnnouncedFavorite)
                root.setOnClickListener {
                    onInfoAnnounced.invoke(rubric.id.toString())
                }
                deleteFav.setOnClickListener {
                    deleteFavAnnounce.invoke(rubric.id)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder =
        MedicineViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_favorite_announced, parent, false)
        )

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}