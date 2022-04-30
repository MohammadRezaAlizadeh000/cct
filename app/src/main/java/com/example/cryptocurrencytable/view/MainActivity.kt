package com.example.cryptocurrencytable.view

import android.content.Context
import android.content.Intent
import android.net.*
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.cryptocurrencytable.R
import com.example.cryptocurrencytable.utils.createConnectionSnackBar
import com.example.cryptocurrencytable.utils.toast
import com.example.cryptocurrencytable.utils.transaction
import com.example.cryptocurrencytable.view.exchangeslist.ExchangeListFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var coordinatorLayout: CoordinatorLayout
    private var isDisconnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coordinatorLayout = findViewById(R.id.coordinator)

        transaction(ExchangeListFragment())

        if (!checkInternetConnection())
            createConnectionSnackBar(
                coordinatorLayout,
                "اینترنت شما قطع شد",
                R.color.internetDisConnected,
                Snackbar.LENGTH_INDEFINITE
            )

        internetMonitoring()
    }

    private fun checkInternetConnection(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION") networkInfo.isConnected
        }
    }

    private fun internetMonitoring() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val connectivityManager =
                this.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
            val networkRequest = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build()

            val networkCallback = object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    createConnectionSnackBar(
                        coordinatorLayout,
                        "اینترنت شما وصل شد",
                        R.color.internetConnected,
                        Snackbar.LENGTH_LONG
                    )
//                    if (isDisconnected) {
//                        isDisconnected = false
//                    }
                }

                override fun onUnavailable() {
                    super.onUnavailable()

                    createConnectionSnackBar(
                        coordinatorLayout,
                        "اینترنت شما قطع شد",
                        R.color.internetDisConnected,
                        Snackbar.LENGTH_INDEFINITE
                    )
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    createConnectionSnackBar(
                        coordinatorLayout,
                        "اینترنت شما قطع شد",
                        R.color.internetDisConnected,
                        Snackbar.LENGTH_INDEFINITE
                    )
//                    isDisconnected = true
                }
            }
            connectivityManager.requestNetwork(networkRequest, networkCallback)

        } else {
            val connectivityManager =
                this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkRequest = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(
                networkRequest.build(),
                object : ConnectivityManager.NetworkCallback() {

                    override fun onAvailable(network: Network) {
                        super.onAvailable(network)
                        createConnectionSnackBar(
                            coordinatorLayout,
                            "اینترنت شما وصل شد",
                            R.color.internetConnected,
                            Snackbar.LENGTH_LONG
                        )
                        if (isDisconnected) {
                            isDisconnected = false
                        }
//                        toast("اینترنت شما وصل شد")
                    }

                    override fun onLost(network: Network) {
                        super.onLost(network)
                        createConnectionSnackBar(
                            coordinatorLayout,
                            "اینترنت شما قطع شد",
                            R.color.internetDisConnected,
                            Snackbar.LENGTH_INDEFINITE
                        )
                        isDisconnected = true
//                    toast("اینترنت شما قطع شد")
                    }
                })
        }
    }
}