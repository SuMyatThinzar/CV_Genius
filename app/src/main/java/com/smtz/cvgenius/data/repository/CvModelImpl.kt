package com.smtz.cvgenius.data.repository

import androidx.lifecycle.LiveData
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.repository.BaseModel
import com.smtz.cvgenius.domain.repository.CvModel

object CvModelImpl : BaseModel(), CvModel {

    override fun insertCV(cvVO: CvVO) {
        mCVGeniusDatabase?.cvDao()?.insertNewCV(cvVO)
    }

    override fun getCv(cvId: Long): LiveData<CvVO?>? {
        return mCVGeniusDatabase?.cvDao()?.getSingleCV(cvId)
    }

    override fun getAllCv(): LiveData<List<CvVO>>? {
        return mCVGeniusDatabase?.cvDao()?.getAllCV()
    }

    override fun deleteCv(cvId: Long) {
        mCVGeniusDatabase?.cvDao()?.deleteCV(cvId)
    }

}