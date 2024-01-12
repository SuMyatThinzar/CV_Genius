package com.smtz.cvgenius.presentation.details.educationDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.smtz.cvgenius.R
import com.smtz.cvgenius.common.components.CvSingleton
import com.smtz.cvgenius.core.BaseActivity
import com.smtz.cvgenius.data.repository.CvModelImpl
import com.smtz.cvgenius.databinding.ActivityEducationDetailBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.EducationDetailVO
import com.smtz.cvgenius.domain.repository.CvModel

class EducationDetailActivity : BaseActivity<ActivityEducationDetailBinding>(),
    ButtonSaveEducationDelegate {

    private val ANIMATION_DURATION = 250L
    private val mToolbarHeight = 330
    private var isExpanded = true

    private var mEducationDetailAdapter = AddEducationDetailsAdapter(this)
    private lateinit var mEducationDetailViewPod : EducationDetailViewPod

    private var mCvModel: CvModel = CvModelImpl

    private var mCvVO: CvVO? = null
    private var mEducationList: MutableList<EducationDetailVO> = mutableListOf()

    override val binding: ActivityEducationDetailBinding by lazy {
        ActivityEducationDetailBinding.inflate(layoutInflater)
    }

    companion object {
//        const val EXTRA_EDUCATION_STRING = "EXTRA_EDUCATION_STRING"

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, EducationDetailActivity::class.java)
//                .putExtra(EXTRA_EDUCATION_STRING, jsonString)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val jsonListString = intent.getStringExtra(EXTRA_EDUCATION_STRING)
        mCvVO = CvSingleton.instance.cvVO

//        setUpTitleAndButton(expand = true, binding.tvTitle, binding.btnBack, binding.frameLayout)
        setUpViewPods()
        setUpListeners()
        setUpAdapters()
        setUpData()
    }

    private fun setUpData() {
        if (mCvVO?.educationDetails != null) {
            mEducationList = mCvVO?.educationDetails?: mutableListOf()

                mEducationDetailAdapter.setNewData(mEducationList)
        }
    }

    private fun setUpViewPods() {
        mEducationDetailViewPod = binding.root.findViewById(R.id.vpAddEducationDetail)
        mEducationDetailViewPod.setUpEducationDetailViewPod(delegate = this, changeBtnAdd = true)
    }

    private fun setUpListeners() {

        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }

//        binding.nestedScrollView.viewTreeObserver.addOnScrollChangedListener(object : ViewTreeObserver.OnScrollChangedListener {
//                var y = 0f
//                override fun onScrollChanged() {
//                    if (binding.nestedScrollView.scrollY > y && mToolbarHeight == binding.frameLayout.height) {
//                        collapseToolbar()
//                        isExpanded = false
//                    }
//                    if (mToolbarHeight > binding.frameLayout.height && binding.nestedScrollView.scrollY == 0 && !isExpanded) {
//                        expandToolbar()
//                        isExpanded = true
//                    }
//                    y = binding.nestedScrollView.scrollY.toFloat()
//                }
//            })

    }

    private fun setUpAdapters() {
        binding.rvEducationDetails.apply {
            adapter = mEducationDetailAdapter
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
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
//
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

    override fun onTapSaveEducation(educationDetailVO: EducationDetailVO) {
        val iterator = mEducationList.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item.id == educationDetailVO.id) {
                iterator.remove()
            }
        }
        mEducationList.add(educationDetailVO)
        mCvVO!!.educationDetails = mEducationList  //  singletons are used to provide a single shared instance throughout an application
//        mCvModel.insertCV(mCvVO!!)
        mEducationDetailAdapter.setNewData(mEducationList)
        Toast.makeText(applicationContext, "Saved", Toast.LENGTH_SHORT).show()
    }

    override fun onTapDelete(id: Long) {
//        val iterator = mEducationList.value?.iterator()
//        while (iterator?.hasNext() == true) {
//            val item = iterator.next()
//            if (item.id == id) {
//                iterator.remove()
//            }
//        }
        // notify observers that the data has changed
//        mEducationList.value = mEducationList.value

        val iterator = mEducationList.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item.id == id) {
                iterator.remove()
            }
        }
//        mCvModel.insertCV(mCvVO!!)
        mEducationDetailAdapter.setNewData(mEducationList)
        Toast.makeText(applicationContext, "Deleted", Toast.LENGTH_SHORT).show()
    }
}