package com.smtz.cvgenius.presentation.details.workExperiences

import com.smtz.cvgenius.domain.model.WorkExperienceVO

interface ButtonSaveWorkExpDelegate {
    fun onTapSaveWorkExperience(workExperienceVO: WorkExperienceVO)
    fun onTapDelete(id: Long)
}