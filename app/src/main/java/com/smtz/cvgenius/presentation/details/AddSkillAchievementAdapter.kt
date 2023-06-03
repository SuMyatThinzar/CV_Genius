package com.smtz.cvgenius.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smtz.cvgenius.databinding.ViewHolderAddSkillAchievementObjectiveSignBinding
import com.smtz.cvgenius.domain.model.AchievementVO
import com.smtz.cvgenius.domain.model.SkillsVO

class AddSkillAchievementAdapter(private var delegate: ButtonSaveSkillAchievementDelegate, private var skillAchiObjSign: String) : RecyclerView.Adapter<AddSkillAchievementViewHolder>() {

    private var mSkillsList: List<SkillsVO> = listOf()
    private var mAchievementList: List<AchievementVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddSkillAchievementViewHolder {
        val binding = ViewHolderAddSkillAchievementObjectiveSignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddSkillAchievementViewHolder(binding, delegate, skillAchiObjSign)
    }

    override fun onBindViewHolder(holder: AddSkillAchievementViewHolder, position: Int) {
        if (mSkillsList.isNotEmpty())  holder.bindSkillData(mSkillsList[position])
        else                           holder.bindAchievementData(mAchievementList[position])
    }

    override fun getItemCount(): Int {
        return if (mSkillsList.isNotEmpty()) mSkillsList.count() else mAchievementList.count()
    }

    fun setNewData(skillList: List<SkillsVO>, achievementList: List<AchievementVO>){
        if (skillList.isNotEmpty())        mSkillsList = skillList
        if (achievementList.isNotEmpty())  mAchievementList = achievementList
        notifyDataSetChanged()
    }
}
