package com.example.tzexample.presentation.ui.main.add

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.tzexample.R
import com.example.tzexample.data.locale.preferences.PreferencesHelper
import com.example.tzexample.presentation.extensions.activityNavController
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<MaterialButton>(R.id.add_announced).setOnClickListener {
            findNavController().navigate(R.id.to_select_category)
        }
    }
}