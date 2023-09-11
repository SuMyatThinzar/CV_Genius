package com.smtz.cvgenius.presentation.details.reference

import androidx.recyclerview.widget.RecyclerView
import com.smtz.cvgenius.R
import com.smtz.cvgenius.databinding.ViewHolderAddProjectDetailsBinding
import com.smtz.cvgenius.databinding.ViewHolderAddReferenceBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.ProjectDetailVO
import com.smtz.cvgenius.domain.model.ReferenceVO
import com.smtz.cvgenius.presentation.details.projectDetails.ButtonSaveProjectDelegate
import com.smtz.cvgenius.presentation.details.projectDetails.ProjectDetailViewPod

class ReferenceViewHolder(private val binding: ViewHolderAddReferenceBinding, private val delegate: ButtonSaveReferenceDelegate) : RecyclerView.ViewHolder(binding.root) {

    private var mData: ReferenceVO? = null

    private var mReferenceViewPod: ReferenceViewPod = itemView.findViewById(R.id.vpAddReference)

    init {

        mReferenceViewPod.setUpReferenceViewPod(delegate = delegate, changeBtnAdd = false)

    }

    fun bindData(data: ReferenceVO) {
        mData = data

        mReferenceViewPod.setUpData(data)

    }
}