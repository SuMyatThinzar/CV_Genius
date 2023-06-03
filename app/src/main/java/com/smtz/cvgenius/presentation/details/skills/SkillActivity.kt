package com.smtz.cvgenius.presentation.details.skills

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
import com.smtz.cvgenius.databinding.ActivitySkillBinding
import com.smtz.cvgenius.domain.model.*
import com.smtz.cvgenius.domain.repository.CvModel
import com.smtz.cvgenius.presentation.details.AddSkillAchievementAdapter
import com.smtz.cvgenius.presentation.details.ButtonSaveSkillAchievementDelegate
import com.smtz.cvgenius.presentation.details.SkillAchievementViewPod
import com.smtz.cvgenius.utils.setUpTitleAndButton
import com.smtz.cvgenius.utils.skill

class SkillActivity : BaseActivity<ActivitySkillBinding>(),
    ButtonSaveSkillAchievementDelegate {

    private val ANIMATION_DURATION = 250L
    private val mToolbarHeight = 330
    private var isExpanded = true

    private var mAddDetailAdapter = AddSkillAchievementAdapter(this, skillAchiObjSign = skill)
    private lateinit var mSkillAchiObjSignViewPod: SkillAchievementViewPod

    private var mCvModel: CvModel = CvModelImpl

    private var mCvVO: CvVO? = null
    private var mSkillList: MutableList<SkillsVO> = mutableListOf()

    override val binding: ActivitySkillBinding by lazy {
        ActivitySkillBinding.inflate(layoutInflater)
    }

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, SkillActivity::class.java)
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
        if (mCvVO?.skills != null) {
            mSkillList = mCvVO?.skills!!

            mAddDetailAdapter.setNewData(skillList = mSkillList, achievementList =  listOf())
        }
    }

    private fun setUpViewPods() {
        mSkillAchiObjSignViewPod = binding.root.findViewById(R.id.vpAddSkill)
        mSkillAchiObjSignViewPod.setUpSkillAchiObjSignViewPod(delegate = this, changeBtnAdd = true, skillAchiObjSign = skill)
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

    override fun onTapSave(
        skillsVO: SkillsVO?,
        achievementVO: AchievementVO?,
    ) {
        skillsVO?.let{
            val iterator = mSkillList.iterator()
            while (iterator.hasNext()) {
                val item = iterator.next()
                if (item.id == skillsVO.id) {
                    iterator.remove()
                }
            }
            mSkillList.add(skillsVO)
            mCvVO!!.skills = mSkillList
            mCvModel.insertCV(mCvVO!!)
            mAddDetailAdapter.setNewData(skillList = mSkillList, achievementList = listOf())
            Toast.makeText(applicationContext, "Saved", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onTapDelete(id: Long, skillOrAchievement: String) {
        if (skillOrAchievement == skill) {
            val iterator = mSkillList.iterator()
            while (iterator.hasNext()) {
                val item = iterator.next()
                if (item.id == id) {
                    iterator.remove()
                }
            }
            mCvModel.insertCV(mCvVO!!)
            mAddDetailAdapter.setNewData(skillList = mSkillList, achievementList = listOf())
            Toast.makeText(applicationContext, "Deleted", Toast.LENGTH_SHORT).show()
        }
    }

}