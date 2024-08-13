package com.example.tzexample.presentation.ui.auth.sms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.tzexample.R
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
        view.findViewById<TextView>(R.id.otp_value).text = "+922 ${args.number}"
    }

}