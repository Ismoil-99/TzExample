package com.example.tzexample.presentation.ui.main.more.myannounced

import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tzexample.R
import com.example.tzexample.data.locale.db.AnnouncedDbModel
import com.example.tzexample.databinding.ItemAnnouncedBinding
import com.example.tzexample.databinding.ItemAnnouncedMyBinding

class MyAnnouncedAdapter(private val onInfoAnnounced:(idOrder:String,) -> Unit): ListAdapter<AnnouncedDbModel, MyAnnouncedAdapter.MedicineViewHolder>(FinishDiffUtil()) {


    class FinishDiffUtil : DiffUtil.ItemCallback<AnnouncedDbModel>(){
        override fun areItemsTheSame(oldItem: AnnouncedDbModel, newItem: AnnouncedDbModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AnnouncedDbModel, newItem: AnnouncedDbModel): Boolean {
            return oldItem == newItem
        }
    }
    inner class MedicineViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemAnnouncedMyBinding.bind(view)
        fun bind(rubric: AnnouncedDbModel){
            binding.apply {
                titleAnnounced.text = rubric.nameAnnounced
                priceAnnounced.text = "${rubric.priceAnnounced} с."
                Log.d("im","${rubric.imageAnnounced}")
                val bitmap = BitmapFactory.decodeByteArray(
                    rubric.imageAnnounced,0,rubric.imageAnnounced.size
                )
                iconAnnounced.setImageBitmap(bitmap)

                root.setOnClickListener {
                    onInfoAnnounced.invoke(rubric.id.toString())
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder =
        MedicineViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_announced_my, parent, false)
        )

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}