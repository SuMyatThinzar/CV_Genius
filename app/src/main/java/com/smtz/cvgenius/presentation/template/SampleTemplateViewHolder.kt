package com.smtz.cvgenius.presentation.template

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.smtz.cvgenius.common.delegates.SampleTemplateDelegate
import com.smtz.cvgenius.databinding.ViewHolderSampleTemplateBinding
import com.smtz.cvgenius.domain.model.TemplateVO

class SampleTemplateViewHolder(private val binding: ViewHolderSampleTemplateBinding, private val delegate: SampleTemplateDelegate) :
    RecyclerView.ViewHolder(binding.root) {

    private var mTemplate: TemplateVO? = null

    init {
        itemView.setOnClickListener {
            mTemplate?.let {
                delegate.onTapSampleTemplate(it.id)
            }
        }
    }

    fun bindData(template: TemplateVO) {
        mTemplate = template

        binding.ivSampleTemplate.setImageResource(template.image)
//        binding.tv.text = template.toString()

        if (!template.available) {
            itemView.isClickable = false
            binding.darkenBackground.visibility = View.VISIBLE
            binding.ivComingSoon.visibility = View.VISIBLE
        } else {
            itemView.isClickable = true
            binding.darkenBackground.visibility = View.INVISIBLE
            binding.ivComingSoon.visibility = View.INVISIBLE
        }
    }
}