package com.smtz.cvgenius.presentation.details.reference

import com.smtz.cvgenius.domain.model.ReferenceVO

interface ButtonSaveReferenceDelegate {
    fun onTapSaveReference(referenceVO: ReferenceVO)
    fun onTapDelete(id: Long)
}