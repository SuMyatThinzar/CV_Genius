package com.smtz.cvgenius.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smtz.cvgenius.databinding.ViewHolderCvListBinding
import com.smtz.cvgenius.domain.model.CvVO

class CvListAdapter(private val delegate: CvDelegate) : RecyclerView.Adapter<CvListViewHolder>() {

    private var mData: List<CvVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CvListViewHolder {
        val binding = ViewHolderCvListBinding.inflate(LayoutInflater.from(parent.context))
        return CvListViewHolder(binding, delegate)
    }

    override fun onBindViewHolder(holder: CvListViewHolder, position: Int) {
        if(mData.isNotEmpty()){
            holder.bindData(mData[position])
        }
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    fun setNewData(data: List<CvVO>){
        mData = data.reversed()
        notifyDataSetChanged()
    }
}