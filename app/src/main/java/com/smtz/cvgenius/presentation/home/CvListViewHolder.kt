package com.smtz.cvgenius.presentation.home

import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import com.smtz.cvgenius.databinding.ViewHolderCvListBinding
import com.smtz.cvgenius.domain.model.CvVO

class CvListViewHolder(private var binding: ViewHolderCvListBinding, private val delegate: CvDelegate) : RecyclerView.ViewHolder(binding.root) {

    private var mDataVO : CvVO ? = null

    init {
        itemView.setOnClickListener {
            mDataVO?.let {
                delegate.onTapCv(it.cvId)
            }
        }
    }

    fun bindData(data: CvVO) {
        mDataVO = data

        val name = "${data.personalDetails?.firstName} ${data.personalDetails?.lastName}"
        binding.tvUserName.text = name
        binding.tvEmail.text = data.personalDetails?.email

        data.profileImage?.let {
            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            binding.ivProfile.setImageBitmap(bitmap)
        }
    }
}