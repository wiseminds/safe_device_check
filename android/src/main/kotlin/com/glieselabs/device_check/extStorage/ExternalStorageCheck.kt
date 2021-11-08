package com.glieselabs.device_check.extStorage

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager


class ExternalStorageCheck {
    companion object {
        fun isOnExternalStorage(context: Context): Boolean {
            val pm: PackageManager = context.packageManager
            try {
                val pi: PackageInfo = pm.getPackageInfo(context.packageName, 0)
                val ai = pi.applicationInfo
                return ai.flags and ApplicationInfo.FLAG_EXTERNAL_STORAGE == ApplicationInfo.FLAG_EXTERNAL_STORAGE
            } catch (e: PackageManager.NameNotFoundException) {
                // ignore
            }
            try {
                val filesDir: String = context.filesDir.absolutePath
                if (filesDir.startsWith("/data/")) {
                    return false
                } else if (filesDir.contains("/mnt/") || filesDir.contains("/sdcard/")) {
                    return true
                }
            } catch (e: Throwable) {
                // ignore
            }
            return false
        }
    }
}