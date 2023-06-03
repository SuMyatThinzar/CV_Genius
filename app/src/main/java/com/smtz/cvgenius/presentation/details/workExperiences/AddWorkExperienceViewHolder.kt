package com.smtz.cvgenius.presentation.details.workExperiences

import androidx.recyclerview.widget.RecyclerView
import com.smtz.cvgenius.R
import com.smtz.cvgenius.databinding.ViewHolderAddWorkExperienceBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.WorkExperienceVO

class AddWorkExperienceViewHolder(private val binding: ViewHolderAddWorkExperienceBinding, private val delegate: ButtonSaveWorkExpDelegate) : RecyclerView.ViewHolder(binding.root) {

    private var mData: WorkExperienceVO? = null

    private var mWorkExperienceViewPod: WorkExperienceViewPod = itemView.findViewById(R.id.vpWorkExperience)

    init {

        mWorkExperienceViewPod.setUpWorkExperienceViewPod(delegate = delegate, changeBtnAdd = false)

    }

    fun bindData(data: WorkExperienceVO) {
        mData = data

        mWorkExperienceViewPod.setUpData(data)

    }
}