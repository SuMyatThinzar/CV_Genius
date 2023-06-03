package com.smtz.cvgenius.domain.repository

import androidx.lifecycle.LiveData
import com.smtz.cvgenius.domain.model.CvVO

interface CvModel {

    fun insertCV(cvVO: CvVO)

    fun getCv(cvId: Long): LiveData<CvVO?>?

    fun getAllCv(): LiveData<List<CvVO>>?

    fun deleteCv(cvId: Long)

}