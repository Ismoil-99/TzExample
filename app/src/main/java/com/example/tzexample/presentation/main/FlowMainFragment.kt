package com.example.tzexample.presentation.main

import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUiSaveStateControl
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tzexample.R
import com.example.tzexample.databinding.FragmentFlowMainBinding
import com.example.tzexample.presentation.base.BaseFlowFragment

class FlowMainFragment:BaseFlowFragment(R.layout.fragment_flow_main,R.id.fragment_main_container) {
    private val binding by viewBinding(FragmentFlowMainBinding::bind)

    @OptIn(NavigationUiSaveStateControl::class)
    override fun setupNavigation(navController: NavController) {
        super.setupNavigation(navController)
        binding.bottomNavigation.setupWithNavController(navController)
        NavigationUI.setupWithNavController(requireActivity().findViewById(R.id.toolbar),navController,null)
        NavigationUI.setupWithNavController(binding.bottomNavigation,navController,false)
    }
}