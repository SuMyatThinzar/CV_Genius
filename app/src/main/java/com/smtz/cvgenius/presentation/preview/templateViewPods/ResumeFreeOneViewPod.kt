package com.smtz.cvgenius.presentation.preview.templateViewPods

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.graphics.Typeface
import android.os.Build
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.smtz.cvgenius.R
import com.smtz.cvgenius.common.CvSingleton
import com.smtz.cvgenius.databinding.ViewPodZresumeFreeOneBinding
import com.smtz.cvgenius.domain.model.AchievementVO
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.EducationDetailVO
import com.smtz.cvgenius.domain.model.ProjectDetailVO
import com.smtz.cvgenius.domain.model.SkillsVO
import com.smtz.cvgenius.domain.model.WorkExperienceVO
import com.smtz.cvgenius.presentation.preview.utils.setUpContentVisibilityResumeSecondOne

@RequiresApi(Build.VERSION_CODES.N)
class ResumeFreeOneViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : BaseViewPod(context, attrs) {

    private lateinit var binding: ViewPodZresumeFreeOneBinding

    private var mEducationDetailList: List<EducationDetailVO> = listOf()
    private var mWorkExperienceList: List<WorkExperienceVO> = listOf()
    private var mProjectDetailList: List<ProjectDetailVO> = listOf()
    private var mSkillList: List<SkillsVO> = listOf()
    private var mAchievementList: List<AchievementVO> = listOf()

    private lateinit var containerFirstPageRightSide: LinearLayout
    private lateinit var containerFirstPageLeftSide: LinearLayout
    private lateinit var  containerSecondPageRightSide: LinearLayout
    private lateinit var  containerSecondPageLeftSide: LinearLayout

    private var mCvVO: CvVO? = null
    private var isInitialSetupDone = false
    private var isSentToSecondPage = false

    override fun onFinishInflate() {
        binding = ViewPodZresumeFreeOneBinding.bind(this)
        mCvVO = CvSingleton.instance.cvVO!!
//        setUpScreenHeight()
        containerFirstPageRightSide = binding.containerFirstPageRightSide
        containerFirstPageLeftSide = binding.containerFirstPageLeftSide
        containerSecondPageRightSide = binding.containerSecondPageRightSide
        containerSecondPageLeftSide = binding.containerSecondPageLeftSide

//        setUpData()
        super.onFinishInflate()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus && !isInitialSetupDone) {
            setUpData()
            isInitialSetupDone = true
        }
    }

    private fun setUpData() {
        if (mCvVO?.profileImage != null) {
            val bitmap = BitmapFactory.decodeByteArray(mCvVO?.profileImage, 0, mCvVO?.profileImage!!.size)
            binding.ivProfileImage.setImageBitmap(bitmap)
        } else {
            binding.ivProfileImage.visibility = View.GONE
        }

        // 1.
        val name = "${mCvVO?.personalDetails?.firstName} ${mCvVO?.personalDetails?.lastName}"
        binding.tvName.text = name
        binding.tvPosition.text = mCvVO?.personalDetails?.professionalTitle

        binding.apply {
            if (mCvVO?.personalDetails?.address?.isNotEmpty() == true) setUpContentVisibilityResumeSecondOne(tvAddress, "Address: ${mCvVO?.personalDetails?.address}", null)  else tvAddress.visibility = View.GONE
//            if (mCvVO?.personalDetails?.contact?.isNotEmpty() == true) setUpContentVisibilityResumeSecondOne(tvPhone, "Phone: ${mCvVO?.personalDetails?.contact}",  null)  else tvPhone.visibility = View.GONE
//            if (mCvVO?.personalDetails?.email?.isNotEmpty() == true) setUpContentVisibilityResumeSecondOne(tvEmail, "Email: ${mCvVO?.personalDetails?.email}",  null)  else tvEmail.visibility = View.GONE
//            if (mCvVO?.personalDetails?.dateOfBirth?.isNotEmpty() == true) setUpContentVisibilityResumeSecondOne(tvDateOfBirth, "Date of Birth: ${mCvVO?.personalDetails?.dateOfBirth}",  null)  else tvDateOfBirth.visibility = View.GONE
//            if (mCvVO?.personalDetails?.nationality?.isNotEmpty() == true) setUpContentVisibilityResumeSecondOne(tvNationality, "Nationality: ${mCvVO?.personalDetails?.nationality}",  null)  else tvNationality.visibility = View.GONE
//            if (mCvVO?.personalDetails?.gender?.isNotEmpty() == true) setUpContentVisibilityResumeSecondOne(tvWebsite, "Website: ${mCvVO?.personalDetails?.gender}",  null)  else tvWebsite.visibility = View.GONE
//
            setUpPersonalDetails()

            // 2.
            if (mCvVO?.objective?.isNotEmpty() == true) {
                setUpContentVisibilityResumeSecondOne(tvObjective, mCvVO?.objective, containerObjective)
            } else {
                containerObjective.visibility = View.GONE
                viewBelowObjective.visibility = View.GONE   // invisible underline
            }
        }

        // 3.
        if (mCvVO?.educationDetails?.isNotEmpty() == true) {
            mEducationDetailList = mCvVO?.educationDetails!!
//            setUpWorkExp()
        } else {
            binding.containerEducation.visibility = View.GONE
        }

        // 4.
        if (mCvVO?.workExperiences?.isNotEmpty() == true) {
            mWorkExperienceList = mCvVO?.workExperiences!!
            setUpWorkExp()
        } else {
            binding.containerWorkExp.visibility = View.GONE
        }

        // 5.
        if (mCvVO?.projectDetails?.isNotEmpty() == true) {
            mProjectDetailList = mCvVO?.projectDetails!!
//            setUpWorkExp()
        } else {
            binding.containerProject.visibility = View.GONE
        }

        // 6.
        if (mCvVO?.skills?.isNotEmpty() == true) {
            mSkillList = mCvVO?.skills!!
//            setUpWorkExp()
        } else {
            binding.containerSkill.visibility = View.GONE
        }

        // 7.
        if (mCvVO?.achievements?.isNotEmpty() == true) {
            mAchievementList = mCvVO?.achievements!!
//            setUpWorkExp()
        } else {
            binding.containerAchievement.visibility = View.GONE
        }

        // 8.
//        if (mCvVO?.signature == null) {
//            mAchievementList = mCvVO?.achievements!!
////            setUpWorkExp()
//        } else {
//            binding.containerAchievement.visibility = View.GONE
//        }

        if (mCvVO?.educationDetails?.isEmpty() == true &&
            mCvVO?.workExperiences?.isEmpty() == true &&
            mCvVO?.projectDetails?.isEmpty() == true &&
            mCvVO?.skills?.isEmpty() == true &&
            mCvVO?.achievements?.isEmpty() == true &&
            mCvVO?.signature == null &&
            mCvVO?.references == null) {
            binding.viewSecondBottomost.visibility = View.GONE
        }

    }

    private fun setUpPersonalDetails() {

        binding.apply {
            if (mCvVO?.personalDetails?.contact?.isNotEmpty() == true) {
                val phone = setLayouts("Phone: ${mCvVO?.personalDetails?.contact}   ", 7.13F, R.font.nunito_medium, "", 0, R.color.black, null )
                gridLayoutPersonalDetail.addView(phone)
            }

            if (mCvVO?.personalDetails?.email?.isNotEmpty() == true) {
                val email = setLayouts("Email: ${mCvVO?.personalDetails?.email}   ", 7.13F, R.font.nunito_medium, "", 0, R.color.black, null )
                gridLayoutPersonalDetail.addView(email)
            }

            if (mCvVO?.personalDetails?.dateOfBirth?.isNotEmpty() == true) {
                val dateOfBirth = setLayouts("DateOfBirth: ${mCvVO?.personalDetails?.dateOfBirth}   ", 7.13F, R.font.nunito_medium, "", 0, R.color.black, null )
                gridLayoutPersonalDetail.addView(dateOfBirth)
            }

            if (mCvVO?.personalDetails?.nationality?.isNotEmpty() == true) {
                val nationality = setLayouts("Nationality: ${mCvVO?.personalDetails?.nationality}   ", 7.13F, R.font.nunito_medium, "", 0, R.color.black, null )
                gridLayoutPersonalDetail.addView(nationality)
            }

            if (mCvVO?.personalDetails?.gender?.isNotEmpty() == true) {
                val website = setLayouts("Website: ${mCvVO?.personalDetails?.gender}   ", 7.13F, R.font.nunito_medium, "", 0, R.color.black, null )
                gridLayoutPersonalDetail.addView(website)
            }
        }
    }

    private fun setUpWorkExp() {
        mWorkExperienceList.forEachIndexed { index, workExperienceVO ->

            if (index == 0) {
                binding.tvWorkPosition.text = workExperienceVO.position
                binding.tvCompanyNameAndDate.text =
                    "${workExperienceVO.company} | ${workExperienceVO.startDate}-${workExperienceVO.endDate}"
                binding.tvWorkDescription.text = workExperienceVO.description
                return@forEachIndexed
            }

            val childLayout = LinearLayout(context)
            childLayout.orientation = VERTICAL

            // Set a unique ID for the childLayout
            childLayout.id = View.generateViewId()

            val workPositionTextView =
                setLayouts(workExperienceVO.position, 9.92f, R.font.nunito_medium, "top")
            childLayout.addView(workPositionTextView)

            val companyNameAndDateTextView = setLayouts(
                "${workExperienceVO.company} | ${workExperienceVO.startDate}-${workExperienceVO.endDate}",
                7.13f, R.font.nunito_medium, "bottom"
            )
            childLayout.addView(companyNameAndDateTextView)

            workExperienceVO.description?.let {
                val workDescriptionTextView = setLayouts(it, 7.13f, R.font.nunito_medium, "null")
                childLayout.addView(workDescriptionTextView)
            }

            binding.containerWorkExp.addView(childLayout)

            val parentView = findViewById<ViewGroup>(R.id.firstRootView)

            val handler = Handler()
            handler.post {
                val parentRect = Rect()
                val childRect = Rect()

                binding.containerWorkExp.getGlobalVisibleRect(parentRect)

                // Adjust the parent visible rect to account for padding
                parentRect.left += parentView.paddingLeft
                parentRect.top += parentView.paddingTop
                parentRect.right -= parentView.paddingRight
                parentRect.bottom -= parentView.paddingBottom

                val parentLocation = IntArray(2)
                binding.containerWorkExp.getLocationOnScreen(parentLocation)

                childLayout.getGlobalVisibleRect(childRect)

                val childLocation = IntArray(2)
                childLayout.getLocationOnScreen(childLocation)

                childRect.offset(-childLocation[0], -childLocation[1])
                parentRect.offset(-parentLocation[0], -parentLocation[1])

                val isFullyVisible = Rect.intersects(parentRect, childRect) && childRect.height() >= childLayout.height
                val isPartiallyVisible = Rect.intersects(parentRect, childRect)

                val globalVisibilityRectangle = Rect()
                childLayout.getGlobalVisibleRect(globalVisibilityRectangle)

                val visibleHeight = globalVisibilityRectangle.right - globalVisibilityRectangle.left
                val visibleWidth = globalVisibilityRectangle.bottom - globalVisibilityRectangle.top

                val actualHeight: Int = childLayout.measuredHeight
                val actualWidth: Int = childLayout.measuredWidth

                Log.d("asdfasdfasdf", "partially visible $visibleHeight $actualHeight $index")

                if (visibleHeight == actualHeight) {
                    Log.d("asdfasdfasdf", "fully visible $visibleHeight $actualHeight $index")
                }
                if (visibleHeight in 1 until actualHeight) {
                    Log.d("asdfasdfasdf", "partially visible $visibleHeight $actualHeight $index")
                }
                if (visibleHeight < actualHeight && visibleHeight <= 0) {
                    Log.d("asdfasdfasdf", "completely covered $visibleHeight $actualHeight $index")
                }

                if (isFullyVisible) {
                    // The target view is fully visible
//                    Log.d("asdfasdfasdf", "fully visible $index")
                } else if (isPartiallyVisible) {
                    // The target view is partially visible
//                    Log.d("asdfasdfasdf", "partially visible $index")
                    setUpTheChildViews(2, childLayout)
                } else {
                    // The target view is completely covered
//                    Log.d("asdfasdfasdf", "completely covered $index")
                    setUpTheChildViews(2, childLayout)
                }
            }
        }
//        setUpLayoutScreen(childViewList)
    }

    private fun setUpTheChildViews(pageNumber: Int, childLayout: LinearLayout) {

        if (pageNumber == 1) {
            binding.containerWorkExp.addView(childLayout)
        }
        if (pageNumber == 2) {
            binding.secondRootView.visibility = View.VISIBLE
            binding.viewFirstBottomost.visibility = View.INVISIBLE

            val oldParent = childLayout.parent as? ViewGroup
            oldParent?.removeView(childLayout) // Remove the childLayout from the current parent

            containerSecondPageRightSide.addView(childLayout)

            // move project to second page
            if (!isSentToSecondPage) {
                val oldParentProjectDetail = binding.containerProject.parent as? ViewGroup
                oldParentProjectDetail?.removeView(binding.containerProject)
                containerSecondPageRightSide.addView(binding.containerProject)
                isSentToSecondPage = true
            }
        }
    }

    private fun setLayouts(text: String, textSize: Float, fontFamily: Int, margin: String): TextView {
        val textView = TextView(context)
        textView.text = text

        // textSize
        textView.textSize = textSize // Set text size in SP

        // textColor
        textView.setTextColor(resources.getColorStateList(R.color.black))

        val marginTopBottom = resources.getDimensionPixelSize(R.dimen.margin_small)

        // fontFamily
        val typeface = ResourcesCompat.getFont(context, fontFamily)

        val layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        when (margin) {
            "top" -> layoutParams.setMargins(0, marginTopBottom, 0, 0)
            "bottom" -> layoutParams.setMargins(0, 0, 0, marginTopBottom)
            "topbottom" -> layoutParams.setMargins(0, marginTopBottom, 0, marginTopBottom)
            "end" -> layoutParams.setMargins(0, 0, marginTopBottom, 0)
        }
        textView.typeface = typeface
        textView.layoutParams = layoutParams

        return textView
    }

//    private fun setUpScreenHeight() {
//        val ratio = 0.707f
//        val desiredHeight = (screenWidth * ratio).toInt()
//        screenHeight = desiredHeight
//
//        val layoutParams = binding.root.layoutParams
//        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
//        layoutParams.height = desiredHeight
//        binding.root.layoutParams = layoutParams
//    }

    // reusable func
    private fun setLayouts(
        text: String,
        textSize: Float,
        fontFamily: Int,
        marginType: String,
        marginSize: Int,
        textColor: Int,
        bold: String?
    ): TextView {

        val textView = TextView(context)
        textView.text = text

        textView.textSize = textSize   // textSize
        textView.setTextColor(resources.getColorStateList(textColor))   // textColor
        textView.letterSpacing = 0.06f  // letterSpacing
        var marginTopBottom = 0
        if ( marginSize != 0) marginTopBottom = resources.getDimensionPixelSize(marginSize)

        // fontFamily
        val typeface = ResourcesCompat.getFont(context, fontFamily)

        // fontStyle
        if (bold != null) {
            val textStyle = Typeface.BOLD
            textView.setTypeface(typeface, textStyle)
        } else textView.typeface = typeface

        val layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        when (marginType) {
            "top" -> layoutParams.setMargins(0, marginTopBottom, 0, 0)
            "bottom" -> layoutParams.setMargins(0, 0, 0, marginTopBottom)
            "topbottom" -> layoutParams.setMargins(0, marginTopBottom, 0, marginTopBottom)
            "end" -> layoutParams.setMargins(0, 0, marginTopBottom, 0)
        }

        textView.layoutParams = layoutParams

        return textView
    }

}