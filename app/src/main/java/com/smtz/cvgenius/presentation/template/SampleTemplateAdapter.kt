package com.smtz.cvgenius.presentation.template

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smtz.cvgenius.common.delegates.SampleTemplateDelegate
import com.smtz.cvgenius.common.dummy.templateList
import com.smtz.cvgenius.databinding.ViewHolderSampleTemplateBinding

class SampleTemplateAdapter(private val delegate: SampleTemplateDelegate) : RecyclerView.Adapter<SampleTemplateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleTemplateViewHolder {
        val binding = ViewHolderSampleTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SampleTemplateViewHolder(binding, delegate)
    }

    override fun onBindViewHolder(holder: SampleTemplateViewHolder, position: Int) {
        if(templateList.isNotEmpty()){
            holder.bindData(templateList[position])
        }
    }

    override fun getItemCount(): Int {
        return templateList.count()
    }
}