package com.smtz.cvgenius.presentation.details.educationDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smtz.cvgenius.databinding.ViewHolderAddEducationDetailsBinding
import com.smtz.cvgenius.domain.model.EducationDetailVO

class AddEducationDetailsAdapter(private var delegate: ButtonSaveEducationDelegate) : RecyclerView.Adapter<AddEducationDetailViewHolder>() {

    private var mData: List<EducationDetailVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddEducationDetailViewHolder {
        val binding = ViewHolderAddEducationDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddEducationDetailViewHolder(binding, delegate)
    }

    override fun onBindViewHolder(holder: AddEducationDetailViewHolder, position: Int) {
        if(mData.isNotEmpty()){
            holder.bindData(mData[position])
        }
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    fun setNewData(data: List<EducationDetailVO>){
        mData = data
        notifyDataSetChanged()
    }
}
