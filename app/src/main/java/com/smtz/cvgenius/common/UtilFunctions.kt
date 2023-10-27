package com.smtz.cvgenius.common

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.view.ViewGroup

fun checkInternetConnection(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}


fun setUpLayoutParams(view: View, left: Int, top: Int, right: Int, bottom: Int): ViewGroup.MarginLayoutParams {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.setMargins(left, top, right, bottom)
    return layoutParams
}