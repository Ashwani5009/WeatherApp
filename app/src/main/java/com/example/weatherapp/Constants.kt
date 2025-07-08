package com.example.weatherapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

object Constants {
    const val APP_ID = "46ba14ffb49de950b3bdbf1c5162ac32"
    const val BASE_URL = "https://api.openweathermap.org/data/"
    const val METRIC_UNIT = "metric"


    // Define a function called isNetworkAvailable that takes a context parameter and return boolean values
    fun isNetworkAvailable(context : Context) : Boolean {
        // Get an instance of ConnectivityManager using the context parameter
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Check if device's API is level 23 or higher
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            // Get Current active network and return false if there is none
            val network = connectivityManager.activeNetwork ?: return false

            // Get the capabilities of the active network and return false if there is none
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            // Check the type of active network and return true if it is either WIFI, CELLULAR , ETHERNET
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                else -> return false
            }
        } else {
            // For API level below 23 , get the active network info and return true if it is connected or connecting
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }
}