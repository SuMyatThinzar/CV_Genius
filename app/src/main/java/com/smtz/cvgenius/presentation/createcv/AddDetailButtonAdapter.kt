package com.smtz.cvgenius.presentation.createcv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smtz.cvgenius.presentation.details.DetailButtonDelegate
import com.smtz.cvgenius.databinding.ViewHolderDetailButtonBinding
import com.smtz.cvgenius.utils.detailButtonList

class AddDetailButtonAdapter(private val delegate: DetailButtonDelegate) : RecyclerView.Adapter<AddDetailButtonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddDetailButtonViewHolder {
        val binding = ViewHolderDetailButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddDetailButtonViewHolder(binding, delegate)
    }

    override fun onBindViewHolder(holder: AddDetailButtonViewHolder, position: Int) {
            holder.bindData(detailButtonList[position])
    }

    override fun getItemCount(): Int {
        return detailButtonList.count()
    }
}
