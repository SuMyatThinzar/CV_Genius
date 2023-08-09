package com.smtz.cvgenius.utils

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.smtz.cvgenius.R
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.DetailButtonLabel

//val templateList = mapOf<Int, Int>(
//    0 to R.drawable.resume_free_one,
//    1 to R.drawable.resume_free_two,
//    2 to R.drawable.resume_second_one,
//    3 to R.drawable.resume_second_two,
//    4 to R.drawable.resume_second_three,
//    5 to R.drawable.resume_second_four,
//    6 to R.drawable.resume_third_one,
//    7 to R.drawable.resume_third_two,
//    8 to R.drawable.resume_third_three,
//    9 to R.drawable.resume_third_four,
//    10 to R.drawable.resume_third_five,
//)

val tabList = listOf("All", "Professional", "Modern")

val detailButtonList = listOf(
    DetailButtonLabel(1, R.drawable.ic_profile_create, "Personal Detail"),
    DetailButtonLabel(2, R.drawable.ic_education, "Education Detail"),
    DetailButtonLabel(3, R.drawable.ic_experience, "Work Experience"),
    DetailButtonLabel(4, R.drawable.ic_skill, "Skills"),
    DetailButtonLabel(5, R.drawable.ic_achievement, "Achievements"),
    DetailButtonLabel(6, R.drawable.ic_objective, "Objectives"),
    DetailButtonLabel(7, R.drawable.ic_project, "Project Detail"),
    DetailButtonLabel(8, R.drawable.ic_reference, "Reference"),
    DetailButtonLabel(9, R.drawable.ic_signature, "Signature"),
)

fun setUpTitleAndButton(expand: Boolean, tvTitle: TextView, btnBack: ImageView, frameLayout: FrameLayout) {
    val layoutParams = tvTitle.layoutParams as ViewGroup.MarginLayoutParams
    tvTitle.layoutParams = layoutParams

    val layoutParamsBtn = btnBack.layoutParams as ViewGroup.MarginLayoutParams
    btnBack.layoutParams = layoutParamsBtn

    if (expand) {
        layoutParams.bottomMargin = 24
        layoutParamsBtn.bottomMargin = 24
        frameLayout.elevation = 0.0f
        tvTitle.textSize = 24F
    } else {
        layoutParams.bottomMargin = 0
        layoutParamsBtn.bottomMargin = 0
        frameLayout.elevation = 10.0f
        tvTitle.textSize = 20F
    }
}
const val INTERSTITIAL_TAG = "INTERSTITIAL_AD_TAG"
const val skill = "SKILL"
const val achievement = "ACHIEVEMENT"
const val workExperience = "WORK EXPERIENCE"
const val projectDetail = "PROJECT DETAIL"
const val education = "EDUCATION DETAIL"
const val signature = "SIGNATURE"
const val reference = "REFERENCE"

const val PREVIEW_ACTIVITY = "PreviewActivity"
const val CREATE_CV_ACTIVITY = "CreateCVActivity"
const val BACK_PRESSED = "BACK_PRESSED"
