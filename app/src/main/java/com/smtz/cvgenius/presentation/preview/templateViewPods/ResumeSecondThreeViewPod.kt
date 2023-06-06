package com.smtz.cvgenius.presentation.preview.templateViewPods

import android.content.Context
import android.graphics.*
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
import com.smtz.cvgenius.databinding.ViewPodZresumeSecondThreeBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.WorkExperienceVO
import com.smtz.cvgenius.presentation.preview.utils.setUpContentVisibilityResumeSecondOne
import kotlin.math.ceil

class ResumeSecondThreeViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : BaseViewPod(context, attrs) {

    private lateinit var binding: ViewPodZresumeSecondThreeBinding

    private var mWorkExperienceList: List<WorkExperienceVO> = listOf()

    private var mCvVO: CvVO? = null
    private lateinit var containerWorkExp: LinearLayout

    private var pageIndex: Int = 0
    private var pageHeight = 1374.0
    private var parentFullHeight = 0

    override fun onFinishInflate() {
        binding = ViewPodZresumeSecondThreeBinding.bind(this)
        mCvVO = CvSingleton.instance.cvVO!!

        containerWorkExp = binding.containerExperience
        setUpData()
        super.onFinishInflate()
    }

//    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)
//        if (hasFocus && !isInitialSetupDone) {
//            separatePages()
//            isInitialSetupDone = true
//        }
//    }

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

//            contentList.add(childLayout)
            containerWorkExp.addView(childLayout)
        }

    }

    private fun separatePages() {
        val rootLayout = binding.root

        // Manually measure and layout the (not need if the layout height is displayed fully) can setUp as val totalHeight = viewPodLayout.height
        val widthMeasureSpec = MeasureSpec.makeMeasureSpec(rootLayout.width, MeasureSpec.EXACTLY)
        val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        rootLayout.measure(widthMeasureSpec, heightMeasureSpec)
        rootLayout.layout(0, 0, rootLayout.measuredWidth, rootLayout.measuredHeight)

        parentFullHeight = rootLayout.measuredHeight

        Log.d("afasdf", "$parentFullHeight")

        if (parentFullHeight >= pageHeight) {
            Log.d("afasdf", "true $parentFullHeight >= $pageHeight")
            pageIndex = (parentFullHeight / pageHeight).toInt()

            val pageHeight = 1364
            val blankSpaceHeight = 10

            val generatedBitmaps = generatePageBitmaps(context, binding.root, pageHeight, blankSpaceHeight)
        } else {
            Log.d("afasdf", "false ${rootLayout.height} >= $pageHeight")
        }

    }

    private fun generatePageBitmaps(context: Context, viewPodLayout: ViewGroup, pageHeight: Int, blankSpaceHeight: Int): List<Bitmap> {
        val density = context.resources.displayMetrics.density
        val totalHeight = viewPodLayout.height

        val numPages = ceil(totalHeight.toDouble() / pageHeight).toInt()

        val bitmaps = mutableListOf<Bitmap>()

        for (pageNo in 0 until numPages) {
            val startY = pageNo * pageHeight.toFloat()
            val endY = (startY + pageHeight).toInt().coerceAtMost(totalHeight)

            val bitmap = Bitmap.createBitmap(
                (viewPodLayout.width * density).toInt(),
                1364 + blankSpaceHeight,
                Bitmap.Config.ARGB_8888
            )

            val canvas = Canvas(bitmap)
            canvas.scale(density, density)

            canvas.translate(0f, -startY)
            canvas.clipRect(0, startY.toInt(), viewPodLayout.width, endY)

            viewPodLayout.draw(canvas)

            // Add blank space below each page
            val blankSpaceBitmap = Bitmap.createBitmap(
                (viewPodLayout.width * density).toInt(),
                (20 * density).toInt(),
                Bitmap.Config.ARGB_8888
            )

            val blankSpaceCanvas = Canvas(blankSpaceBitmap)
            blankSpaceCanvas.scale(density, density)

            val blankSpaceStartY = endY.toFloat()
            blankSpaceCanvas.translate(0f, -blankSpaceStartY)
            blankSpaceCanvas.drawColor(Color.WHITE)

            val combinedBitmap = Bitmap.createBitmap(
                bitmap.width,
                bitmap.height + blankSpaceBitmap.height,
                bitmap.config
            )

            val combinedCanvas = Canvas(combinedBitmap)
            combinedCanvas.drawBitmap(bitmap, 0f, 0f, null)
            combinedCanvas.drawBitmap(blankSpaceBitmap, 0f, bitmap.height.toFloat(), null)

            bitmaps.add(combinedBitmap)
        }

        return bitmaps
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
