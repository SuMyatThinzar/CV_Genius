package com.smtz.cvgenius.presentation.details.projectDetails

import androidx.recyclerview.widget.RecyclerView
import com.smtz.cvgenius.R
import com.smtz.cvgenius.databinding.ViewHolderAddProjectDetailsBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.ProjectDetailVO

class AddProjectDetailViewHolder(private val binding: ViewHolderAddProjectDetailsBinding, private val delegate: ButtonSaveProjectDelegate) : RecyclerView.ViewHolder(binding.root) {

    private var mData: ProjectDetailVO? = null

    private var mProjectDetailViewPod: ProjectDetailViewPod = itemView.findViewById(R.id.vpAddProjectDetail)

    init {

        mProjectDetailViewPod.setUpProjectDetailViewPod(delegate = delegate, changeBtnAdd = false)

    }

    fun bindData(data: ProjectDetailVO) {
        mData = data

        mProjectDetailViewPod.setUpData(data)

    }
}