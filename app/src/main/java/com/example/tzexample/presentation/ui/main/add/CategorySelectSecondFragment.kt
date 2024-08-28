package com.example.tzexample.presentation.ui.main.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.tzexample.R
import com.example.tzexample.data.locale.preferences.PreferencesHelper
import com.example.tzexample.presentation.extensions.activityNavController
import com.example.tzexample.presentation.extensions.hideActionBar
import com.example.tzexample.presentation.ui.main.add.addannounced.AddAnnouncedViewModel
import com.example.tzexample.presentation.ui.main.menu.detail.AnnouncedFragmentArgs
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategorySelectSecondFragment : Fragment() {
    private val viewModel : AddAnnouncedViewModel by viewModels()
    private val args: CategorySelectSecondFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_select_second, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferencesHelper = PreferencesHelper(requireContext())
        preferencesHelper.setBackProfile(0)
       viewModel.showSelectCategory(args.id).map {category ->
           val newRowChild: View =
               layoutInflater.inflate(R.layout.list_category_select_second, null)
           newRowChild.findViewById<TextView>(R.id.text_category_select).text = category.name
           newRowChild.setOnClickListener {
               preferencesHelper.setTypeAnnounced(category.id)
               if (!preferencesHelper.isAuthored()){
                   preferencesHelper.setBackProfile(1)
                   activityNavController().navigate(R.id.to_flow_auth)
               }else{
                   activityNavController().navigate(R.id.to_add_announced)
               }
           }
           view.findViewById<LinearLayout>(R.id.list_category_select_second).addView(newRowChild)
       }
    }

    override fun onResume() {
        super.onResume()
        //requireActivity().findViewById<MaterialToolbar>(R.id.toolbar).visibility = View.GONE
    }
}