package com.example.cryptocurrencytable.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.cryptocurrencytable.R
import com.google.android.material.snackbar.Snackbar

fun ViewGroup.inflater(layout: Int): View {
    return LayoutInflater.from(this.context).inflate(layout, this, false)
}

fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.transaction(f: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(FRAGMENT_CONTAINER, f)
        .addToBackStack(null)
        .commit()
}

fun AppCompatActivity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.createConnectionSnackBar(
    mainView: View,
    message: String,
    color: Int,
    duration: Int
) {
    val s = Snackbar.make(mainView, message, duration)
    s.view.setBackgroundResource(color)
    s.show()
}