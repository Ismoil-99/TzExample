package com.example.tzexample.presentation.ui.main.add

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        val preferencesHelper = PreferencesHelper(requireContext())
        preferencesHelper.setBackProfile(0)
        if (!preferencesHelper.isAuthored()){
            view.findViewById<TextView>(R.id.name_auth).visibility = View.VISIBLE
            view.findViewById<MaterialButton>(R.id.sign_in).visibility = View.VISIBLE
            view.findViewById<MaterialButton>(R.id.add_announced).visibility = View.GONE
        }else{
            view.findViewById<TextView>(R.id.name_auth).visibility = View.GONE
            view.findViewById<MaterialButton>(R.id.sign_in).visibility = View.GONE
            view.findViewById<MaterialButton>(R.id.add_announced).visibility = View.VISIBLE
        }
        view.findViewById<MaterialButton>(R.id.sign_in).setOnClickListener {
            preferencesHelper.setBackProfile(1)
            activityNavController().navigate(R.id.to_flow_auth)
        }
        view.findViewById<MaterialButton>(R.id.add_announced).setOnClickListener {
            activityNavController().navigate(R.id.to_add_announced)
        }
    }
}