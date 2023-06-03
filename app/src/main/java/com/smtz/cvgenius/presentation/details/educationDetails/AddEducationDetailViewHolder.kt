package com.smtz.cvgenius.presentation.details.educationDetails

import androidx.recyclerview.widget.RecyclerView
import com.smtz.cvgenius.R
import com.smtz.cvgenius.databinding.ViewHolderAddEducationDetailsBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.EducationDetailVO

class AddEducationDetailViewHolder(private val binding: ViewHolderAddEducationDetailsBinding, private val delegate: ButtonSaveEducationDelegate) : RecyclerView.ViewHolder(binding.root) {

    private var mData: EducationDetailVO? = null

    private var mEducationDetailViewPod : EducationDetailViewPod = itemView.findViewById(R.id.vpAddEducationDetail)

    init {

        mEducationDetailViewPod.setUpEducationDetailViewPod(delegate = delegate, changeBtnAdd = false)

    }

    fun bindData(data: EducationDetailVO) {
        mData = data

        mEducationDetailViewPod.setUpData(data)

    }
}