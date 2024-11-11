package com.example.nativeeletrichouse.error

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.net.InetAddress
import java.net.UnknownHostException
import java.nio.channels.UnresolvedAddressException
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun handleUnresolvedAddressException(context: Context, e: UnresolvedAddressException) {
    Log.e("NetworkError", "UnresolvedAddressException: ${e.message}")

    try {
        InetAddress.getByName(getHostnameFromException(e))
    } catch (unknownHostException: UnknownHostException) {
        showErrorToUser(context, "Invalid hostname or network unavailable.")
        return
    }

    showErrorToUser(context, "Network connection error. Please check your internet connection.")
}

fun getHostnameFromException(e: UnresolvedAddressException): String {
    return e.message?.substringAfter("hostname:")?.trim() ?: ""
}

fun showErrorToUser(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    Log.e("NetworkError", "Error shown to user: $message")
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
            return true
        }
    }
    return false
}