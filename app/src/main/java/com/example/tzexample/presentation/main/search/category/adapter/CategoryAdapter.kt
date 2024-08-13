package com.example.tzexample.presentation.main.search.category.adapter

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
import com.example.tzexample.data.models.Announcement
import com.example.tzexample.databinding.ItemAnnouncedBinding

class CategoryAdapter(
    private val onItemClicked: (idCategory:Long,) -> Unit,
): ListAdapter<Announcement, CategoryAdapter.MedicineViewHolder>(FinishDiffUtil()) {


    class FinishDiffUtil : DiffUtil.ItemCallback<Announcement>(){
        override fun areItemsTheSame(oldItem: Announcement, newItem: Announcement): Boolean {
            return oldItem.idAnnouncement == newItem.idAnnouncement
        }

        override fun areContentsTheSame(oldItem: Announcement, newItem: Announcement): Boolean {
            return oldItem == newItem
        }
    }
    inner class MedicineViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemAnnouncedBinding.bind(view)
        fun bind(rubric: Announcement){
            binding.apply {
                titleAnnounced.text = rubric.name
                priceAnnounced.text = "${rubric.priceAnnouncement} с."
                Glide.with(binding.root)
                    .load(
                        if (rubric!!.imagesAnnouncement.isEmpty()){
                            ""
                        }else{
                           rubric.imagesAnnouncement.first().iconUrl
                        }
                    )
                    .transform(CenterCrop(), RoundedCorners(12))
                    .placeholder(R.drawable.error_image)
                    .into(iconAnnounced)
                root.setOnClickListener {
                    onItemClicked.invoke(rubric.idAnnouncement ?: 0)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder =
        MedicineViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_announced, parent, false)
        )

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}