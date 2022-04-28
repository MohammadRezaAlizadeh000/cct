package com.example.cryptocurrencytable.utils

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.transaction(f: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(FRAGMENT_CONTAINER, f)
        .addToBackStack(null)
        .commit()
}