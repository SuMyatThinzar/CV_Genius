package com.smtz.cvgenius.presentation.details.achievements

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
import com.smtz.cvgenius.databinding.ActivityAchievementBinding
import com.smtz.cvgenius.domain.model.*
import com.smtz.cvgenius.domain.repository.CvModel
import com.smtz.cvgenius.presentation.details.AddSkillAchievementAdapter
import com.smtz.cvgenius.presentation.details.ButtonSaveSkillAchievementDelegate
import com.smtz.cvgenius.presentation.details.SkillAchievementViewPod
import com.smtz.cvgenius.utils.achievement
import com.smtz.cvgenius.utils.setUpTitleAndButton

class AchievementActivity : BaseActivity<ActivityAchievementBinding>(),
    ButtonSaveSkillAchievementDelegate {

    private val ANIMATION_DURATION = 250L
    private val mToolbarHeight = 330
    private var isExpanded = true

    private var mAddDetailAdapter = AddSkillAchievementAdapter(this, skillAchiObjSign = achievement)
    private lateinit var mAchievementViewPod: SkillAchievementViewPod

    private var mCvModel: CvModel = CvModelImpl

    private var mCvVO: CvVO? = null
    private var mAchievementList: MutableList<AchievementVO> = mutableListOf()

    override val binding: ActivityAchievementBinding by lazy {
        ActivityAchievementBinding.inflate(layoutInflater)
    }

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, AchievementActivity::class.java)
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
        if (mCvVO?.achievements != null) {
            mAchievementList = mCvVO?.achievements!!

            mAddDetailAdapter.setNewData(skillList = listOf(), achievementList = mAchievementList)
        }
    }

    private fun setUpViewPods() {
        mAchievementViewPod = binding.root.findViewById(R.id.vpAddAchievement)
        mAchievementViewPod.setUpSkillAchiObjSignViewPod(
            delegate = this,
            changeBtnAdd = true,
            skillAchiObjSign = achievement
        )
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
        binding.rvSkills.apply {
            adapter = mAddDetailAdapter
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        }
    }


    private fun collapseToolbar() {
        if (mToolbarHeight == binding.frameLayout.height) {
            setUpTitleAndButton(
                expand = false,
                binding.tvTitle,
                binding.btnBack,
                binding.frameLayout
            )

            binding.tvTitle.textSize = 20F

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
            setUpTitleAndButton(
                expand = true,
                binding.tvTitle,
                binding.btnBack,
                binding.frameLayout
            )

            binding.tvTitle.textSize = 24F

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

    override fun onTapSave(
        skillsVO: SkillsVO?,
        achievementVO: AchievementVO?
    ) {
        achievementVO?.let {
            val iterator = mAchievementList.iterator()
            while (iterator.hasNext()) {
                val item = iterator.next()
                if (item.id == achievementVO.id) {
                    iterator.remove()
                }
            }
            mAchievementList.add(achievementVO)
            mCvVO!!.achievements = mAchievementList
            mCvModel.insertCV(mCvVO!!)
            mAddDetailAdapter.setNewData(skillList = listOf(), achievementList = mAchievementList)
            Toast.makeText(applicationContext, "Saved", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onTapDelete(id: Long, skillOrAchievement: String) {
        if (skillOrAchievement == achievement) {
            val iterator = mAchievementList.iterator()
            while (iterator.hasNext()) {
                val item = iterator.next()
                if (item.id == id) {
                    iterator.remove()
                }
            }
            mCvModel.insertCV(mCvVO!!)
            mAddDetailAdapter.setNewData(skillList = listOf(), achievementList = mAchievementList)
            Toast.makeText(applicationContext, "Deleted", Toast.LENGTH_SHORT).show()
        }
    }

}