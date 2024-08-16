package com.example.tzexample.presentation.ui.main

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUiSaveStateControl
import androidx.navigation.ui.setupWithNavController
import com.example.tzexample.R
import com.example.tzexample.presentation.base.BaseFlowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlowMainFragment:BaseFlowFragment(R.layout.fragment_flow_main,R.id.fragment_main_container) {

    @OptIn(NavigationUiSaveStateControl::class)
    override fun setupNavigation(navController: NavController, view: View) {
        super.setupNavigation(navController, view)
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)
        NavigationUI.setupWithNavController(requireActivity().findViewById(R.id.toolbar),navController,null)
        NavigationUI.setupWithNavController(bottomNavigationView,navController,false)
        navController.addOnDestinationChangedListener{_,destination,_ ->
            when(destination.id) {
                R.id.AnnouncedFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                R.id.searchFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                R.id.categoryFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                R.id.showCategoryFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                R.id.settingsFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                R.id.myAnnouncedFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                R.id.myProfileFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                R.id.showMyAnnouncedFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                R.id.showFavoriteFragment ->{
                    bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }}
            }
    }
}