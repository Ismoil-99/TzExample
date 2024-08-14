package com.example.tzexample.presentation.ui.main.more

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.navigation.fragment.findNavController
import com.example.tzexample.R


class MoreFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_more, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RelativeLayout>(R.id.box_user).setOnClickListener {
            findNavController().navigate(R.id.to_profile)
        }
        view.findViewById<RelativeLayout>(R.id.box_announced).setOnClickListener {
            findNavController().navigate(R.id.to_my_announced)
        }
        view.findViewById<RelativeLayout>(R.id.box_settings).setOnClickListener {
            findNavController().navigate(R.id.to_settings)
        }
    }
}