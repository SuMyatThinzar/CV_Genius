package com.smtz.cvgenius.presentation.createcv

import androidx.recyclerview.widget.RecyclerView
import com.smtz.cvgenius.presentation.details.DetailButtonDelegate
import com.smtz.cvgenius.databinding.ViewHolderDetailButtonBinding
import com.smtz.cvgenius.domain.model.DetailButtonLabel

class AddDetailButtonViewHolder(private val binding: ViewHolderDetailButtonBinding, private val delegate: DetailButtonDelegate) : RecyclerView.ViewHolder(binding.root) {

    private var mData: DetailButtonLabel? = null

    init {
        binding.root.setOnClickListener {
            mData?.let {
                delegate.onTapButton(it.id)
            }
        }
    }

    fun bindData(data: DetailButtonLabel) {
        mData = data

        binding.ivDetailImage.setImageResource(data.image)
        binding.tvDetailLabel.text = data.label
    }
}