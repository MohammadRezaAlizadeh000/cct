package com.example.cryptocurrencytable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cryptocurrencytable.utils.transaction
import com.example.cryptocurrencytable.view.ExchangeListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transaction(ExchangeListFragment())
    }
}