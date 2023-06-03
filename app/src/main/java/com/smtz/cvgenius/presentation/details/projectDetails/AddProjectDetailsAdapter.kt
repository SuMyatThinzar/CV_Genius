package com.smtz.cvgenius.presentation.details.projectDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smtz.cvgenius.databinding.ViewHolderAddProjectDetailsBinding
import com.smtz.cvgenius.domain.model.ProjectDetailVO

class AddProjectDetailsAdapter(private var delegate: ButtonSaveProjectDelegate) :
    RecyclerView.Adapter<AddProjectDetailViewHolder>() {

    private var mData: List<ProjectDetailVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddProjectDetailViewHolder {
        val binding = ViewHolderAddProjectDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddProjectDetailViewHolder(binding, delegate)
    }

    override fun onBindViewHolder(holder: AddProjectDetailViewHolder, position: Int) {
        if (mData.isNotEmpty()) {
            holder.bindData(mData[position])
        }
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    fun setNewData(data: List<ProjectDetailVO>) {
        mData = data
        notifyDataSetChanged()
    }
}
