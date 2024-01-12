package com.smtz.cvgenius.common.components

import com.smtz.cvgenius.domain.model.CvVO

class CvSingleton private constructor() {

    // singleton က ဘယ်မှာပြောင်းပြောင်း obj တစ်ခုထဲကိုပဲ reference လုပ်နေလို့ အတူတူပဲရတယ်/ can be used instead of serializable or parcelable without passing the object through the intent
    var cvVO: CvVO? = null
    
    companion object {
        val instance: CvSingleton by lazy {
            Holder.INSTANCE
        }
    }

    private object Holder {
        val INSTANCE = CvSingleton()
    }

    fun cleanup() {
        cvVO = null
    }
}