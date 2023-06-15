package com.smtz.cvgenius.presentation.home

import com.smtz.cvgenius.domain.model.CvVO

interface CvDelegate {
    fun onTapCv(cvId: Long)
    fun onTapViewCv(cvVO: CvVO)
    fun onTapDeleteCv(cvId: Long)
    fun onTapDuplicate(cvVO: CvVO)
//    fun onTapShare(templateId: Int)
}