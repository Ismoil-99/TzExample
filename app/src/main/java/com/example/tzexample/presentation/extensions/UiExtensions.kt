package com.example.tzexample.presentation.extensions

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun Fragment.hideActionBar() = (requireActivity() as AppCompatActivity).supportActionBar?.hide()
fun Fragment.showActionBar() = (requireActivity() as AppCompatActivity).supportActionBar?.show()
