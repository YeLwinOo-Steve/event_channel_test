package com.yelwinoo.event_channel.event_channel_practice

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import io.flutter.plugin.common.EventChannel

class NetworkStreamHandler(private var activity: Activity?) : EventChannel.StreamHandler {
    private var eventSink: EventChannel.EventSink? = null
    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        eventSink = events
        startListeningNetworkChanges()
    }

    override fun onCancel(arguments: Any?) {
        stopListeningNetworkChanges()
        eventSink = null
        activity = null
    }

    private val networkCallback =
    object : ConnectivityManager.NetworkCallback() {
        override fun onLost(network: Network){
            super.onLost(network)
            activity?.runOnUiThread{ eventSink?.success(Constants.disconnected)}
        }

        override fun onCapabilitiesChanged(
            network: Network,
            netCap: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, netCap)
            val status =
                when{
                    netCap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> Constants.wifi
                    netCap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> Constants.internet
                    else -> Constants.unknown
                }
            activity?.runOnUiThread{eventSink?.success(status)}

        }
    }

    private fun startListeningNetworkChanges() {
        val manager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            manager?.registerDefaultNetworkCallback(networkCallback)
        } else {
            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()
            manager?.registerNetworkCallback(request, networkCallback)
        }
    }

    private fun stopListeningNetworkChanges() {
        val manager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        manager?.unregisterNetworkCallback(networkCallback)
    }
}