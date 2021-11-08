package com.glieselabs.device_check.devMode

import android.content.Context
import android.os.Build
import android.provider.Settings

class DeveloperModeCheck {
    companion object {
        @Suppress("DEPRECATION")
        fun isDeveloperModeEnabled(context: Context): Boolean {
            return when {
                Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN -> {
                    Settings.Secure.getInt(
                        context.contentResolver,
                        Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0
                    ) != 0
                }
                Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN -> {
                    Settings.Secure.getInt(
                        context.contentResolver,
                        Settings.Secure.DEVELOPMENT_SETTINGS_ENABLED, 0
                    ) != 0
                }
                else -> false
            }
        }
    }
}