package com.glieselabs.device_check.root

import java.io.File

class PreAndroidM : RootCheckImpl {
    private val commandToExecute = "/system/xbin/which su";

    override fun isRooted(): Boolean {
        return method1() || method2()
    }

    private fun method1(): Boolean {
        return try {
            val process = Runtime.getRuntime().exec(commandToExecute)
            process.waitFor() == 0
        } catch (e: Exception) {
            false
        }
    }

    private fun method2(): Boolean {
        val paths = arrayOf(
            "/system/app/Superuser.apk",
            "/sbin/su",
            "/system/bin/su",
            "/system/xbin/su",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/system/sd/xbin/su",
            "/system/bin/failsafe/su",
            "/data/local/su"
        )
        for (path in paths) {
            if (File(path).exists()) {
                return true
            }
        }
        return false
    }
}