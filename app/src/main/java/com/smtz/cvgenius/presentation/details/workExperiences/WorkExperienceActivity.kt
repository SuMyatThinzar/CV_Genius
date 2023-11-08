package com.smtz.cvgenius.presentation.details.workExperiences

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.smtz.cvgenius.R
import com.smtz.cvgenius.common.CvSingleton
import com.smtz.cvgenius.core.BaseActivity
import com.smtz.cvgenius.data.repository.CvModelImpl
import com.smtz.cvgenius.databinding.ActivityWorkExperienceBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.WorkExperienceVO
import com.smtz.cvgenius.domain.repository.CvModel
import com.smtz.cvgenius.utils.setUpTitleAndButton

class WorkExperienceActivity : BaseActivity<ActivityWorkExperienceBinding>(), ButtonSaveWorkExpDelegate {

    private val ANIMATION_DURATION = 250L
    private val mToolbarHeight = 330
    private var isExpanded = true

    private var mWorkExperienceAdapter = AddWorkExperienceAdapter(this)
    private lateinit var mWorkExperienceViewPod: WorkExperienceViewPod

    private var mCvModel: CvModel = CvModelImpl

    private var mCvVO: CvVO? = null
    private var mWorkExperienceList: MutableList<WorkExperienceVO> = mutableListOf()

    override val binding: ActivityWorkExperienceBinding by lazy {
        ActivityWorkExperienceBinding.inflate(layoutInflater)
    }

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, WorkExperienceActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mCvVO = CvSingleton.instance.cvVO

        binding.root.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

//        setUpTitleAndButton(expand = true, binding.tvTitle, binding.btnBack, binding.frameLayout)
        setUpViewPods()
        setUpListeners()
        setUpAdapters()
        setUpData()

    }

    private fun setUpData() {
        if (mCvVO?.workExperiences != null) {
            mWorkExperienceList = mCvVO?.workExperiences!!

            mWorkExperienceAdapter.setNewData(mWorkExperienceList)
        }
    }
    private fun setUpViewPods() {
        mWorkExperienceViewPod = binding.root.findViewById(R.id.vpAddWorkExperience)
        mWorkExperienceViewPod.setUpWorkExperienceViewPod(delegate = this, changeBtnAdd = true)
    }

    private fun setUpListeners() {

        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }

//        binding.nestedScrollView.viewTreeObserver.addOnScrollChangedListener(object :
//            ViewTreeObserver.OnScrollChangedListener {
//            var y = 0f
//            override fun onScrollChanged() {
//                if (binding.nestedScrollView.scrollY > y && mToolbarHeight == binding.frameLayout.height) {
//                    collapseToolbar()
//                    isExpanded = false
//                }
//                if (mToolbarHeight > binding.frameLayout.height && binding.nestedScrollView.scrollY == 0 && !isExpanded) {
//                    expandToolbar()
//                    isExpanded = true
//                }
//                y = binding.nestedScrollView.scrollY.toFloat()
//            }
//        })
    }

    private fun setUpAdapters() {
        binding.rvWorkExperiences.apply {
            adapter = mWorkExperienceAdapter
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        }
    }

//    private fun collapseToolbar() {
//        if (mToolbarHeight == binding.frameLayout.height) {
//            setUpTitleAndButton(expand = false, binding.tvTitle, binding.btnBack, binding.frameLayout)
//
//            val animator = ValueAnimator.ofInt(binding.frameLayout.height, mToolbarHeight / 2)
//            animator.duration = ANIMATION_DURATION
//            animator.interpolator = AccelerateInterpolator()
//            animator.addUpdateListener {
//                val value = it.animatedValue as Int
//                binding.frameLayout.layoutParams.height = value
//                binding.frameLayout.requestLayout()
//            }
//            animator.start()
//        }
//    }

//    private fun expandToolbar() {
//        if (mToolbarHeight > binding.frameLayout.height) {
//            setUpTitleAndButton(expand = true, binding.tvTitle, binding.btnBack, binding.frameLayout)
//
//            val animator = ValueAnimator.ofInt(binding.frameLayout.height, mToolbarHeight)
//            animator.duration = ANIMATION_DURATION
//            animator.interpolator = DecelerateInterpolator()
//            animator.addUpdateListener {
//                val value = it.animatedValue as Int
//                binding.frameLayout.layoutParams.height = value
//                binding.frameLayout.requestLayout()
//            }
//            animator.start()
//        }
//    }

    override fun onTapSaveWorkExperience(workExperienceVO: WorkExperienceVO) {
        val iterator = mWorkExperienceList.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item.id == workExperienceVO.id) {
                iterator.remove()
            }
        }
        mWorkExperienceList.add(workExperienceVO)
        mCvVO!!.workExperiences = mWorkExperienceList
//        mCvModel.insertCV(mCvVO!!)
        mWorkExperienceAdapter.setNewData(mWorkExperienceList)
        Toast.makeText(applicationContext, "saved", Toast.LENGTH_SHORT).show()
    }

    override fun onTapDelete(id: Long) {
        val iterator = mWorkExperienceList.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item.id == id) {
                iterator.remove()
            }
        }
//        mCvModel.insertCV(mCvVO!!)
        mWorkExperienceAdapter.setNewData(mWorkExperienceList)
        Toast.makeText(applicationContext, "Deleted", Toast.LENGTH_SHORT).show()
    }

}