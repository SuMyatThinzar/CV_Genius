package com.smtz.cvgenius.presentation.preview.templateViewPods

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.os.Handler
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.AttributeSet
import android.util.Base64
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
import com.smtz.cvgenius.domain.model.AchievementVO
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.EducationDetailVO
import com.smtz.cvgenius.domain.model.ProjectDetailVO
import com.smtz.cvgenius.domain.model.SkillsVO
import com.smtz.cvgenius.domain.model.WorkExperienceVO
import com.smtz.cvgenius.presentation.preview.utils.getCurrentPageHeight
import com.smtz.cvgenius.presentation.preview.utils.setUpContentVisibilityResumeSecondOne
import com.smtz.cvgenius.utils.achievement
import com.smtz.cvgenius.utils.education
import com.smtz.cvgenius.utils.projectDetail
import com.smtz.cvgenius.utils.signature
import com.smtz.cvgenius.utils.skill
import com.smtz.cvgenius.utils.workExperience

class ResumeSecondTwoViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : BaseViewPod(context, attrs) {

    private lateinit var binding: ViewPodZresumeSecondTwoBinding

    private var mWorkExperienceList: List<WorkExperienceVO> = listOf()
    private var mEducationList: List<EducationDetailVO> = listOf()
    private var mProjectDetailList: List<ProjectDetailVO> = listOf()
    private var mAchievementList: List<AchievementVO> = listOf()
    private var mSkillList: List<SkillsVO> = listOf()

    private var experienceContentViewList: MutableList<View> = mutableListOf()
    private var educationContentViewList: MutableList<View> = mutableListOf()
    private var projectContentViewList: MutableList<View> = mutableListOf()
    private var achievementContentViewList: MutableList<View> = mutableListOf()
    private var skillContentViewList: MutableList<View> = mutableListOf()

    private lateinit var containerWorkExp: LinearLayout
    private lateinit var containerFirstPageRightSide: LinearLayout
    private lateinit var containerFirstPageLeftSide: LinearLayout
    private lateinit var containerSecondPageRightSide: LinearLayout
    private lateinit var containerSecondPageLeftSide: LinearLayout

    private lateinit var currentPageLayoutRight: LinearLayout     // FirstPageRightSide  SecondPageRightSide
    private lateinit var currentPageLayoutLeft: LinearLayout      // FirstPageLeftSide   SecondPageLeftSide

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
    // 2 left
    private var rootViewAbsoluteHeightLeft = 0

    // Left 5 - 1 fixed     1.Image, 2.Contact, 3.Education, 4.Achievement, 5.Reference
    private var pageHeightLeft = 0F          // 2ခု
    private var pageLeft1to0 = 0F     // Edu -> Achi

    // Right 7 - 2 fixed     1.Name, 2.Position, 3.Objective, 4.Experience, 5.Project, 6.Skill, 7.Sign
    private var pageHeightRight = 0F           // 5ခု
    private var pageRight4to3 = 0F    // Obj -> Sign Skill Proj Exp
    private var pageRight3to2 = 0F    // Exp -> Sign Skill Proj
    private var pageRight2to1 = 0F    // Pro -> Sign Skill
//    private var pageRight1to0 = 0F    // Skill -> Sign   မလိုတော့ဘူး no heading for signature

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
            setUpContentVisibilityResumeSecondOne(tvAddress, mCvVO?.personalDetails?.address, containerAddress)
            setUpContentVisibilityResumeSecondOne(tvPhone, mCvVO?.personalDetails?.contact, containerPhone)
            setUpContentVisibilityResumeSecondOne(tvEmail, mCvVO?.personalDetails?.email, containerEmail)
            setUpContentVisibilityResumeSecondOne(tvDateOfBirth, mCvVO?.personalDetails?.dateOfBirth, containerDoB)
            setUpContentVisibilityResumeSecondOne(tvNationality, mCvVO?.personalDetails?.nationality, containerNationality)
            setUpContentVisibilityResumeSecondOne(tvObjective, mCvVO?.objective, null)
        }

        if (mCvVO?.signature != null) {
            val decodedBytes = Base64.decode(mCvVO?.signature, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
            binding.ivSignature.setImageBitmap(bitmap)

        } else binding.ivSignature.visibility = View.GONE

        if (mCvVO?.workExperiences?.isNotEmpty() == true) {       // null နဲ့စစ်လို့မရဘူး never null တဲ့ empty string ပဲဖြစ်နိုင်တယ်
            mWorkExperienceList = mCvVO?.workExperiences!!
            setUpWorkExp()        // content တွေကိုအရင်ထည့်လိုက်တယ် ဆန့်ဆန့်မဆန့်ဆန့်
        } else {
            containerWorkExp.visibility = View.GONE
        }

        if (mCvVO?.educationDetails?.isNotEmpty() == true) {
            mEducationList = mCvVO?.educationDetails!!
            setUpEducation()
        } else {
            binding.containerEducation.visibility = View.GONE
        }

        if (mCvVO?.projectDetails?.isNotEmpty() == true) {
            mProjectDetailList = mCvVO?.projectDetails!!
            setUpProject()
        } else {
            binding.containerProject.visibility = View.GONE
        }

        if (mCvVO?.skills?.isNotEmpty() == true) {
            mSkillList = mCvVO?.skills!!
            setUpSkill()
        } else {
            binding.containerSkill.visibility = View.GONE
        }

        if (mCvVO?.achievements?.isNotEmpty() == true) {
            mAchievementList = mCvVO?.achievements!!
            setUpAchievement()
        } else {
            binding.containerAchievement.visibility = View.GONE
        }

        checkContentsVisibility()     // content တွေကိုဆန့်မဆန့်စစ်ပြီး next page ပို့တယ်
    }

    private fun setUpWorkExp() {

        mWorkExperienceList.forEachIndexed { index, workExperienceVO ->

            val childLayout = LinearLayout(context)
            childLayout.orientation = VERTICAL

            // Set a unique ID for the childLayout
            childLayout.id = View.generateViewId()

            val workExperienceDuration = setLayouts("${workExperienceVO.startDate} - ${workExperienceVO.endDate}", 6.5f, R.font.lato_light, "top", R.dimen.margin_medium, R.color.black, null)
            childLayout.addView(workExperienceDuration)

            val companyNameTextView = setLayouts(workExperienceVO.company, 6.5f, R.font.lato_regular, "", 0, R.color.black, "bold")
            childLayout.addView(companyNameTextView)

            val workPositionTextView = setLayouts(workExperienceVO.position, 7.5f, R.font.lato_regular, "", 0, R.color.black, null)
            childLayout.addView(workPositionTextView)

            workExperienceVO.description?.let {
                val workDescriptionTextView = setLayouts(it, 6.5f, R.font.lato_light, "", 0, R.color.black, null)
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

            val educationDate = setLayouts("${educationVO.startDate} - ${educationVO.endDate}", 6.5f, R.font.lato_light, "top", R.dimen.margin_medium, R.color.white, null)
            childLayout.addView(educationDate)

            val degree = setLayouts(educationVO.diplomaName, 6.8f, R.font.lato_regular, "", 0, R.color.white, "bold")
            childLayout.addView(degree)

            val university = setLayouts("${educationVO.levelOfEducation} at ${educationVO.schoolName}", 6.8f, R.font.lato_regular, "", 0, R.color.white, null)
            childLayout.addView(university)

            educationVO.credentialURL?.let {
                val credentialURL = setLayouts(it, 7.5f, R.font.lato_regular, "", 0, R.color.white, null)
                Linkify.addLinks(credentialURL, Patterns.WEB_URL, "http://")
                credentialURL.autoLinkMask = Linkify.WEB_URLS
                credentialURL.isClickable = true
                credentialURL.setLinkTextColor(resources.getColor(R.color.colorLinkText))
                credentialURL.movementMethod = LinkMovementMethod.getInstance()
                childLayout.addView(credentialURL)
            }

            educationContentViewList.add(childLayout)

            binding.containerEducation.addView(childLayout)
        }
//        addContentToPagesEducation(contentList)
    }

    private fun setUpProject() {

        mProjectDetailList.forEachIndexed { index, projectDetailVO ->

            val childLayout = LinearLayout(context)
            childLayout.orientation = VERTICAL

            // Set a unique ID for the childLayout
            childLayout.id = View.generateViewId()

            val projectTitle = setLayouts(projectDetailVO.projectTitle, 7.5f, R.font.lato_regular, "top", R.dimen.margin_medium, R.color.black, null)
            childLayout.addView(projectTitle)

            val position = setLayouts("${projectDetailVO.position} | ${projectDetailVO.startDate} - ${projectDetailVO.endDate}", 7.5f, R.font.lato_regular, "topbottom", R.dimen.margin_small, R.color.black, null)
            childLayout.addView(position)

            projectDetailVO.projectDescription?.let {
                val projectDescription = setLayouts("- $it", 6.5f, R.font.lato_light, "bottom", R.dimen.margin_small, R.color.black, null)
                childLayout.addView(projectDescription)
            }

            projectDetailVO.credentialURL?.let {
                val credentialURL = setLayouts(it, 7.5f, R.font.lato_regular, "", 0, R.color.white, null)
                Linkify.addLinks(credentialURL, Patterns.WEB_URL, "http://")
                credentialURL.autoLinkMask = Linkify.WEB_URLS
                credentialURL.isClickable = true
                credentialURL.setLinkTextColor(resources.getColor(R.color.colorLinkText))
                credentialURL.movementMethod = LinkMovementMethod.getInstance()
                childLayout.addView(credentialURL)
            }

            projectContentViewList.add(childLayout)

            // အရင်ထည့်ပြီးမှစစ်တာ
            binding.containerProject.addView(childLayout)
        }
//        addContentToPagesWorkExp(contentList)
    }

    private fun setUpSkill() {

        mSkillList.forEachIndexed { index, skillsVO ->

            val childLayout = LinearLayout(context)

            val skillName = setLayouts("● ${skillsVO.skill}", 7.5f, R.font.lato_regular, "top", R.dimen.margin_small, R.color.black, null)
            childLayout.addView(skillName)

            skillContentViewList.add(childLayout)

            // အရင်ထည့်ပြီးမှစစ်တာ
            binding.containerSkill.addView(childLayout)
        }
    }

    private fun setUpAchievement() {

        mAchievementList.forEachIndexed { index, achievementVO ->

            val childLayout = LinearLayout(context)

            val achievementName = setLayouts("● ${achievementVO.achievement}", 7.5f, R.font.lato_regular, "top", R.dimen.margin_small, R.color.white, null)
            childLayout.addView(achievementName)

            achievementContentViewList.add(childLayout)

            // အရင်ထည့်ပြီးမှစစ်တာ
            binding.containerAchievement.addView(childLayout)
        }
    }

    // reusable func left and right side
    private fun checkContentsVisibility() {

        rootViewAbsoluteHeightRight = currentPageLayoutLeft.height - (18 * density).toInt() - (18 * density).toInt()
        rootViewAbsoluteHeightLeft = currentPageLayoutRight.height - paddingInPx - paddingInPx

        pageHeightRight = getCurrentPageHeight(containerToCheckHeight = containerFirstPageRightSide, 3) // need to reduce Project and Skill below Exp
        pageRight4to3 = getCurrentPageHeight(containerFirstPageRightSide, 3) - getCurrentPageHeight(containerFirstPageRightSide, 4)  //reduce 3 height က reduce 4 ထက် height ပိုများတယ်
        pageRight3to2 = getCurrentPageHeight(containerFirstPageRightSide, 2) - getCurrentPageHeight(containerFirstPageRightSide, 3)
        pageRight2to1 = getCurrentPageHeight(containerFirstPageRightSide, 1) - getCurrentPageHeight(containerFirstPageRightSide, 2)

        pageHeightLeft = getCurrentPageHeight(containerToCheckHeight = containerFirstPageLeftSide, 1) // need to reduce Project and Skill below Exp
        pageLeft1to0 = getCurrentPageHeight(containerFirstPageLeftSide, 0) - getCurrentPageHeight(containerFirstPageLeftSide, 1)

        Log.d("asdfasfsaf", "$rootViewAbsoluteHeightLeft ${(20 * density).toInt()} ${(18 * density).toInt()} $rootViewAbsoluteHeightRight $rootViewAbsoluteHeightLeft")

        val handler = Handler()
        handler.post {
            asynchronouslyCheckContent()
        }
    }

    // reusable func check visibility for all
    private fun asynchronouslyCheckContent() {
        // Objective  (Right)
        mCvVO?.objective?.let {
            val objective : View = binding.tvObjective
            val widthMeasure = MeasureSpec.makeMeasureSpec(objective.width, MeasureSpec.EXACTLY)
            val heightMeasure = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            objective.measure(widthMeasure, heightMeasure)
            objective.layout(0, 0, objective.measuredWidth, objective.measuredHeight)
            val objectiveHeight = objective.measuredHeight

            Log.d("adf4ff", "obj $pageHeightRight + $objectiveHeight = ${pageHeightRight + objectiveHeight}")
            pageHeightRight += objectiveHeight
        }


        // Work Experience (Right)
        if (mCvVO?.workExperiences?.isNotEmpty() == true) {
            Log.d("adf4ff", "added different 4-3 $pageHeightRight + $pageRight4to3 = ${pageHeightRight+pageRight4to3}")
            pageHeightRight += pageRight4to3
            for (content in experienceContentViewList) {

                // Manually measure and layout the (not need if the layout height is displayed fully) can setUp as val totalHeight = viewPodLayout.height
                val widthMeasureSpec = MeasureSpec.makeMeasureSpec(content.width, MeasureSpec.EXACTLY)
                val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                content.measure(widthMeasureSpec, heightMeasureSpec)
                content.layout(0, 0, content.measuredWidth, content.measuredHeight)

                val contentHeight = content.measuredHeight

                if (pageHeightRight + contentHeight <= rootViewAbsoluteHeightRight) {

                    if (currentPageRight == 1) {    // Content fits on the current page
                        Log.d("adf4ff", "Exp $pageHeightRight + $contentHeight = ${pageHeightRight + contentHeight} <= $rootViewAbsoluteHeightRight")
                        pageHeightRight += contentHeight
                        setUpTheChildViews(content as LinearLayout, type = workExperience, leftOrRight = "right")
                    }
                    if (currentPageRight == 2) {     // Add content to the new page
                        setUpTheChildViews(content as LinearLayout, type = workExperience, leftOrRight = "right")
                    }
                } else {  // change all Layouts here
                    if (currentPageRight == 1) { //ပထမဆုံးအကြိမ် first pageမှာမဆန့်တာဆိုရင် currentPage, RootView, pageNum အကုန်အရင်ပြောင်း

                        currentPageRight = 2
                        currentPageLayoutRight = binding.secondRootViewSecondTwo  // 1
                        rootViewAbsoluteHeightRight = currentPageLayoutRight.height - paddingInPx - paddingInPx  // 2
                    }
                    setUpTheChildViews(content as LinearLayout, type = workExperience, leftOrRight = "right")
                }
            }
            sendOtherLayoutsToAnotherPage(type = workExperience)
        }


        // Project (Right)
        if (mCvVO?.projectDetails?.isNotEmpty() == true) {
            Log.d("adf4ff", "added different 3-2 $pageHeightRight + $pageRight3to2 = ${pageHeightRight+pageRight3to2}")
            pageHeightRight += pageRight3to2
            projectContentViewList.forEachIndexed { index, content ->

                // Manually measure and layout the (not need if the layout height is displayed fully) can setUp as val totalHeight = viewPodLayout.height
                val widthMeasureSpec = MeasureSpec.makeMeasureSpec(content.width, MeasureSpec.EXACTLY)
                val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                content.measure(widthMeasureSpec, heightMeasureSpec)
                content.layout(0, 0, content.measuredWidth, content.measuredHeight)

                val contentHeight = content.measuredHeight
                val contentFitsInFirstPage = pageHeightRight + contentHeight <= rootViewAbsoluteHeightRight

                if (contentFitsInFirstPage) {

                    if (currentPageRight == 1) {    // Content fits on the current page
                        Log.d("adf4ff", "Pro $index $pageHeightRight + $contentHeight = ${pageHeightRight + contentHeight} <= $rootViewAbsoluteHeightRight")
                        pageHeightRight += contentHeight
                        setUpTheChildViews(content as LinearLayout, type = projectDetail, leftOrRight = "right")
                    }
                    if (currentPageRight == 2) {     // Add content to the new page
                        setUpTheChildViews(content as LinearLayout, type = projectDetail, leftOrRight = "right")
                    }

                } else {  // change all Layouts here
                    if (currentPageRight == 1) { //ပထမဆုံးအကြိမ် first pageမှာမဆန့်တာဆိုရင် currentPage, RootView, pageNum အကုန်အရင်ပြောင်း

                        currentPageRight = 2
                        currentPageLayoutRight = binding.secondRootViewSecondTwo  // 1
                        rootViewAbsoluteHeightRight = currentPageLayoutRight.height - paddingInPx - paddingInPx  // 2

                        if (index == 0) {  // ပထမဆုံးတစ်ခုမှာတင် မဆန့်ရင် header ကိုပါ nextpage ပို့ဖို့ဆိုရင် workExperience နဲ့ဆို project ရော skill ပါအကုန်ပို့ပြီးသားဖြစ်သွားရော
                            sendOtherLayoutsToAnotherPage(type = workExperience)
                        }
                    }
                    setUpTheChildViews(content as LinearLayout, type = projectDetail, leftOrRight = "right")
                }
            }
            sendOtherLayoutsToAnotherPage(type = projectDetail)
        }


        // skill (Right)
        if (mCvVO?.skills?.isNotEmpty() == true) {
            Log.d("adf4ff", "added different 2-1 $pageHeightRight + $pageRight2to1 = ${pageHeightRight+pageRight2to1}")
            pageHeightRight += pageRight2to1
            skillContentViewList.forEachIndexed { index, content ->

                // Manually measure and layout the (not need if the layout height is displayed fully) can setUp as val totalHeight = viewPodLayout.height
                val widthMeasureSpec = MeasureSpec.makeMeasureSpec(content.width, MeasureSpec.EXACTLY)
                val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                content.measure(widthMeasureSpec, heightMeasureSpec)
                content.layout(0, 0, content.measuredWidth, content.measuredHeight)

                val contentHeight = content.measuredHeight
                val contentFitsInFirstPage = pageHeightRight + contentHeight <= rootViewAbsoluteHeightRight
                Log.d("adf4ff", "Ski $index $pageHeightRight + $contentHeight = ${pageHeightRight + contentHeight} <= $rootViewAbsoluteHeightRight")

                if (contentFitsInFirstPage) {

                    if (currentPageRight == 1) {    // Content fits on the current page
                        Log.d("adf4ff", "Ski $index $pageHeightRight + $contentHeight = ${pageHeightRight + contentHeight} <= $rootViewAbsoluteHeightRight")
                        pageHeightRight += contentHeight
                        setUpTheChildViews(content as LinearLayout, type = skill, leftOrRight = "right")
                    }
                    if (currentPageRight == 2) {     // Add content to the new page
                        setUpTheChildViews(content as LinearLayout, type = skill, leftOrRight = "right")
                    }

                } else {  // change all Layouts here
                    if (currentPageRight == 1) { //ပထမဆုံးအကြိမ် first pageမှာမဆန့်တာဆိုရင် currentPage, RootView, pageNum အကုန်အရင်ပြောင်း

                        currentPageRight = 2
                        currentPageLayoutRight = binding.secondRootViewSecondTwo  // 1
                        rootViewAbsoluteHeightRight = currentPageLayoutRight.height - paddingInPx - paddingInPx  // 2

                        if (index == 0) {
                            sendOtherLayoutsToAnotherPage(type = projectDetail)
                        }
                    }
                    setUpTheChildViews(content as LinearLayout, type = skill, leftOrRight = "right")
                }
            }
            sendOtherLayoutsToAnotherPage(type = skill)
        }


        // Signature  (Right)
        mCvVO?.signature?.let {
            val signatureView : View = binding.ivSignature
            val signatureHeight = signatureView.measuredHeight  // static height
            val contentFitsInFirstPage = pageHeightRight + signatureHeight <= rootViewAbsoluteHeightRight

            if (contentFitsInFirstPage) {

                if (currentPageRight == 1) {    // Content fits on the current page
                    Log.d("adf4ff", "Sign $pageHeightRight + $signatureHeight = ${pageHeightRight + signatureHeight} <= $rootViewAbsoluteHeightRight")

                    pageHeightRight += signatureHeight
                    setUpTheChildViews(signatureView, type = signature, leftOrRight = "right")
                }
                if (currentPageRight == 2){     // Add content to the new page
                    setUpTheChildViews(signatureView, type = signature, leftOrRight = "right")
                }
            }
            else {  // change all Layouts here
                currentPageRight = 2
                setUpTheChildViews(signatureView, type = signature, leftOrRight = "right")
            }
        }
//        sendOtherLayoutsToAnotherPage(type = signature)   no need for the last element


        // Education
        if (mCvVO?.educationDetails?.isNotEmpty() == true) {
            educationContentViewList.forEachIndexed { index, content ->

                // Manually measure and layout the (not need if the layout height is displayed fully) can setUp as val totalHeight = viewPodLayout.height
                val widthMeasureSpec = MeasureSpec.makeMeasureSpec(content.width, MeasureSpec.EXACTLY)
                val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                content.measure(widthMeasureSpec, heightMeasureSpec)
                content.layout(0, 0, content.measuredWidth, content.measuredHeight)

                val contentHeight = content.measuredHeight
                val contentFitsInFirstPage =
                    pageHeightLeft + contentHeight <= rootViewAbsoluteHeightLeft

                if (contentFitsInFirstPage) { // content ကဆန့်တယ်ဆိုရင်

                    if (currentPageLeft == 1) {    // Content fits on the current page
                        Log.d("a3dsrwe", "Edu $index $pageHeightLeft + $contentHeight = ${pageHeightLeft + contentHeight} <= $rootViewAbsoluteHeightLeft")
                        pageHeightLeft += contentHeight
                        setUpTheChildViews(content as LinearLayout, type = education, leftOrRight = "left")
                    }
                    if (currentPageLeft == 2) {     // Add content to the new page
                        setUpTheChildViews(content as LinearLayout, type = education, leftOrRight = "left")
                    }
                } else {  // content ကမဆန့်ဘူး change all Layouts here
                    if (currentPageLeft == 1) {  //  //ပထမဆုံးအကြိမ် first page မှာမဆန့်တာဆိုရင် currentPage, RootView, pageNum အကုန်အရင်ပြောင်း
                        currentPageLeft = 2
                        currentPageLayoutLeft = containerSecondPageLeftSide  // 1
                        rootViewAbsoluteHeightLeft =
                            currentPageLayoutLeft.height - paddingInPx - paddingInPx  // 2
//                    currentPageHeightLeft = getCurrentPageHeight(containerToCheckHeight = containerSecondPageLeftSide, 2)  // 3

                    }
                    // page က already 2 ဖြစ်ရင်
                    setUpTheChildViews(content as LinearLayout, type = education, leftOrRight = "left")
                }
            }
            sendOtherLayoutsToAnotherPage(type = education)
        }


        // Achievement
        if (mCvVO?.achievements?.isNotEmpty() == true) {
            Log.d("a3dsrwe", "added different 1-0 $pageHeightLeft + $pageLeft1to0 = ${pageHeightLeft + pageLeft1to0}")
            pageHeightLeft += pageLeft1to0 + (8 * density).toInt()
            achievementContentViewList.forEachIndexed { index, content ->

                // Manually measure and layout the (not need if the layout height is displayed fully) can setUp as val totalHeight = viewPodLayout.height
                val widthMeasureSpec = MeasureSpec.makeMeasureSpec(content.width, MeasureSpec.EXACTLY)
                val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                content.measure(widthMeasureSpec, heightMeasureSpec)
                content.layout(0, 0, content.measuredWidth, content.measuredHeight)

                val contentHeight = content.measuredHeight
                val contentFitsInFirstPage =
                    pageHeightLeft + contentHeight <= rootViewAbsoluteHeightLeft

                if (contentFitsInFirstPage) {

                    if (currentPageLeft == 1) {    // Content fits on the current page
                        Log.d("a3dsrwe", "Skil $index $pageHeightLeft + $contentHeight = ${pageHeightLeft + contentHeight} <= $rootViewAbsoluteHeightLeft")
                        pageHeightLeft += contentHeight
                        setUpTheChildViews(content as LinearLayout, type = achievement, leftOrRight = "left")
                    }
                    if (currentPageLeft == 2) {     // Add content to the new page
                        setUpTheChildViews(content as LinearLayout, type = achievement, leftOrRight = "left")
                    }

                } else {  // change all Layouts here
                    if (currentPageLeft == 1) { //ပထမဆုံးအကြိမ် first pageမှာမဆန့်တာဆိုရင် currentPage, RootView, pageNum အကုန်အရင်ပြောင်း

                        currentPageLeft = 2
                        currentPageLayoutLeft = binding.secondRootViewSecondTwo  // 1
                        rootViewAbsoluteHeightLeft = currentPageLayoutLeft.height - paddingInPx - paddingInPx  // 2

                        if (index == 0) {
                            sendOtherLayoutsToAnotherPage(type = achievement)
                        }
                    }
                    setUpTheChildViews(content as LinearLayout, type = achievement, leftOrRight = "left")
                }
            }
//        sendOtherLayoutsToAnotherPage(type = achievement)  no need for the last element
        }

    }

    // reusable func asynchronouslyCheckContent ကနေတစ်ခေါက်ပြီးတစ်ခေါက် ခေါ်ပြီး pages တွေထဲကို add and remove
    private fun setUpTheChildViews(childLayout: View, type: String, leftOrRight: String) {

        // Right
        if (leftOrRight == "right") {
            // Right 1
            if (currentPageRight == 1) {
                val oldParent = childLayout.parent as? ViewGroup
                oldParent?.removeView(childLayout) // Remove the childLayout from the current parent

                if (type == workExperience) containerWorkExp.addView(childLayout)
                if (type == projectDetail) binding.containerProject.addView(childLayout)
                if (type == skill) binding.containerSkill.addView(childLayout)
                if (type == signature) binding.containerFirstPageRightSide.addView(childLayout)
            }

            // Right 2
            if (currentPageRight == 2) {
                binding.secondRootViewSecondTwo.visibility = View.VISIBLE

                val oldParent = childLayout.parent as? ViewGroup
                oldParent?.removeView(childLayout) // Remove the childLayout from the current parent

                if (type == workExperience) { containerSecondPageRightSide.addView(childLayout) }  // isSentToSecondPageRight ကအောက်ကကောင်တွေအတွက်ပဲလိုတယ်

                if (isSentToSecondPageRight) {  // အပေါ်ဆုံး view မှာထဲက မဆန့်လို့ nextpage ပို့ပြီးသားဆိုရင် container ထဲ တန်းထည့်လိုက်ရုံပဲ
                    if (type == projectDetail) { binding.containerProject.addView(childLayout) }
                    if (type == skill) binding.containerSkill.addView(childLayout)
                    if (type == signature) { containerSecondPageRightSide.addView(childLayout) }

                } else {  // အခုမှပို့ရမှာဆိုရင် root ထဲတန်းထည့်လိုက်ရုံပဲ / first element ဆိုရင် header ကိုပါ ပို့ဖို့အတွက်က checkContent ထဲမှာစစ်ပြီးသား
                    if (type == projectDetail) { containerSecondPageRightSide.addView(childLayout) }
                    if (type == skill) { containerSecondPageRightSide.addView(childLayout) }
                    if (type == signature) { containerSecondPageRightSide.addView(childLayout) }
                }
            }
        }

        // Left
        if (leftOrRight == "left") {
            // Left 1
            if (currentPageLeft == 1) {
                val oldParent = childLayout.parent as? ViewGroup
                oldParent?.removeView(childLayout) // Remove the childLayout from the current parent

                if (type == education) binding.containerEducation.addView(childLayout)
                if (type == achievement) binding.containerAchievement.addView(childLayout)
            }
            // Left 2
            if (currentPageLeft == 2) {
                binding.secondRootViewSecondTwo.visibility = View.VISIBLE

                val oldParent = childLayout.parent as? ViewGroup
                oldParent?.removeView(childLayout) // Remove the childLayout from the current parent

                containerSecondPageLeftSide.addView(childLayout)
            }
        }
    }

    // reusable func if currentPage == 2, page 2 ကို below layouts တွေပို့တဲ့ func
    private fun sendOtherLayoutsToAnotherPage(type: String) {
        when (type) {
            workExperience -> {
                if (currentPageRight == 2) {

                    // move Project and Skill and Signature to second page
                    if (!isSentToSecondPageRight) {
                        val oldParentProjectDetail = binding.containerProject.parent as? ViewGroup
                        oldParentProjectDetail?.removeView(binding.containerProject)
                        containerSecondPageRightSide.addView(binding.containerProject)

                        val oldParentSkills = binding.containerSkill.parent as? ViewGroup
                        oldParentSkills?.removeView(binding.containerSkill)
                        containerSecondPageRightSide.addView(binding.containerSkill)

                        val oldParentSignature = binding.ivSignature.parent as? ViewGroup
                        oldParentSignature?.removeView(binding.ivSignature)
                        containerSecondPageRightSide.addView(binding.ivSignature)
                        isSentToSecondPageRight = true
                    }
                }
            }
            projectDetail -> {
                if (currentPageRight == 2) {

                    // move Skill and Signature to second page
                    if (!isSentToSecondPageRight) {
                        val oldParentSkills = binding.containerSkill.parent as? ViewGroup
                        oldParentSkills?.removeView(binding.containerSkill)
                        containerSecondPageRightSide.addView(binding.containerSkill)

                        val oldParentSignature = binding.ivSignature.parent as? ViewGroup
                        oldParentSignature?.removeView(binding.ivSignature)
                        containerSecondPageRightSide.addView(binding.ivSignature)
                        isSentToSecondPageRight = true
                    }
                }
            }
            skill -> {
                if (currentPageRight == 2) {

                    // move Signature to second page
                    if (!isSentToSecondPageRight) {
                        val oldParentSignature = binding.ivSignature.parent as? ViewGroup
                        oldParentSignature?.removeView(binding.ivSignature)
                        containerSecondPageRightSide.addView(binding.ivSignature)
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
                        isSentToSecondPageRight = true
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