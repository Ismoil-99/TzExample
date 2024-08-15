package com.example.tzexample.presentation.ui.main.more.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.tzexample.R
import com.example.tzexample.data.locale.preferences.PreferencesHelper
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MyProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferencesHelper = PreferencesHelper(requireContext())
        view.findViewById<TextView>(R.id.num_user).text = preferencesHelper.getTell()
        view.findViewById<RelativeLayout>(R.id.box_quit).setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage(resources.getString(R.string.quit_user_text))
                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->

                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    preferencesHelper.clearPreferences()
                    findNavController().navigateUp()
                }
                .show()
        }
    }
}