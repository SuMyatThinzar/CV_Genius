package com.smtz.cvgenius.presentation.details.objectives

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import com.smtz.cvgenius.R
import com.smtz.cvgenius.common.CvSingleton
import com.smtz.cvgenius.core.BaseActivity
import com.smtz.cvgenius.data.repository.CvModelImpl
import com.smtz.cvgenius.databinding.ActivityObjectiveBinding
import com.smtz.cvgenius.domain.model.*
import com.smtz.cvgenius.domain.repository.CvModel
import com.smtz.cvgenius.utils.setUpTitleAndButton

class ObjectiveActivity : BaseActivity<ActivityObjectiveBinding>() {

    private var mCvModel: CvModel = CvModelImpl

    private var mCvVO: CvVO? = null
    private var mObjective: String? = null

    override val binding: ActivityObjectiveBinding by lazy {
        ActivityObjectiveBinding.inflate(layoutInflater)
    }

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, ObjectiveActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mCvVO = CvSingleton.instance.cvVO

        setUpTitleAndButton(expand = true, binding.tvTitle, binding.btnBack, binding.frameLayout)
        setUpListeners()
        requestData()
    }

    private fun requestData() {
        mCvVO?.objective?.let {
            mObjective = it
            binding.etObjective.setText(it)
        }
    }

    private fun setUpListeners() {

        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }
        binding.btnSave.setOnClickListener {
            mCvVO?.objective = binding.etObjective.text.toString().trim()
//            mCvModel.insertCV(mCvVO!!)
            finish()
        }
        binding.btnClear.setOnClickListener {
            binding.etObjective.setText("")
        }
    }

    override fun onBackPressed() {
        if (binding.etObjective.text.toString().isEmpty()) {
            mCvVO?.objective = null
//            mCvModel.insertCV(mCvVO!!)
        }
        super.onBackPressed()
    }
}