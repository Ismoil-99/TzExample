package com.example.tzexample.presentation.ui.auth.sms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tzexample.R
import com.example.tzexample.data.locale.preferences.PreferencesHelper
import com.example.tzexample.presentation.extensions.activityNavController
import com.example.tzexample.presentation.extensions.childCurrentBack


class SmsFragment : Fragment() {

    private val args: SmsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childCurrentBack(R.id.fragment_auth_container)
        val preferencesHelper = PreferencesHelper(requireContext())
        view.findViewById<TextView>(R.id.otp_value).text = "+922 ${args.number}"
        view.findViewById<Button>(R.id.send_data).setOnClickListener {
            preferencesHelper.setToken("123eresdf23resfdv23")
            preferencesHelper.setNumber(args.number)
            if (preferencesHelper.getBack() == 1){
                activityNavController().navigate(R.id.to_add_announced)
                preferencesHelper.setBackProfile(0)
            }else if (preferencesHelper.getBack() == 2){
                preferencesHelper.setBackProfile(0)
                activityNavController().navigateUp()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        val preferencesHelper = PreferencesHelper(requireContext())
        preferencesHelper.setBackProfile(0)
    }

}