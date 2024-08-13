package com.example.tzexample.presentation.ui.main.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tzexample.R
import com.example.tzexample.databinding.FragmentAddBinding
import com.example.tzexample.presentation.extensions.activityNavController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddFragment : Fragment(R.layout.fragment_add) {
    private val binding by viewBinding(FragmentAddBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signIn.setOnClickListener {
            activityNavController().navigate(R.id.to_flow_auth)
        }
    }
}