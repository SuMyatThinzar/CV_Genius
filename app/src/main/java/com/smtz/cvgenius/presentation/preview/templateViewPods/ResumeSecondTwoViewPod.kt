package com.smtz.cvgenius.presentation.preview.templateViewPods

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.smtz.cvgenius.R
import com.smtz.cvgenius.common.CvSingleton
import com.smtz.cvgenius.databinding.ViewPodZresumeSecondTwoBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.WorkExperienceVO
import com.smtz.cvgenius.presentation.preview.utils.getCurrentPageHeight
import com.smtz.cvgenius.presentation.preview.utils.setUpContentVisibilityResumeSecondOne

class ResumeSecondTwoViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : BaseViewPod(context, attrs) {

    private lateinit var binding: ViewPodZresumeSecondTwoBinding

    private var mWorkExperienceList: List<WorkExperienceVO> = listOf()

    private lateinit var containerWorkExp: LinearLayout
    private lateinit var containerFirstPageRightSide: LinearLayout
    private lateinit var containerFirstPageLeftSide: LinearLayout
    private lateinit var containerSecondPageRightSide: LinearLayout
    private lateinit var containerSecondPageLeftSide: LinearLayout

    private lateinit var currentPageLayout: LinearLayout

    private var mCvVO: CvVO? = null
    private var isInitialSetupDone = false
    private var isSentToSecondPage = false
    private var currentPage = 1

    private val contentList = mutableListOf<View>()

    override fun onFinishInflate() {
        binding = ViewPodZresumeSecondTwoBinding.bind(this)
        mCvVO = CvSingleton.instance.cvVO!!
        containerWorkExp = binding.containerExperience
        containerFirstPageRightSide = binding.containerFirstPageRightSide
        containerFirstPageLeftSide = binding.containerFirstPageLeftSide
        containerSecondPageRightSide = binding.containerSecondPageRightSide
        containerSecondPageLeftSide = binding.containerSecondPageLeftSide

        // 1
        currentPageLayout = binding.firstRootView
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
        mCvVO?.profileImage?.let {
            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            binding.ivSelectedImage.setImageBitmap(bitmap)
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
            containerWorkExp.visibility = View.VISIBLE
            mWorkExperienceList = mCvVO?.workExperiences!!
            setUpWorkExp()
        } else {
            containerWorkExp.visibility = View.GONE
        }
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

            contentList.add(childLayout)
            binding.containerExperience.addView(childLayout)
        }
        currentPage = 1
        addContentToPages()
    }

    private fun addContentToPages() {
        val density = resources.displayMetrics.density
        val paddingInPx = (20 * density).toInt()

        // 2
        var rootViewAbsoluteHeight = currentPageLayout.height - paddingInPx - paddingInPx
        // 3
        var currentPageHeight = getCurrentPageHeight(containerToCheckHeight = containerFirstPageRightSide, 2)

        var temp = 0

//        for (content in contentList) {
//            binding.containerExperience.addView(content)
//        }

        val handler = Handler()
        handler.post {

            for (content in contentList) {

                // Manually measure and layout the (not need if the layout height is displayed fully) can setUp as val totalHeight = viewPodLayout.height
                val widthMeasureSpec = MeasureSpec.makeMeasureSpec(content.width, MeasureSpec.EXACTLY)
                val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                content.measure(widthMeasureSpec, heightMeasureSpec)
                content.layout(0, 0, content.measuredWidth, content.measuredHeight)

                val contentHeight = content.measuredHeight

                if (currentPageHeight + contentHeight <= rootViewAbsoluteHeight) {

                    if (currentPage == 1) {    // Content fits on the current page
                        Log.d("asfddfsasdf", "page1 $temp $currentPageHeight + $contentHeight = ${currentPageHeight + contentHeight} <= ${rootViewAbsoluteHeight} $currentPageLayout")
                        temp++
                        currentPageHeight += contentHeight
                        setUpTheChildViews(content as LinearLayout)
                    }
                    if (currentPage == 2){     // Add content to the new page
                        Log.d("asfddfsasdf", "page2 $temp $currentPageHeight + $contentHeight = ${currentPageHeight + contentHeight} <= ${rootViewAbsoluteHeight} $currentPageLayout")
                        temp++
                        setUpTheChildViews(content as LinearLayout)
                    }
                }

                else {  // change all Layouts here
                    temp++

                    if (currentPage == 1) {

                        currentPage = 2
                        currentPageLayout = binding.secondRootView  // 1
                        rootViewAbsoluteHeight = currentPageLayout.height - paddingInPx - paddingInPx  // 2
                        currentPageHeight = getCurrentPageHeight(containerToCheckHeight = containerSecondPageRightSide, 2)  // 3

                    }
                    setUpTheChildViews(content as LinearLayout)
                    Log.d("asfddfsasdf", "page2 $temp $currentPageHeight + $contentHeight = ${currentPageHeight + contentHeight} <= ${rootViewAbsoluteHeight} $currentPageLayout")

                }
            }
            sendOtherLayoutsToAnotherPage()
        }
    }

    private fun setUpTheChildViews(childLayout: LinearLayout) {

        if (currentPage == 1) {
            val oldParent = childLayout.parent as? ViewGroup
            oldParent?.removeView(childLayout) // Remove the childLayout from the current parent

            containerWorkExp.addView(childLayout)
        }
        if (currentPage == 2) {
            binding.secondRootView.visibility = View.VISIBLE

            val oldParent = childLayout.parent as? ViewGroup
            oldParent?.removeView(childLayout) // Remove the childLayout from the current parent

            containerSecondPageRightSide.addView(childLayout)
        }
    }

    private fun sendOtherLayoutsToAnotherPage() {
        if (currentPage == 2) {

            // move Project to second page
            if (!isSentToSecondPage) {
                val oldParentProjectDetail = binding.containerProject.parent as? ViewGroup
                oldParentProjectDetail?.removeView(binding.containerProject)
                containerSecondPageRightSide.addView(binding.containerProject)

                val oldParentSkills = binding.containerSkill.parent as? ViewGroup
                oldParentSkills?.removeView(binding.containerSkill)
                containerSecondPageRightSide.addView(binding.containerSkill)
                isSentToSecondPage = true
            }
        }
    }

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