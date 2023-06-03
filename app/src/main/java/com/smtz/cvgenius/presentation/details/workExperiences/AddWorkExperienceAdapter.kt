package com.smtz.cvgenius.presentation.details.workExperiences

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smtz.cvgenius.databinding.ViewHolderAddWorkExperienceBinding
import com.smtz.cvgenius.domain.model.EducationDetailVO
import com.smtz.cvgenius.domain.model.WorkExperienceVO

class AddWorkExperienceAdapter(private var delegate: ButtonSaveWorkExpDelegate) : RecyclerView.Adapter<AddWorkExperienceViewHolder>() {

    private var mData: List<WorkExperienceVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddWorkExperienceViewHolder {
        val binding = ViewHolderAddWorkExperienceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddWorkExperienceViewHolder(binding, delegate)
    }

    override fun onBindViewHolder(holder: AddWorkExperienceViewHolder, position: Int) {
        if(mData.isNotEmpty()){
            holder.bindData(mData[position])
        }
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    fun setNewData(data: List<WorkExperienceVO>){
        mData = data
        notifyDataSetChanged()
    }
}
