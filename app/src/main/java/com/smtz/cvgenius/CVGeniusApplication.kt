package com.smtz.cvgenius

import android.app.Application
import com.smtz.cvgenius.data.repository.CvModelImpl

class CVGeniusApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        CvModelImpl.initDatabase(applicationContext)
    }
}