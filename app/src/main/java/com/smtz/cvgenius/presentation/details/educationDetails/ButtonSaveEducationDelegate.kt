package com.smtz.cvgenius.presentation.details.educationDetails

import com.smtz.cvgenius.domain.model.EducationDetailVO

interface ButtonSaveEducationDelegate {
    fun onTapSaveEducation(educationDetailVO: EducationDetailVO)
    fun onTapDelete(id: Long)
}