package com.smtz.cvgenius.presentation.preview.templateViewPods

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.graphics.Typeface
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.smtz.cvgenius.R
import com.smtz.cvgenius.common.CvSingleton
import com.smtz.cvgenius.databinding.ViewPodZresumeSecondTwoBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.WorkExperienceVO
import com.smtz.cvgenius.presentation.preview.utils.setUpContentVisibilityResumeSecondOne
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.CountDownLatch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ResumeSecondTwoViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : BaseViewPod(context, attrs) {

    private lateinit var binding: ViewPodZresumeSecondTwoBinding

    private var mWorkExperienceList: List<WorkExperienceVO> = listOf()

    private lateinit var containerWorkExp : LinearLayout
    private lateinit var containerFirstPageRightSide: LinearLayout
    private lateinit var containerFirstPageLeftSide: LinearLayout
    private lateinit var  containerSecondPageRightSide: LinearLayout
    private lateinit var  containerSecondPageLeftSide: LinearLayout

    private var mCvVO: CvVO? = null
    private var isInitialSetupDone = false
    private var isSentToSecondPage = false

    private val contentList = mutableListOf<View>()

    override fun onFinishInflate() {
        binding = ViewPodZresumeSecondTwoBinding.bind(this)
        mCvVO = CvSingleton.instance.cvVO!!
        containerWorkExp = binding.containerExperience
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

            val workExperienceDuration = setLayouts(
                "${workExperienceVO.startDate} - ${workExperienceVO.endDate}",
                6.5f, R.font.lato_light, "top", R.color.black, null
            )
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
        }
        addContentToPages()
    }

    private fun addContentToPages() {
        val currentPageLayout = binding.firstRootView
        val density = resources.displayMetrics.density
        val paddingInPx = (20 * density).toInt()

        val absoluteHeight = currentPageLayout.height - paddingInPx - paddingInPx

        var currentPageHeight = getCurrentPageHeight()

        var temp = 0

        for (content in contentList) {

            binding.containerExperience.addView(content)
            val handler = Handler()
            handler.post {
//                val parentRect = Rect()
//                val childRect = Rect()

                val height = content.measuredHeight

//                containerWorkExp.getGlobalVisibleRect(parentRect)

//                val parentLocation = IntArray(2)
//                containerWorkExp.getLocationOnScreen(parentLocation)
//
//                content.getGlobalVisibleRect(childRect)
//
//                val childLocation = IntArray(2)
//                content.getLocationOnScreen(childLocation)
//
//                childRect.offset(-childLocation[0], -childLocation[1])
//                parentRect.offset(-parentLocation[0], -parentLocation[1])
//
//                val isFullyVisible = Rect.intersects(parentRect, childRect) && childRect.height() >= content.height

                if (currentPageHeight + height <= absoluteHeight) {
                    // Content fits on the current page
                    Log.d("asfddfsasdf", "page1 $temp $currentPageHeight + $height = ${currentPageHeight+height} <= ${absoluteHeight}")
                    temp ++
                    currentPageHeight += height
                } else {
                    // Add content to the new page
                    Log.d("asfddfsasdf", "page2 $temp $currentPageHeight + $height = ${currentPageHeight+height} <= ${absoluteHeight}")
                    temp ++
                    setUpTheChildViews(2, content as LinearLayout)
                }
            }
        }
    }

    private fun getCurrentPageHeight(): Float {
        var height = 0f

        for (i in 0 until binding.containerFirstPageRightSide.childCount -2) {
            val view = binding.containerFirstPageRightSide.getChildAt(i)
            height += view.height.toFloat()
        }

        return height
    }

    private fun setUpTheChildViews(pageNumber: Int, childLayout: LinearLayout) {

        if (pageNumber == 2) {
            binding.secondRootView.visibility = View.VISIBLE

            val oldParent = childLayout.parent as? ViewGroup
            oldParent?.removeView(childLayout) // Remove the childLayout from the current parent

            containerSecondPageRightSide.addView(childLayout)

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

    private fun setLayouts(text: String, textSize: Float, fontFamily: Int, margin: String, textColor: Int, bold: String?): TextView {
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