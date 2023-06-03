package com.smtz.cvgenius.data.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smtz.cvgenius.domain.model.EducationDetailVO
import com.smtz.cvgenius.domain.model.ProjectDetailVO

class ProjectDetailTypeConverter {
    @TypeConverter
    fun toString(projectDetails : List<ProjectDetailVO>?) : String{
        return Gson().toJson(projectDetails)
    }
    @TypeConverter
    fun toProjectDetailVO(str: String) : List<ProjectDetailVO>? {
        val projectDetailVOType = object : TypeToken<List<ProjectDetailVO>?>() {}.type
        return Gson().fromJson(str, projectDetailVOType)
    }
}