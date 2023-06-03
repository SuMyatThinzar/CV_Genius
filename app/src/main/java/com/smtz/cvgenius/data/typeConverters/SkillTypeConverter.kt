package com.smtz.cvgenius.data.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smtz.cvgenius.domain.model.EducationDetailVO
import com.smtz.cvgenius.domain.model.SkillsVO

class SkillTypeConverter {
    @TypeConverter
    fun toString(skills : List<SkillsVO>?) : String{
        return Gson().toJson(skills)
    }
    @TypeConverter
    fun toSkillsVO(skillsVOJsonString: String) : List<SkillsVO>? {
        val skillsVOType = object : TypeToken<List<SkillsVO>?>() {}.type
        return Gson().fromJson(skillsVOJsonString, skillsVOType)
    }
}