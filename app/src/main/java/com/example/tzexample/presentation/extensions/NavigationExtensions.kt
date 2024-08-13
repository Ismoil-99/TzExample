package com.example.tzexample.presentation.extensions

import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.tzexample.R

fun NavController.navigateSafely(@IdRes actionId: Int) {
    currentDestination?.getAction(actionId)?.let { navigate(actionId) }
}

fun NavController.navigateSafely(directions: NavDirections) {
    currentDestination?.getAction(directions.actionId)?.let { navigate(directions) }
}
fun Fragment.activityNavController() = requireActivity().findNavController(R.id.fragment_container_view)


fun Fragment.backToFlow(@IdRes container:Int){
    val gets = requireActivity().supportFragmentManager.findFragmentById(container) as NavHostFragment
    val nav = gets.navController
    NavigationUI.setupWithNavController(requireActivity().findViewById(R.id.toolbar),nav,null)
}
fun Fragment.childCurrentBack(@IdRes container:Int){
    val navController =  Navigation.findNavController(requireActivity(),container)
    NavigationUI.setupWithNavController(requireActivity().findViewById(R.id.toolbar),navController,null)
}
fun Fragment.overrideOnBackPressed(onBackPressed: OnBackPressedCallback.() -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(this) {
        onBackPressed()
    }
}
