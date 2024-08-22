package com.example.tzexample.presentation.ui.main.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tzexample.R
import com.example.tzexample.data.locale.preferences.PreferencesHelper
import com.example.tzexample.presentation.extensions.activityNavController
import com.example.tzexample.presentation.ui.main.add.addannounced.AddAnnouncedViewModel
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategorySelectFragment : Fragment() {
    private val viewModel : AddAnnouncedViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_select, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            viewModel.showCategories().map { category ->
                val newRowChild =
                    layoutInflater.inflate(R.layout.list_category_select, null)
                newRowChild.findViewById<TextView>(R.id.text_category_select).text = category.nameCategory
                newRowChild.setOnClickListener {
                    val directions = CategorySelectFragmentDirections.toSelectCategorySecond(category.id)
                    findNavController().navigate(directions)
                }
                view.findViewById<LinearLayout>(R.id.list_category_select).addView(newRowChild)
            }

    }


}