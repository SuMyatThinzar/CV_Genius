package com.smtz.cvgenius.presentation.details

import com.smtz.cvgenius.domain.model.*

interface ButtonSaveSkillAchievementDelegate {
    fun onTapSave(skillsVO: SkillsVO?, achievementVO: AchievementVO?)
    fun onTapDelete(id: Long, skillOrAchievement: String)
}