package com.smtz.cvgenius.domain.repository

import android.content.Context
import com.smtz.cvgenius.data.data_source.CVGeniusDatabase

abstract class BaseModel {

    protected var mCVGeniusDatabase: CVGeniusDatabase? = null

    fun initDatabase(context: Context) {
        mCVGeniusDatabase = CVGeniusDatabase.getDBInstance(context)
    }

}