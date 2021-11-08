package com.glieselabs.device_check.emulator

import android.annotation.SuppressLint
import android.os.Build
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.Method

class EmulatorCheck {
    companion object {
        fun isProbablyAnEmulator(): Boolean {
            // Android SDK emulator
            return (Build.FINGERPRINT.startsWith("google/sdk_gphone_")
                    && Build.FINGERPRINT.endsWith(":user/release-keys")
                    && Build.MANUFACTURER == "Google" && Build.PRODUCT.startsWith("sdk_gphone_") && Build.BRAND == "google"
                    && Build.MODEL.startsWith("sdk_gphone_"))
                    || Build.FINGERPRINT.startsWith("generic")
                    || Build.FINGERPRINT.startsWith("unknown")
                    || Build.MODEL.contains("google_sdk")
                    || Build.MODEL.contains("Emulator")
                    || Build.MODEL.contains("Android SDK built for x86")
                    //bluestacks
                    || "QC_Reference_Phone" == Build.BOARD && !"Xiaomi".equals(Build.MANUFACTURER, ignoreCase = true) //bluestacks
                    || Build.MANUFACTURER.contains("Genymotion")
                    || Build.HOST == "Build2" //MSI App Player
                    || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
                    || Build.PRODUCT == "google_sdk"
                    // another Android SDK emulator check
                    || SystemProperties.getProp("ro.kernel.qemu") == "1"

        }
    }

    private object SystemProperties {
        private var failed = false
        private var getPropMethod: Method? = null

        @SuppressLint("PrivateApi")
        fun getProp(propName: String, arg: String = ""): String {
            if (!failed) try {
                if (getPropMethod == null) {
                    val clazz = Class.forName("android.os.SystemProperties")
                    getPropMethod = clazz.getMethod("get", String::class.java, String::class.java)
                }
                return getPropMethod!!.invoke(null, propName, arg) as String? ?: arg
            } catch (e: Exception) {
                getPropMethod = null
                failed = true
            }
            var process: Process? = null
            try {
                process = Runtime.getRuntime().exec("getprop \"$propName\" \"$arg\"")
                val reader = BufferedReader(InputStreamReader(process.inputStream))
                return reader.readLine()
            } catch (e: IOException) {
            } finally {
                process?.destroy()
            }
            return arg
        }
    }
}

