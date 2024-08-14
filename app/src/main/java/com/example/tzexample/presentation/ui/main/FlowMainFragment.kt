package com.example.tzexample.presentation.ui.main

import android.view.View
import androidx.annotation.AnyRes
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUiSaveStateControl
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tzexample.R
import com.example.tzexample.databinding.FragmentFlowMainBinding
import com.example.tzexample.presentation.base.BaseFlowFragment
import com.example.tzexample.presentation.extensions.hideActionBar
import com.example.tzexample.presentation.extensions.showActionBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlowMainFragment:BaseFlowFragment(R.layout.fragment_flow_main,R.id.fragment_main_container) {
    private val binding by viewBinding(FragmentFlowMainBinding::bind)

    @OptIn(NavigationUiSaveStateControl::class)
    override fun setupNavigation(navController: NavController) {
        super.setupNavigation(navController)
        binding.bottomNavigation.setupWithNavController(navController)
        NavigationUI.setupWithNavController(requireActivity().findViewById(R.id.toolbar),navController,null)
        NavigationUI.setupWithNavController(binding.bottomNavigation,navController,false)
        navController.addOnDestinationChangedListener{_,destination,_ ->
            when(destination.id) {
                R.id.AnnouncedFragment -> {
                    binding.bottomNavigation.visibility = View.GONE
                }
                R.id.searchFragment -> {
                    binding.bottomNavigation.visibility = View.GONE
                }
                R.id.categoryFragment -> {
                    binding.bottomNavigation.visibility = View.GONE
                }
                R.id.showCategoryFragment -> {
                    binding.bottomNavigation.visibility = View.GONE
                }
                R.id.settingsFragment -> {
                    binding.bottomNavigation.visibility = View.GONE
                }
                R.id.myAnnouncedFragment -> {
                    binding.bottomNavigation.visibility = View.GONE
                }
                R.id.myProfileFragment -> {
                    binding.bottomNavigation.visibility = View.GONE
                }
                else -> {
                    binding.bottomNavigation.visibility = View.VISIBLE
                }}
            }
    }
}