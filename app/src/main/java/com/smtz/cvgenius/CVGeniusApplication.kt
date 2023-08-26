package com.smtz.cvgenius

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.smtz.cvgenius.common.CvSingleton
import com.smtz.cvgenius.common.ads.AppOpenAdManager
import com.smtz.cvgenius.data.repository.CvModelImpl

class CVGeniusApplication : Application() {

    private lateinit var appOpenManager: AppOpenAdManager

    override fun onCreate() {
        super.onCreate()

        CvModelImpl.initDatabase(applicationContext)

        // for google admob.
        MobileAds.initialize(this) {
            appOpenManager = AppOpenAdManager(this)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        CvSingleton.instance.cleanup()   // Call the cleanup method of your singleton
    }
}