package com.example.tzexample.presentation.ui.main.more

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.navigation.fragment.findNavController
import com.example.tzexample.R
import com.example.tzexample.data.locale.preferences.PreferencesHelper
import com.example.tzexample.presentation.extensions.activityNavController


class MoreFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_more, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferences = PreferencesHelper(requireContext())
        preferences.setBackProfile(0)
        view.findViewById<RelativeLayout>(R.id.box_user).setOnClickListener {
            preferences.setBackProfile(2)
            if (preferences.isAuthored()){
                findNavController().navigate(R.id.to_profile)
            }else{
                activityNavController().navigate(R.id.to_flow_auth)
            }
        }
        view.findViewById<RelativeLayout>(R.id.box_announced).setOnClickListener {
            findNavController().navigate(R.id.to_my_announced)
        }
        view.findViewById<RelativeLayout>(R.id.box_settings).setOnClickListener {
            findNavController().navigate(R.id.to_settings)
        }
    }
}