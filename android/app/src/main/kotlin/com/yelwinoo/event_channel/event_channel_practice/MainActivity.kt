package com.yelwinoo.event_channel.event_channel_practice

import com.raywenderlich.platform_channel_events.ImageStreamHandler
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel

class MainActivity : FlutterActivity() {
    private val networkEventChannel = "platform_channel_events/connectivity"
    private val imageEventChannel = "platform_channel_events/image"
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        EventChannel(flutterEngine.dartExecutor.binaryMessenger, networkEventChannel).setStreamHandler(
            NetworkStreamHandler(this)
        )
        EventChannel(flutterEngine.dartExecutor.binaryMessenger, imageEventChannel).setStreamHandler(
            ImageStreamHandler(this)
        )
    }


}

