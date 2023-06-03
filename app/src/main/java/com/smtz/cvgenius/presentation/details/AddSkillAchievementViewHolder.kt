package com.smtz.cvgenius.presentation.details

import androidx.recyclerview.widget.RecyclerView
import com.smtz.cvgenius.R
import com.smtz.cvgenius.databinding.ViewHolderAddSkillAchievementObjectiveSignBinding
import com.smtz.cvgenius.domain.model.AchievementVO
import com.smtz.cvgenius.domain.model.SkillsVO

class AddSkillAchievementViewHolder(
    private val binding: ViewHolderAddSkillAchievementObjectiveSignBinding,
    private val delegate: ButtonSaveSkillAchievementDelegate,
    private val skillAchiObjSign: String
    ) : RecyclerView.ViewHolder(binding.root)
{

//    private var mData: CvVO? = null

    private var mSkillAchiObjSignViewPod : SkillAchievementViewPod =
        itemView.findViewById(R.id.vpAddSkillAchievementObjectiveSign)

    init {

        mSkillAchiObjSignViewPod.setUpSkillAchiObjSignViewPod(delegate = delegate, changeBtnAdd = false, skillAchiObjSign = skillAchiObjSign)

    }

    fun bindSkillData(data: SkillsVO) {
//        mData = data
        mSkillAchiObjSignViewPod.setUpSkillData(data)

    }
    fun bindAchievementData(data: AchievementVO) {
//        mData = data
        mSkillAchiObjSignViewPod.setUpAchievementData(data)

    }
}