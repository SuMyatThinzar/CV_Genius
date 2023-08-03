package com.smtz.cvgenius.presentation.preview.templateViewPods

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Typeface
import android.os.Handler
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.AttributeSet
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.smtz.cvgenius.R
import com.smtz.cvgenius.common.CvSingleton
import com.smtz.cvgenius.databinding.ViewPodZresumeSecondTwoBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.EducationDetailVO
import com.smtz.cvgenius.domain.model.WorkExperienceVO
import com.smtz.cvgenius.presentation.preview.utils.getCurrentPageHeight
import com.smtz.cvgenius.presentation.preview.utils.setUpContentVisibilityResumeSecondOne
import com.smtz.cvgenius.utils.education
import com.smtz.cvgenius.utils.projectDetail
import com.smtz.cvgenius.utils.workExperience

class ResumeSecondTwoViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : BaseViewPod(context, attrs) {

    private lateinit var binding: ViewPodZresumeSecondTwoBinding

    private var mWorkExperienceList: List<WorkExperienceVO> = listOf()
    private var mEducationList: List<EducationDetailVO> = listOf()

    private var experienceContentViewList: MutableList<View> = mutableListOf()
    private var educationContentViewList: MutableList<View> = mutableListOf()

    private lateinit var containerWorkExp: LinearLayout
    private lateinit var containerFirstPageRightSide: LinearLayout
    private lateinit var containerFirstPageLeftSide: LinearLayout
    private lateinit var containerSecondPageRightSide: LinearLayout
    private lateinit var containerSecondPageLeftSide: LinearLayout

    private lateinit var currentPageLayoutRight: LinearLayout         // containerFirstPageRightSide  containerSecondPageRightSide
    private lateinit var currentPageLayoutLeft: LinearLayout         // containerFirstPageLeftSide  containerSecondPageLeftSide

    private var mCvVO: CvVO? = null
    private var isInitialSetupDone = false
    private var isSentToSecondPageRight = false
    private var isSentToSecondPageLeft = false
    private var currentPageRight = 1
    private var currentPageLeft = 1

    private val density = resources.displayMetrics.density
    private val paddingInPx = (20 * density).toInt()

    // 2 right
    private var rootViewAbsoluteHeightRight = 0
    private var currentPageHeightRight = 0F

    // 2 left
    private var rootViewAbsoluteHeightLeft = 0
    private var currentPageHeightLeft = 0F

    override fun onFinishInflate() {
        binding = ViewPodZresumeSecondTwoBinding.bind(this)
        mCvVO = CvSingleton.instance.cvVO!!
        containerWorkExp = binding.containerExperience
        containerFirstPageRightSide = binding.containerFirstPageRightSide
        containerFirstPageLeftSide = binding.containerFirstPageLeftSide
        containerSecondPageRightSide = binding.containerSecondPageRightSide
        containerSecondPageLeftSide = binding.containerSecondPageLeftSide

        // 1
        currentPageLayoutRight = containerFirstPageRightSide
        currentPageLayoutLeft = containerFirstPageLeftSide

        setUpResumeSecondTemplates(mCvVO?.templateId!!)

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

    private fun setUpResumeSecondTemplates(templateId: Int) {
        when (templateId) {
            2 -> {
                containerFirstPageLeftSide.setBackgroundResource(R.color.colorResumeSecondOne)
                containerSecondPageLeftSide.setBackgroundResource(R.color.colorResumeSecondOne)
            }
            3 -> {
                containerFirstPageLeftSide.setBackgroundResource(R.color.colorResumeSecondTwo)
                containerSecondPageLeftSide.setBackgroundResource(R.color.colorResumeSecondTwo)
            }
            4 -> {
                containerFirstPageLeftSide.setBackgroundResource(R.color.colorResumeSecondThree)
                containerSecondPageLeftSide.setBackgroundResource(R.color.colorResumeSecondThree)
            }
            5 -> {
                containerFirstPageLeftSide.setBackgroundResource(R.drawable.background_resume_second_four)
                containerSecondPageLeftSide.setBackgroundResource(R.drawable.background_resume_second_four)
            }
            6 -> {
                containerFirstPageLeftSide.setBackgroundResource(R.drawable.background_resume_second_five)
                containerSecondPageLeftSide.setBackgroundResource(R.drawable.background_resume_second_five)
            }
        }
    }

    private fun setUpData() {
        if (mCvVO?.profileImage != null) {
            val bitmap = BitmapFactory.decodeByteArray(mCvVO?.profileImage, 0, mCvVO?.profileImage!!.size)
            binding.ivSelectedImage.setImageBitmap(bitmap)
        } else {
            binding.ivSelectedImage.visibility = View.GONE
        }

        val name = "${mCvVO?.personalDetails?.firstName} ${mCvVO?.personalDetails?.lastName}"
        binding.tvName.text = name
        binding.tvPosition.text = mCvVO?.personalDetails?.professionalTitle
        binding.apply {
            setUpContentVisibilityResumeSecondOne(tvAddress, mCvVO?.personalDetails?.address, tvLabelAddress)
            setUpContentVisibilityResumeSecondOne(tvPhone, mCvVO?.personalDetails?.contact, tvLabelPhone)
            setUpContentVisibilityResumeSecondOne(tvEmail, mCvVO?.personalDetails?.email, tvLabelEmail)
            setUpContentVisibilityResumeSecondOne(tvDateOfBirth, mCvVO?.personalDetails?.dateOfBirth, tvLabelDoB)
            setUpContentVisibilityResumeSecondOne(tvObjective, mCvVO?.objective, null)
        }

        if (mCvVO?.workExperiences?.isNotEmpty() == true) {
            mWorkExperienceList = mCvVO?.workExperiences!!
            setUpWorkExp()       // content တွေကိုအရင်ထည့်လိုက်တယ် ဆန့်ဆန့်မဆန့်ဆန့်
        } else {
            containerWorkExp.visibility = View.GONE
        }

        if (mCvVO?.educationDetails?.isNotEmpty() == true) {
            mEducationList = mCvVO?.educationDetails!!
            setUpEducation()      // content တွေကိုအရင်ထည့်လိုက်တယ် ဆန့်ဆန့်မဆန့်ဆန့်
        } else {
            binding.containerEducationSecondTwo.visibility = View.GONE
        }

        checkContentsVisibility()     // content တွေကိုဆန့်မဆန့်စစ်ပြီး ဟိုဘက်ပို့တယ်
    }

    private fun setUpWorkExp() {

        mWorkExperienceList.forEachIndexed { index, workExperienceVO ->

            val childLayout = LinearLayout(context)
            childLayout.orientation = VERTICAL

            // Set a unique ID for the childLayout
            childLayout.id = View.generateViewId()

            val workExperienceDuration = setLayouts("${workExperienceVO.startDate} - ${workExperienceVO.endDate}", 6.5f, R.font.lato_light, "top", R.color.black, null)
            childLayout.addView(workExperienceDuration)

            val companyNameTextView = setLayouts(workExperienceVO.company, 6.5f, R.font.lato_regular, "", R.color.black, "bold")
            childLayout.addView(companyNameTextView)

            val workPositionTextView = setLayouts(workExperienceVO.position, 7.5f, R.font.lato_regular, "", R.color.black, null)
            childLayout.addView(workPositionTextView)

            workExperienceVO.description?.let {
                val workDescriptionTextView = setLayouts(it, 6.5f, R.font.lato_light, "", R.color.black, null)
                childLayout.addView(workDescriptionTextView)
            }

            experienceContentViewList.add(childLayout)

            // အရင်ထည့်ပြီးမှစစ်တာ
            binding.containerExperience.addView(childLayout)
        }
//        addContentToPagesWorkExp(contentList)
    }

    private fun setUpEducation() {
        mEducationList.forEachIndexed { index, educationVO ->

            val childLayout = LinearLayout(context)
            childLayout.orientation = VERTICAL

            val educationDate = setLayouts("${educationVO.startDate} - ${educationVO.endDate}", 6.5f, R.font.lato_light, "top", R.color.white, null)
            childLayout.addView(educationDate)

            val degree = setLayouts(educationVO.diplomaName, 6.8f, R.font.lato_regular, "", R.color.white, "bold")
            childLayout.addView(degree)

            val university = setLayouts("${educationVO.levelOfEducation} at ${educationVO.schoolName}", 6.8f, R.font.lato_regular, "", R.color.white, null)
            childLayout.addView(university)

            educationVO.credentialURL?.let {
                val credentialURL = setLayouts(it, 7.5f, R.font.lato_regular, "", R.color.white, null)
                Linkify.addLinks(credentialURL, Patterns.WEB_URL, "http://")
                credentialURL.autoLinkMask = Linkify.WEB_URLS
                credentialURL.isClickable = true
                credentialURL.setLinkTextColor(resources.getColor(R.color.colorLinkText))
                credentialURL.movementMethod = LinkMovementMethod.getInstance()
                childLayout.addView(credentialURL)
            }

            educationContentViewList.add(childLayout)

            binding.containerEducationSecondTwo.addView(childLayout)
        }
//        addContentToPagesEducation(contentList)
    }

    // reusable func left and right side
    private fun checkContentsVisibility() {

        // 2 right
        rootViewAbsoluteHeightRight = currentPageLayoutRight.height - paddingInPx - paddingInPx
        currentPageHeightRight = getCurrentPageHeight(containerToCheckHeight = containerFirstPageRightSide, 2)

        // 2 left
        rootViewAbsoluteHeightLeft = currentPageLayoutLeft.height - paddingInPx - paddingInPx
        currentPageHeightLeft = getCurrentPageHeight(containerToCheckHeight = containerFirstPageLeftSide, 1)

        Log.d("asdfasdfgfsdfghfgujr56", "$currentPageHeightRight $currentPageHeightLeft")

        val handler = Handler()
        handler.post {
            asynchronouslyCheckContent()
        }
    }

    // reusable func check visibility for all
    private fun asynchronouslyCheckContent() {
        // Education
        for (content in educationContentViewList) {

            // Manually measure and layout the (not need if the layout height is displayed fully) can setUp as val totalHeight = viewPodLayout.height
            val widthMeasureSpec = MeasureSpec.makeMeasureSpec(content.width, MeasureSpec.EXACTLY)
            val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            content.measure(widthMeasureSpec, heightMeasureSpec)
            content.layout(0, 0, content.measuredWidth, content.measuredHeight)

            val contentHeight = content.measuredHeight

            if (currentPageHeightLeft + contentHeight <= rootViewAbsoluteHeightLeft) { // content ကဆန့်တယ်ဆိုရင်

                if (currentPageLeft == 1) {    // Content fits on the current page
                    currentPageHeightLeft += contentHeight
                    setUpTheChildViews(content as LinearLayout, type = education, leftOrRight = "left")
                }
                if (currentPageLeft == 2){     // Add content to the new page
                    setUpTheChildViews(content as LinearLayout, type = education, leftOrRight = "left")
                }
            }

            else {  // content ကမဆန့်ဘူး change all Layouts here
                if (currentPageLeft == 1) {  // page က 1 ဖြစ်ရင်
                    currentPageLeft = 2
                    currentPageLayoutLeft = containerSecondPageLeftSide  // 1
                    rootViewAbsoluteHeightLeft = currentPageLayoutLeft.height - paddingInPx - paddingInPx  // 2
                    currentPageHeightLeft = getCurrentPageHeight(containerToCheckHeight = containerSecondPageLeftSide, 2)  // 3
                }
                // page က already 2 ဖြစ်ရင်
                setUpTheChildViews(content as LinearLayout, type = education, leftOrRight = "left")
            }
        }
        sendOtherLayoutsToAnotherPage(type = education)

        // Work Experience
        var temp = 0
        for (content in experienceContentViewList) {

            // Manually measure and layout the (not need if the layout height is displayed fully) can setUp as val totalHeight = viewPodLayout.height
            val widthMeasureSpec = MeasureSpec.makeMeasureSpec(content.width, MeasureSpec.EXACTLY)
            val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            content.measure(widthMeasureSpec, heightMeasureSpec)
            content.layout(0, 0, content.measuredWidth, content.measuredHeight)

            val contentHeight = content.measuredHeight

            if (currentPageHeightRight + contentHeight <= rootViewAbsoluteHeightRight) {

                if (currentPageRight == 1) {    // Content fits on the current page
                    Log.d("asfddfsasdf", "page1 $temp $currentPageHeightRight + $contentHeight = ${currentPageHeightRight + contentHeight} <= $rootViewAbsoluteHeightRight")
                    temp++
                    currentPageHeightRight += contentHeight
                    setUpTheChildViews(content as LinearLayout, type = workExperience, leftOrRight = "right")
                }
                if (currentPageRight == 2){     // Add content to the new page
                    Log.d("asfddfsasdf", "page2 $temp $currentPageHeightRight + $contentHeight = ${currentPageHeightRight + contentHeight} <= $rootViewAbsoluteHeightRight")
                    temp++
                    setUpTheChildViews(content as LinearLayout, type = workExperience, leftOrRight = "right")
                }
            }

            else {  // change all Layouts here
                temp++

                if (currentPageRight == 1) {

                    currentPageRight = 2
                    currentPageLayoutRight = binding.secondRootViewSecondTwo  // 1
                    rootViewAbsoluteHeightRight = currentPageLayoutRight.height - paddingInPx - paddingInPx  // 2
                    currentPageHeightRight = getCurrentPageHeight(containerToCheckHeight = containerSecondPageRightSide, 2)  // 3

                }
                setUpTheChildViews(content as LinearLayout, type = workExperience, leftOrRight = "right")
                Log.d("asfddfsasdf", "page2 $temp $currentPageHeightRight + $contentHeight = ${currentPageHeightRight + contentHeight} <= $rootViewAbsoluteHeightRight")

            }
        }
        sendOtherLayoutsToAnotherPage(type = workExperience)

    }

    // reusable func add and remove views for pages
    private fun setUpTheChildViews(childLayout: LinearLayout, type: String, leftOrRight: String) {

        if (leftOrRight == "right") {
            if (currentPageRight == 1) {
                val oldParent = childLayout.parent as? ViewGroup
                oldParent?.removeView(childLayout) // Remove the childLayout from the current parent

                if (type == workExperience) containerWorkExp.addView(childLayout)
            }
            if (currentPageRight == 2) {
                binding.secondRootViewSecondTwo.visibility = View.VISIBLE

                val oldParent = childLayout.parent as? ViewGroup
                oldParent?.removeView(childLayout) // Remove the childLayout from the current parent

                containerSecondPageRightSide.addView(childLayout)
            }
        }

        if (leftOrRight == "left") {
            if (currentPageLeft == 1) {
                val oldParent = childLayout.parent as? ViewGroup
                oldParent?.removeView(childLayout) // Remove the childLayout from the current parent

                if (type == education) binding.containerEducationSecondTwo.addView(childLayout)
            }
            if (currentPageLeft == 2) {
                binding.secondRootViewSecondTwo.visibility = View.VISIBLE

                val oldParent = childLayout.parent as? ViewGroup
                oldParent?.removeView(childLayout) // Remove the childLayout from the current parent

                containerSecondPageLeftSide.addView(childLayout)
            }
        }
    }

    // reusable func send other views to page 2
    private fun sendOtherLayoutsToAnotherPage(type: String) {
        when (type) {
            workExperience -> {
                if (currentPageRight == 2) {

                    // move Project and Skill to second page
                    if (!isSentToSecondPageRight) {
                        val oldParentProjectDetail = binding.containerProject.parent as? ViewGroup
                        oldParentProjectDetail?.removeView(binding.containerProject)
                        containerSecondPageRightSide.addView(binding.containerProject)

                        val oldParentSkills = binding.containerSkill.parent as? ViewGroup
                        oldParentSkills?.removeView(binding.containerSkill)
                        containerSecondPageRightSide.addView(binding.containerSkill)
                        isSentToSecondPageRight = true
                    }
                }
            }
            projectDetail -> {
                if (currentPageRight == 2) {

                    // move Skill to second page
                    if (!isSentToSecondPageRight) {
                        val oldParentSkills = binding.containerSkill.parent as? ViewGroup
                        oldParentSkills?.removeView(binding.containerSkill)
                        containerSecondPageRightSide.addView(binding.containerSkill)
                        isSentToSecondPageRight = true
                    }
                }
            }
            education -> {
                if (currentPageLeft == 2) {

                    // move Achievement to second page
                    if (!isSentToSecondPageLeft) {
                        val oldParentAchievement = binding.containerAchievement.parent as? ViewGroup
                        oldParentAchievement?.removeView(binding.containerAchievement)
                        containerSecondPageLeftSide.addView(binding.containerAchievement)
                        isSentToSecondPageLeft = true
                    }
                }
            }
        }
    }

    // reusable func
    private fun setLayouts(
        text: String,
        textSize: Float,
        fontFamily: Int,
        margin: String,
        textColor: Int,
        bold: String?
    ): TextView {
        val textView = TextView(context)
        textView.text = text

        textView.textSize = textSize   // textSize
        textView.setTextColor(resources.getColorStateList(textColor))   // textColor
        textView.letterSpacing = 0.05f  // letterSpacing
        val marginTopBottom = resources.getDimensionPixelSize(R.dimen.margin_medium)

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
        when (margin) {
            "top" -> layoutParams.setMargins(0, marginTopBottom, 0, 0)
            "bottom" -> layoutParams.setMargins(0, 0, 0, marginTopBottom)
            "topbottom" -> layoutParams.setMargins(0, marginTopBottom, 0, marginTopBottom)
            "end" -> layoutParams.setMargins(0, 0, marginTopBottom, 0)
        }

        textView.layoutParams = layoutParams

        return textView
    }

}