package com.example.tzexample.presentation.ui.main.search.rubric.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tzexample.R
import com.example.tzexample.data.models.Rubrics
import com.example.tzexample.databinding.ListRubricBinding

class RubricAdapter(
    private val onItemClicked: (idCategory:String,nameCategory:String) -> Unit,
): ListAdapter<Rubrics, RubricAdapter.MedicineViewHolder>(FinishDiffUtil()) {

    companion object {
        const val VIEW_TYPE = 4444
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE
    }

    class FinishDiffUtil : DiffUtil.ItemCallback<Rubrics>(){
        override fun areItemsTheSame(oldItem: Rubrics, newItem: Rubrics): Boolean {
            return oldItem.idRubrics == newItem.idRubrics
        }

        override fun areContentsTheSame(oldItem: Rubrics, newItem: Rubrics): Boolean {
            return oldItem == newItem
        }
    }
    inner class MedicineViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListRubricBinding.bind(view)
        fun bind(rubric: Rubrics){
          binding.apply {
              Glide.with(binding.root)
                  .load(rubric.imgRubric)
                  .override(60,60)
                  .error(R.drawable.error_image)
                  .placeholder(R.drawable.error_image)
                  .into(binding.iconsRubric)
              nameRubric.text = rubric.nameRubric
          }
        }
        init {
            binding.root.setOnClickListener {
                onItemClicked.invoke("${getItem(absoluteAdapterPosition).idRubrics}",getItem(absoluteAdapterPosition).nameRubric)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder =
        MedicineViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_rubric, parent, false)
        )

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}