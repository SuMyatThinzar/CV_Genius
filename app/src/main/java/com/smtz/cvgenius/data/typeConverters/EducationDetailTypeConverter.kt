package com.smtz.cvgenius.data.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smtz.cvgenius.domain.model.EducationDetailVO

class EducationDetailTypeConverter {
    @TypeConverter
    fun toString(educationDetails : List<EducationDetailVO>?) : String{
        return Gson().toJson(educationDetails)
    }
    @TypeConverter
    fun toEducationDetailVO(educationDetailsJsonString: String) : List<EducationDetailVO>? {
        val educationDetailVOType = object : TypeToken<List<EducationDetailVO>?>() {}.type
        return Gson().fromJson(educationDetailsJsonString, educationDetailVOType)
    }
}