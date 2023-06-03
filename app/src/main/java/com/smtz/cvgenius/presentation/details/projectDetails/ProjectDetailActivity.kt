package com.smtz.cvgenius.presentation.details.projectDetails

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.smtz.cvgenius.R
import com.smtz.cvgenius.common.CvSingleton
import com.smtz.cvgenius.core.BaseActivity
import com.smtz.cvgenius.data.repository.CvModelImpl
import com.smtz.cvgenius.databinding.ActivityProjectDetailBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.ProjectDetailVO
import com.smtz.cvgenius.domain.repository.CvModel
import com.smtz.cvgenius.utils.setUpTitleAndButton

class ProjectDetailActivity : BaseActivity<ActivityProjectDetailBinding>(),
    ButtonSaveProjectDelegate {

    private val ANIMATION_DURATION = 250L
    private val mToolbarHeight = 330
    private var isExpanded = true

    private var mAddDetailAdapter = AddProjectDetailsAdapter(this)
    private lateinit var mProjectDetailViewPod: ProjectDetailViewPod

    private var mCvModel: CvModel = CvModelImpl

    private var mCvVO: CvVO? = null
    private var mProjectDetailList: MutableList<ProjectDetailVO> = mutableListOf()

    override val binding: ActivityProjectDetailBinding by lazy {
        ActivityProjectDetailBinding.inflate(layoutInflater)
    }

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, ProjectDetailActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mCvVO = CvSingleton.instance.cvVO

        setUpTitleAndButton(expand = true, binding.tvTitle, binding.btnBack, binding.frameLayout)
        setUpViewPods()
        setUpListeners()
        setUpAdapters()
        requestData()

    }

    private fun requestData() {
        if (mCvVO?.projectDetails != null) {
            mProjectDetailList = mCvVO?.projectDetails!!

            mAddDetailAdapter.setNewData(mProjectDetailList)
        }
    }

    private fun setUpViewPods() {
        mProjectDetailViewPod = binding.root.findViewById(R.id.vpAddProjectDetail)
        mProjectDetailViewPod.setUpProjectDetailViewPod(delegate = this, changeBtnAdd = true)
    }

    private fun setUpListeners() {

        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }

        binding.nestedScrollView.viewTreeObserver.addOnScrollChangedListener(object :
            ViewTreeObserver.OnScrollChangedListener {
            var y = 0f
            override fun onScrollChanged() {
                if (binding.nestedScrollView.scrollY > y && mToolbarHeight == binding.frameLayout.height) {
                    collapseToolbar()
                    isExpanded = false
                }
                if (mToolbarHeight > binding.frameLayout.height && binding.nestedScrollView.scrollY == 0 && !isExpanded) {
                    expandToolbar()
                    isExpanded = true
                }
                y = binding.nestedScrollView.scrollY.toFloat()
            }
        })

    }

    private fun setUpAdapters() {
        binding.rvProjectDetails.apply {
            adapter = mAddDetailAdapter
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        }
    }


    private fun collapseToolbar() {
        if (mToolbarHeight == binding.frameLayout.height) {
            setUpTitleAndButton(expand = false, binding.tvTitle, binding.btnBack, binding.frameLayout)

            val animator = ValueAnimator.ofInt(binding.frameLayout.height, mToolbarHeight / 2)
            animator.duration = ANIMATION_DURATION
            animator.interpolator = AccelerateInterpolator()
            animator.addUpdateListener {
                val value = it.animatedValue as Int
                binding.frameLayout.layoutParams.height = value
                binding.frameLayout.requestLayout()
            }
            animator.start()
        }
    }

    private fun expandToolbar() {
        if (mToolbarHeight > binding.frameLayout.height) {
            setUpTitleAndButton(expand = true, binding.tvTitle, binding.btnBack, binding.frameLayout)

            val animator = ValueAnimator.ofInt(binding.frameLayout.height, mToolbarHeight)
            animator.duration = ANIMATION_DURATION
            animator.interpolator = DecelerateInterpolator()
            animator.addUpdateListener {
                val value = it.animatedValue as Int
                binding.frameLayout.layoutParams.height = value
                binding.frameLayout.requestLayout()
            }
            animator.start()
        }
    }

    override fun onTapSaveProject(projectDetailVO: ProjectDetailVO) {
        val iterator = mProjectDetailList.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item.id == projectDetailVO.id) {
                iterator.remove()
            }
        }
        mProjectDetailList.add(projectDetailVO)
        mCvVO!!.projectDetails = mProjectDetailList
        mCvModel.insertCV(mCvVO!!)
        mAddDetailAdapter.setNewData(mProjectDetailList)
        Toast.makeText(applicationContext, "saved", Toast.LENGTH_SHORT).show()
    }

    override fun onTapDelete(id: Long) {
        val iterator = mProjectDetailList.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item.id == id) {
                iterator.remove()
            }
        }
        mCvModel.insertCV(mCvVO!!)
        mAddDetailAdapter.setNewData(mProjectDetailList)
        Toast.makeText(applicationContext, "Deleted", Toast.LENGTH_SHORT).show()
    }
}