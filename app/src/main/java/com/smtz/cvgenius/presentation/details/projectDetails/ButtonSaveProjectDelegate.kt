package com.smtz.cvgenius.presentation.details.projectDetails

import com.smtz.cvgenius.domain.model.ProjectDetailVO

interface ButtonSaveProjectDelegate {
    fun onTapSaveProject(projectDetailVO: ProjectDetailVO)
    fun onTapDelete(id: Long)
}