package com.smtz.cvgenius.data.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smtz.cvgenius.domain.model.PersonalDetailVO

class PersonalDetailTypeConverter {

    @TypeConverter
    fun toString(personalDetail : PersonalDetailVO?) : String{
        return Gson().toJson(personalDetail)
    }
    @TypeConverter
    fun toPersonalDetailVO(str: String) : PersonalDetailVO? {
        val personalDetailVOType = object : TypeToken<PersonalDetailVO?>() {}.type
        return Gson().fromJson(str, personalDetailVOType)
    }
}