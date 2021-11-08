package com.glieselabs.device_check.root

import android.content.Context
import android.os.Build
import com.scottyab.rootbeer.RootBeer
import java.util.*


class RootCheck {


    companion object {
        private lateinit var rootCheckImpl: RootCheckImpl

        fun isRooted(context: Context): Boolean {
            rootCheckImpl = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PostAndroidM()
            else PreAndroidM()
            return rootCheckImpl.isRooted() || rootBeerCheck(context)
        }

        private fun rootBeerCheck(context: Context): Boolean {
            val (onePlus, moto, xiaomi, oppo) = listOf("oneplus", "moto", "xiaomi", "oppo")

            val rootBeer = RootBeer(context)
            val brand = Build.BRAND?.toLowerCase(Locale.getDefault());
            return if ((brand?.contains(onePlus) == true) || (brand?.contains(moto) == true) || (brand?.contains(xiaomi) == true) || (brand?.contains(oppo) == true)) {
                rootBeer.isRootedWithBusyBoxCheck
            } else {
                rootBeer.isRooted
            }
        }
    }
}