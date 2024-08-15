package com.example.tzexample.presentation.ui.auth

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.example.tzexample.R
import com.example.tzexample.presentation.base.BaseFlowFragment
import com.example.tzexample.presentation.extensions.backToFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlowAuthFragment:BaseFlowFragment(R.layout.fragment_flow_auth,R.id.fragment_auth_container) {


    override fun setupNavigation(navController: NavController, view: View) {
        super.setupNavigation(navController, view)
        NavigationUI.setupWithNavController(requireActivity().findViewById(R.id.toolbar),navController,null)
        navController.addOnDestinationChangedListener{_,destination,_ ->
            when(destination.id) {
                    R.id.singInFragment -> {
                       backToFlow(R.id.fragment_container_view)
                    }
                else -> {

                     }
                }
            }
    }
}