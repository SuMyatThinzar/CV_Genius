package com.smtz.cvgenius.presentation.details.reference

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smtz.cvgenius.databinding.ViewHolderAddProjectDetailsBinding
import com.smtz.cvgenius.databinding.ViewHolderAddReferenceBinding
import com.smtz.cvgenius.domain.model.ProjectDetailVO
import com.smtz.cvgenius.domain.model.ReferenceVO
import com.smtz.cvgenius.presentation.details.projectDetails.AddProjectDetailViewHolder
import com.smtz.cvgenius.presentation.details.projectDetails.ButtonSaveProjectDelegate

class ReferenceAdapter(private var delegate: ButtonSaveReferenceDelegate) :
    RecyclerView.Adapter<ReferenceViewHolder>() {

    private var mData: List<ReferenceVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReferenceViewHolder {
        val binding = ViewHolderAddReferenceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReferenceViewHolder(binding, delegate)
    }

    override fun onBindViewHolder(holder: ReferenceViewHolder, position: Int) {
        if (mData.isNotEmpty()) {
            holder.bindData(mData[position])
        }
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    fun setNewData(data: List<ReferenceVO>) {
        mData = data
        notifyDataSetChanged()
    }
}
