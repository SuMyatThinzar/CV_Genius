package com.smtz.cvgenius.data.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smtz.cvgenius.domain.model.EducationDetailVO
import com.smtz.cvgenius.domain.model.WorkExperienceVO

class WorkExperienceTypeConverter {
    @TypeConverter
    fun toString(workExperiences : List<WorkExperienceVO>?) : String{
        return Gson().toJson(workExperiences)
    }
    @TypeConverter
    fun toWorkExperienceVO(workExperienceJsonString: String) : List<WorkExperienceVO>? {
        val workExperienceVOType = object : TypeToken<List<WorkExperienceVO>?>() {}.type
        return Gson().fromJson(workExperienceJsonString, workExperienceVOType)
    }
}