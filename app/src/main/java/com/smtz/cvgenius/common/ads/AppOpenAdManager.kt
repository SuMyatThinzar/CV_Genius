package com.smtz.cvgenius.common.ads

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.smtz.cvgenius.BuildConfig
import com.smtz.cvgenius.CVGeniusApplication
import java.util.*

class AppOpenAdManager : DefaultLifecycleObserver, Application.ActivityLifecycleCallbacks {
    object AppOpen {
        const val LOG_TAG = "AppOpenManager"
        const val AD_UNIT_ID = BuildConfig.APP_OPEN_AD_ID
    }

    private var appOpenAd: AppOpenAd? = null
    private var loadCallback: AppOpenAd.AppOpenAdLoadCallback? = null
    private var currentActivity: Activity? = null
    private var myApplication: CVGeniusApplication? = null
    private var isShowingAd = false
    private var loadTime: Long = 0

    /** Constructor  */
    constructor(myApplication: CVGeniusApplication?) {
        this.myApplication = myApplication
        this.myApplication?.registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    /** LifecycleObserver methods  */
    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        showAdIfAvailable()
        Log.d(AppOpen.LOG_TAG,"On Start")
    }

    /** Request an ad  */
    fun fetchAd() {
        // Have unused ad, no need to fetch another.
        if (isAdAvailable()) {
            return
        }

        loadCallback = object : AppOpenAd.AppOpenAdLoadCallback() {
            /**
             * Called when an app open ad has loaded.
             *
             * @param ad the loaded app open ad.
             */
            override fun onAdLoaded(ad: AppOpenAd) {
                appOpenAd = ad
                loadTime = Date().time
            }

            /**
             * Called when an app open ad has failed to load.
             *
             * @param loadAdError the error.
             */
            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                // Handle the error.
            }
        }
        val request = getAdRequest()
        AppOpenAd.load(
            myApplication!!.applicationContext,
            AppOpen.AD_UNIT_ID,
            request!!,
            AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
            loadCallback!!
        )
    }

    /** Utility method to check if ad was loaded more than n hours ago.  */
    private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
        val dateDifference = Date().time - loadTime
        val numMilliSecondsPerHour: Long = 3600000
        return dateDifference < numMilliSecondsPerHour * numHours
    }

    /** Shows the ad if one isn't already showing.  */
    private fun showAdIfAvailable() {
        // Only show ad if there is not already an app open ad currently showing
        // and an ad is available.
        if (!isShowingAd && isAdAvailable()) {
            Log.d(AppOpen.LOG_TAG, "Will show ad.")
            val fullScreenContentCallback: FullScreenContentCallback =
                object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        // Set the reference to null so isAdAvailable() returns false.
                        appOpenAd = null
                        isShowingAd = false
                        fetchAd()
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {}
                    override fun onAdShowedFullScreenContent() {
                        isShowingAd = true
                    }
                }
            appOpenAd!!.fullScreenContentCallback = fullScreenContentCallback
            appOpenAd!!.show(this.currentActivity!!)
        } else {
            Log.d(AppOpen.LOG_TAG,"Can not show ad.")
            fetchAd()
        }
    }

    /** Creates and returns ad request.  */
    private fun getAdRequest(): AdRequest? {
        return AdRequest.Builder().build()
    }

    /** Utility method that checks if ad exists and can be shown.  */
    private fun isAdAvailable(): Boolean {
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4)
    }

    /** ActivityLifecycleCallback methods */
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, savedInstanceState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {
        currentActivity = null
    }
}