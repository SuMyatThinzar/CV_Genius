package com.smtz.cvgenius.data.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smtz.cvgenius.domain.model.EducationDetailVO
import com.smtz.cvgenius.domain.model.ReferenceVO

class ReferenceTypeConverter {
    @TypeConverter
    fun toString(referenceVO : ReferenceVO?) : String{
        return Gson().toJson(referenceVO)
    }
    @TypeConverter
    fun toReferenceVO(referencesJsonString: String) : ReferenceVO? {
        val referenceVOType = object : TypeToken<ReferenceVO?>() {}.type
        return Gson().fromJson(referencesJsonString, referenceVOType)
    }
}