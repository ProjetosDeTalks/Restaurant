package com.example.restaurant.utils.connectionChecking

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object ConnectionChecker {

    fun handleInternetConnectionAvailability(
        connectivityManager: ConnectivityManager
    ): Boolean {
        if (connectivityManager.allNetworks.isNotEmpty()) {
            connectivityManager.allNetworks.forEach { network ->
                connectivityManager.getNetworkCapabilities(network)?.let { networkCapabilities ->
                    if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                        return true
                    }
                }
            }
        } else {
            return false
        }
        return false
    }
}
