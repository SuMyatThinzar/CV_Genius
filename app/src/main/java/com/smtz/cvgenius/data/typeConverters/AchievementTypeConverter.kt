package com.smtz.cvgenius.data.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smtz.cvgenius.domain.model.AchievementVO
import com.smtz.cvgenius.domain.model.EducationDetailVO

class AchievementTypeConverter {
    @TypeConverter
    fun toString(achievements : List<AchievementVO>?) : String{
        return Gson().toJson(achievements)
    }
    @TypeConverter
    fun toAchievementVO(achievementJsonString: String) : List<AchievementVO>? {
        val achievementVOType = object : TypeToken<List<AchievementVO>?>() {}.type
        return Gson().fromJson(achievementJsonString, achievementVOType)
    }
}