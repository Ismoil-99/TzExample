package com.example.tzexample.presentation.ui.auth.signin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tzexample.R
import com.example.tzexample.data.locale.preferences.PreferencesHelper
import com.example.tzexample.presentation.extensions.backToFlow
import com.example.tzexample.presentation.extensions.overrideOnBackPressed
import kotlinx.coroutines.launch


class SingInFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sing_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //backToFlow(R.id.fragment_main_container)
        view.findViewById<EditText>(R.id.send_otp_user)
            .apply {
                addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0?.length == 9 && p0.isNotEmpty()){
                        view.findViewById<Button>(R.id.sign_in).setOnClickListener {
                            val direction = SingInFragmentDirections.actionSingInFragmentToSmsFragment(p0.toString())
                            findNavController().navigate(direction)
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })
                doAfterTextChanged {
                    view.findViewById<Button>(R.id.sign_in).isEnabled = this.length() == 9
                }
            }


    }

}