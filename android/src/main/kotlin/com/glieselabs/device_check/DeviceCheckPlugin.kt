package com.glieselabs.device_check

import android.content.Context
import androidx.annotation.NonNull
import com.glieselabs.device_check.devMode.DeveloperModeCheck
import com.glieselabs.device_check.emulator.EmulatorCheck
import com.glieselabs.device_check.extStorage.ExternalStorageCheck
import com.glieselabs.device_check.root.RootCheck
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result


class DeviceCheckPlugin() : FlutterPlugin, MethodCallHandler {
    private lateinit var channel: MethodChannel
    private lateinit var context: Context

    override fun onAttachedToEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(binding.binaryMessenger, "device_check")
        context = binding.applicationContext
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when (call.method) {
            "platformVersion" -> {
                result.success("Android ${android.os.Build.VERSION.RELEASE}")
            }
            "isRooted" -> {
                result.success(RootCheck.isRooted(context))
            }
            "isDeveloperModeOn" -> {
                result.success(DeveloperModeCheck.isDeveloperModeEnabled(context))
            }
            "isEmulator" -> {
                result.success(EmulatorCheck.isProbablyAnEmulator())
            }
            "isInstalledOnExternalStorage" -> {
                result.success(ExternalStorageCheck.isOnExternalStorage(context))
            }
            else -> {
                result.notImplemented()
            }
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
