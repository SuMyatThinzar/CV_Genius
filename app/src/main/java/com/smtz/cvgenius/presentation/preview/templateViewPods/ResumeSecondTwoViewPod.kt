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
import com.smtz.cvgenius.domain.model.ReferenceVO
import com.smtz.cvgenius.domain.model.SkillsVO
import com.smtz.cvgenius.domain.model.WorkExperienceVO
import com.smtz.cvgenius.presentation.preview.utils.getCurrentPageHeight
import com.smtz.cvgenius.presentation.preview.utils.setUpContentVisibilityResumeSecondOne
import com.smtz.cvgenius.utils.achievement
import com.smtz.cvgenius.utils.contact
import com.smtz.cvgenius.utils.education
import com.smtz.cvgenius.utils.name
import com.smtz.cvgenius.utils.objective
import com.smtz.cvgenius.utils.projectDetail
import com.smtz.cvgenius.utils.reference
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
    private var mReferenceList: List<ReferenceVO> = listOf()
    private var mSkillList: List<SkillsVO> = listOf()

    private var nameContentView: View = View(context)
    private var objectiveContentView: View = View(context)
    private var contactContentViewList: MutableList<View> = mutableListOf()
    private var experienceContentViewList: MutableList<View> = mutableListOf()
    private var educationContentViewList: MutableList<View> = mutableListOf()
    private var projectContentViewList: MutableList<View> = mutableListOf()
    private var achievementContentViewList: MutableList<View> = mutableListOf()
    private var referenceContentViewList: MutableList<View> = mutableListOf()
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

    private var rootViewAbsoluteHeightRight = 0
    private var rootViewAbsoluteHeightLeft = 0

    // Left 5 - 1 fixed     1.Image, 2.Contact, 3.Education, 4.Achievement, 5.Reference
    private var pageHeightLeft = 0F         // 3ခု
    private var pageLeftContact3To4 = 0F     // Image -> Contact  / Contact heading height
    private var pageLeftEdu2To3 = 0F         // Contact -> Edu    / Edu heading height
    private var pageLeftAchi1To2 = 0F        // Edu -> Achi       / Achi heading height
    private var pageLeftRef0To1 = 0F         // Achi -> Ref       / Ref heading height

    // Right 7 - 1 fixed     1.Name, 2.Position, 3.Objective, 4.Experience, 5.Project, 6.Skill, 7.Sign
    private var pageHeightRight = 0F        // 4ခု    main height which will be added after measurement of each content
    private var pageRightPosition5To6 = 0F     // position height
    private var pageRightExp3To4 = 0F           // Position -> Exp  // Exp heading height
    private var pageRightProj2To3 = 0F          // Exp -> Proj
    private var pageRightSkill1To2 = 0F         // Pro -> Skill
//    private var pageRightSkillToSign = 0F     // Skill -> Sign  မလိုတော့ဘူး no heading for signature

    override fun onFinishInflate() {
        binding = ViewPodZresumeSecondTwoBinding.bind(this)
        mCvVO = CvSingleton.instance.cvVO!!
        containerWorkExp = binding.containerExperience
        containerFirstPageRightSide = binding.containerFirstPageRightSide
        containerFirstPageLeftSide = binding.containerFirstPageLeftSide
        containerSecondPageRightSide = binding.containerSecondPageRightSide
        containerSecondPageLeftSide = binding.containerSecondPageLeftSide

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

        binding.tvPosition.text = mCvVO?.personalDetails?.professionalTitle

        setUpContact()

        // Name
        val name = "${mCvVO?.personalDetails?.firstName} ${mCvVO?.personalDetails?.lastName}"
        val nameChildLayout = LinearLayout(context)
        val nameView = setLayouts(name, 19.2f, R.font.lato_regular, "bottom", R.dimen.margin_ultra_small, R.color.black, "bold")
        nameChildLayout.addView(nameView)
        nameContentView = nameChildLayout
        // အရင်ထည့်ပြီးမှစစ်
        binding.containerName.addView(nameChildLayout)

        // 1. contentView အဖြစ်အရင် save
        if (mCvVO?.objective?.isNotEmpty() == true) {
            val childLayout = LinearLayout(context)
            val objective = setLayouts("${mCvVO?.objective}", 6.5f, R.font.lato_regular, "top", R.dimen.margin_card_medium, R.color.black, null)
            childLayout.addView(objective)
            objectiveContentView = childLayout
            // အရင်ထည့်ပြီးမှစစ်
            binding.containerObjective.addView(childLayout)
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

        if (mCvVO?.references?.isNotEmpty() == true) {
            mReferenceList = mCvVO?.references!!
            setUpReferences()
        } else {
            binding.containerReference.visibility = View.GONE
        }

        // 2. အကုန်လုံးထည့်ပြီးရင် visibility တစ်ခုချင်းထည့်ပြီး စစ်
        checkContentsVisibility()     // content တွေကိုဆန့်မဆန့်စစ်ပြီး next page ပို့တယ်
    }

    // 1. contentViewList အဖြစ်အရင် save ပြီး  empty container ထဲက ထည့်
    private fun setUpWorkExp() {
        mWorkExperienceList.forEachIndexed { index, workExperienceVO ->

            val childLayout = LinearLayout(context)
            childLayout.orientation = VERTICAL

            // Set a unique ID for the childLayout
            childLayout.id = View.generateViewId()

            var workExperienceDuration =
            if (workExperienceVO.endDate?.isNotEmpty() == true) {
                 setLayouts("${workExperienceVO.startDate}  -  ${workExperienceVO.endDate}", 6.7f, R.font.lato_light, "top", R.dimen.margin_medium, R.color.black, null)
            } else setLayouts("", 6.7f, R.font.lato_light, "top", R.dimen.margin_ultra_small, R.color.black, null)
            childLayout.addView(workExperienceDuration)

            val companyNameTextView = setLayouts(workExperienceVO.company, 6.5f, R.font.lato_regular, "", 0, R.color.black, "bold")
            childLayout.addView(companyNameTextView)

            val workPositionTextView = setLayouts(workExperienceVO.position, 7.5f, R.font.lato_regular, "", 0, R.color.black, null)
            childLayout.addView(workPositionTextView)

            if (workExperienceVO.description?.isNotEmpty() == true) {
                val workDescriptionTextView = setLayouts(workExperienceVO.description, 6.5f, R.font.lato_light, "", 0, R.color.black, null)
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

            if (educationVO.credentialURL?.isNotEmpty() == true) {
                val credentialURL = setLayouts(educationVO.credentialURL?:"", 7.5f, R.font.lato_regular, "", 0, R.color.white, null)
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

            val position = setLayouts("${projectDetailVO.position} | ${projectDetailVO.startDate} - ${projectDetailVO.endDate}", 7.5f, R.font.lato_regular, "topbottom", R.dimen.margin_ultra_small, R.color.black, null)
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

            val skillName = setLayouts("● ${skillsVO.skill}", 7.5f, R.font.lato_regular, "topbottom", R.dimen.margin_small, R.color.black, null)
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

    private fun setUpReferences() {

        mReferenceList.forEachIndexed { index, referenceVO ->

            val childLayout = LinearLayout(context)
            childLayout.orientation = VERTICAL

            val referenceName = setLayouts(referenceVO.referenceName, 6.5f, R.font.lato_regular, "top", R.dimen.margin_medium, R.color.white, "bold")
            childLayout.addView(referenceName)

            if (referenceVO.companyName?.isNotEmpty() == true ) {
                val position = setLayouts("${referenceVO.position} • ${referenceVO.companyName}", 6.5f, R.font.lato_regular, "", 0, R.color.white, "bold")
                childLayout.addView(position)
            } else {
                val position = setLayouts(referenceVO.position?:"", 6.5f, R.font.lato_regular, "", 0, R.color.white, "bold")
                childLayout.addView(position)
            }

            if (referenceVO.phoneNumber?.isNotEmpty() == true  || referenceVO.phoneNumber != "") {
                val phone = setLayouts("Tel: ${referenceVO.phoneNumber}", 6.5f, R.font.lato_regular, "top", R.dimen.margin_small, R.color.white, "")
                childLayout.addView(phone)
            }

            if (referenceVO.emailAddress?.isNotEmpty() == true  || referenceVO.emailAddress != "") {
                val mail = setLayouts("Mail: ${referenceVO.emailAddress}", 6.5f, R.font.lato_regular, "", 0, R.color.white, "")
                childLayout.addView(mail)
            }

            if (referenceVO.others?.isNotEmpty() == true  || referenceVO.others != "") {
                val others = setLayouts("${referenceVO.others}", 6.5f, R.font.lato_regular, "", 0, R.color.white, "")
                childLayout.addView(others)
            }

            referenceContentViewList.add(childLayout)

            // အရင်ထည့်ပြီးမှစစ်တာ
            binding.containerReference.addView(childLayout)
        }
    }

    private fun setUpContact() {       // each container ထဲကိုအရင်ထည့်ပြီး height တွက်ဖို့ content view list ထဲပြန်ထည့်
        val phoneView = setLayouts("${mCvVO?.personalDetails?.contact}", 6.5f, R.font.lato_light, "", R.dimen.margin_card_medium, R.color.white, "bold")
        binding.containerPhone.addView(setLayouts("Phone", 7.5f, R.font.lato_regular, "top", R.dimen.margin_small, R.color.white, "bold"))
        binding.containerPhone.addView(phoneView)
        contactContentViewList.add(binding.containerPhone)

        if (mCvVO?.personalDetails?.email?.isNotEmpty() == true) {
            val mailView = setLayouts("${mCvVO?.personalDetails?.email}", 6.5f, R.font.lato_light, "", R.dimen.margin_card_medium, R.color.white, "bold")
            binding.containerEmail.addView(setLayouts("Email", 7.5f, R.font.lato_regular, "top", R.dimen.margin_small, R.color.white, "bold"))
            binding.containerEmail.addView(mailView)
            contactContentViewList.add(binding.containerEmail)
        }

        if (mCvVO?.personalDetails?.address?.isNotEmpty() == true) {
            val addressView = setLayouts("${mCvVO?.personalDetails?.address}", 6.5f, R.font.lato_light, "", R.dimen.margin_card_medium, R.color.white, "bold")
            binding.containerAddress.addView(setLayouts("Address", 7.5f, R.font.lato_regular, "top", R.dimen.margin_small, R.color.white, "bold"))
            binding.containerAddress.addView(addressView)
            contactContentViewList.add(binding.containerAddress)
        }

        if (mCvVO?.personalDetails?.dateOfBirth?.isNotEmpty() == true) {
            val dobView = setLayouts("${mCvVO?.personalDetails?.dateOfBirth}", 6.5f, R.font.lato_light, "", R.dimen.margin_card_medium, R.color.white, "bold")
            binding.containerDoB.addView(setLayouts("Date of Birth", 7.5f, R.font.lato_regular, "top", R.dimen.margin_small, R.color.white, "bold"))
            binding.containerDoB.addView(dobView)
            contactContentViewList.add(binding.containerDoB)
        }

        if (mCvVO?.personalDetails?.nationality?.isNotEmpty() == true) {
            val nationalityView = setLayouts("${mCvVO?.personalDetails?.nationality}", 6.5f, R.font.lato_light, "", R.dimen.margin_card_medium, R.color.white, "bold")
            binding.containerNationality.addView(setLayouts("Nationality", 7.5f, R.font.lato_regular, "top", R.dimen.margin_small, R.color.white, "bold"))
            binding.containerNationality.addView(nationalityView)
            contactContentViewList.add(binding.containerNationality)
        }

        if (mCvVO?.personalDetails?.gender?.isNotEmpty() == true) {
            val websiteView = setLayouts("${mCvVO?.personalDetails?.gender}", 6.5f, R.font.lato_light, "", R.dimen.margin_card_medium, R.color.white, "bold")
            binding.containerWebsite.addView(setLayouts("Website", 7.5f, R.font.lato_regular, "top", R.dimen.margin_small, R.color.white, "bold"))
            binding.containerWebsite.addView(websiteView)
            contactContentViewList.add(binding.containerWebsite)
        }

    }

    // reusable func left and right side        views အကုန်ထည့်ပြီးမှ စစ်လိုက် ပို့လိုက်လုပ်မယ်
    private fun checkContentsVisibility() {

        rootViewAbsoluteHeightRight = currentPageLayoutRight.height - (20 * density).toInt() - (20 * density).toInt()
        rootViewAbsoluteHeightLeft = currentPageLayoutLeft.height - (18 * density).toInt() - (18 * density).toInt()

        // Right
        pageHeightRight = getCurrentPageHeight(containerToCheckHeight = containerFirstPageRightSide, 7)    // height is 0 because reduced all children                                                                                                                 // need to reduce Project and Skill below Exp
//        pageRightNameToObj = getCurrentPageHeight(containerFirstPageRightSide, 4) - getCurrentPageHeight(containerFirstPageRightSide, 6)  // Name to obj အကွာအဝေး
        pageRightPosition5To6 = getCurrentPageHeight(containerFirstPageRightSide, 5) - getCurrentPageHeight(containerFirstPageRightSide, 6)
        pageRightExp3To4 = getCurrentPageHeight(containerFirstPageRightSide, 3) - getCurrentPageHeight(containerFirstPageRightSide, 4)  //reduce 3 height က reduce 4 ထက် height ပိုများတယ်   / position ကနေ exp ရဲ့ heading height
        pageRightProj2To3 = getCurrentPageHeight(containerFirstPageRightSide, 2) - getCurrentPageHeight(containerFirstPageRightSide, 3)
        pageRightSkill1To2 = getCurrentPageHeight(containerFirstPageRightSide, 1) - getCurrentPageHeight(containerFirstPageRightSide, 2)

        // Left
        pageHeightLeft = getCurrentPageHeight(containerToCheckHeight = containerFirstPageLeftSide, 4) // child တစ်ခုမှမရှိသေးတဲ့ height but with fixed profile height
        pageLeftContact3To4 = getCurrentPageHeight(containerFirstPageLeftSide, 3) - getCurrentPageHeight(containerFirstPageLeftSide, 4)
        pageLeftEdu2To3 = getCurrentPageHeight(containerFirstPageLeftSide, 2) - getCurrentPageHeight(containerFirstPageLeftSide, 3)
        pageLeftAchi1To2 = getCurrentPageHeight(containerFirstPageLeftSide, 1) - getCurrentPageHeight(containerFirstPageLeftSide, 2)
        pageLeftRef0To1 = getCurrentPageHeight(containerFirstPageLeftSide, 0) - getCurrentPageHeight(containerFirstPageLeftSide, 1)

        val handler = Handler()
        handler.post {
            asynchronouslyCheckContent()
        }
    }

    // 3. visibility စစ်ပြီး contentViewList ထဲက content တစ်ခုချင်းစစ် ပြီး setUpTheChildViews method ကနေတစ်ဆင့် container အပြောင်းအလဲလုပ်
    // reusable func check visibility for all
    private fun asynchronouslyCheckContent() {

        //Name (Right)
        val width = MeasureSpec.makeMeasureSpec(nameContentView.width, MeasureSpec.EXACTLY)
        val height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        nameContentView.measure(width, height)
        nameContentView.layout(0, 0, nameContentView.measuredWidth, nameContentView.measuredHeight)
        val nameHeight = nameContentView.measuredHeight

        Log.d("adf4ff", "name $pageHeightRight + $nameHeight = ${pageHeightRight + nameHeight}")
        pageHeightRight += nameHeight        // add name height
        setUpTheChildViews(nameContentView, type = name, leftOrRight = "right")


        // Objective  (Right)
        mCvVO?.objective?.let {
            val widthMeasure = MeasureSpec.makeMeasureSpec(objectiveContentView.width, MeasureSpec.EXACTLY)
            val heightMeasure = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            objectiveContentView.measure(widthMeasure, heightMeasure)
            objectiveContentView.layout(0, 0, objectiveContentView.measuredWidth, objectiveContentView.measuredHeight)
            val objectiveHeight = objectiveContentView.measuredHeight

            pageHeightRight += pageRightPosition5To6  // add position height too

            Log.d("adf4ff", "obj $pageHeightRight + $objectiveHeight = ${pageHeightRight + objectiveHeight}")
            pageHeightRight += objectiveHeight        // add objective height
            setUpTheChildViews(objectiveContentView, type = objective, leftOrRight = "right")
        }


        // Work Experience (Right)
        if (mCvVO?.workExperiences?.isNotEmpty() == true) {
            Log.d("adf4ff", "pageRightExp height 3-4 $pageHeightRight + $pageRightExp3To4 = ${pageHeightRight+pageRightExp3To4}")
            pageHeightRight += pageRightExp3To4  // add exp heading height
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
                        pageHeightRight += contentHeight    // add each content height
                        setUpTheChildViews(content as LinearLayout, type = workExperience, leftOrRight = "right")
                    }
                    if (currentPageRight == 2) {     // Add content to the new page
                        setUpTheChildViews(content as LinearLayout, type = workExperience, leftOrRight = "right")
                    }
                } else {  // change all Layouts here
                    if (currentPageRight == 1) {     // ပထမဆုံးအကြိမ် first pageမှာမဆန့်တာဆိုရင် currentPage, RootView, pageNum အကုန်အရင်ပြောင်း

                        currentPageRight = 2
                    }
                    setUpTheChildViews(content as LinearLayout, type = workExperience, leftOrRight = "right")
                }
            }
            sendOtherLayoutsToAnotherPage(type = workExperience)
        }


        // Project (Right)
        if (mCvVO?.projectDetails?.isNotEmpty() == true) {
            Log.d("adf4ff", "added different 3-2 $pageHeightRight + $pageRightProj2To3 = ${pageHeightRight+pageRightProj2To3}")
            pageHeightRight += pageRightProj2To3   // add project heading height
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
            Log.d("adf4ff", "added different 2-1 $pageHeightRight + $pageRightSkill1To2 = ${pageHeightRight+pageRightSkill1To2}")
            pageHeightRight += pageRightSkill1To2
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
            Log.d("adf4ff", "\n")
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


        // LEFT

        // Contact (Left)
        Log.d("a3dsrwe", "added different 4-3 $pageHeightLeft + $pageLeftContact3To4 = ${pageHeightLeft + pageLeftContact3To4}")
        pageHeightLeft += pageLeftContact3To4
        contactContentViewList.forEachIndexed { index, content ->

            // Manually measure and layout the (not need if the layout height is displayed fully) can setUp as val totalHeight = viewPodLayout.height
            val widthMeasureSpec = MeasureSpec.makeMeasureSpec(content.width, MeasureSpec.EXACTLY)
            val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            content.measure(widthMeasureSpec, heightMeasureSpec)
            content.layout(0, 0, content.measuredWidth, content.measuredHeight)

            val contentHeight = content.measuredHeight

            Log.d("a3dsrwe", "contact $pageHeightLeft + $contentHeight = ${pageHeightLeft + contentHeight}")
            pageHeightLeft += contentHeight        // add contact height

            setUpTheChildViews(content as LinearLayout, type = contact, leftOrRight = "left")
        }

        // Education (Left)
        if (mCvVO?.educationDetails?.isNotEmpty() == true) {
            Log.d("a3dsrwe", "added different 3-2 $pageHeightLeft + $pageLeftEdu2To3 = ${pageHeightLeft + pageLeftEdu2To3}")
            pageHeightLeft += pageLeftEdu2To3 + (8 * density).toInt()
            educationContentViewList.forEachIndexed { index, content ->

                // Manually measure and layout the (not need if the layout height is displayed fully) can setUp as val totalHeight = viewPodLayout.height
                val widthMeasureSpec = MeasureSpec.makeMeasureSpec(content.width, MeasureSpec.EXACTLY)
                val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                content.measure(widthMeasureSpec, heightMeasureSpec)
                content.layout(0, 0, content.measuredWidth, content.measuredHeight)

                val contentHeight = content.measuredHeight
                val contentFitsInFirstPage = pageHeightLeft + contentHeight <= rootViewAbsoluteHeightLeft

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
                    }
                    // page က already 2 ဖြစ်ရင်
                    setUpTheChildViews(content as LinearLayout, type = education, leftOrRight = "left")
                }
            }
            sendOtherLayoutsToAnotherPage(type = education)
        }

        // Achievement (Left)
        if (mCvVO?.achievements?.isNotEmpty() == true) {
            Log.d("a3dsrwe", "added different 2-1 $pageHeightLeft + $pageLeftAchi1To2 = ${pageHeightLeft + pageLeftAchi1To2}")
            pageHeightLeft += pageLeftAchi1To2 + (8 * density).toInt()
            achievementContentViewList.forEachIndexed { index, content ->

                // Manually measure and layout the (not need if the layout height is displayed fully) can setUp as val totalHeight = viewPodLayout.height
                val widthMeasureSpec = MeasureSpec.makeMeasureSpec(content.width, MeasureSpec.EXACTLY)
                val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                content.measure(widthMeasureSpec, heightMeasureSpec)
                content.layout(0, 0, content.measuredWidth, content.measuredHeight)

                val contentHeight = content.measuredHeight
                val contentFitsInFirstPage = pageHeightLeft + contentHeight <= rootViewAbsoluteHeightLeft

                if (contentFitsInFirstPage) {

                    if (currentPageLeft == 1) {    // Content fits on the current page
                        Log.d("a3dsrwe", "Achi $index $pageHeightLeft + $contentHeight = ${pageHeightLeft + contentHeight} <= $rootViewAbsoluteHeightLeft")
                        pageHeightLeft += contentHeight
                        setUpTheChildViews(content as LinearLayout, type = achievement, leftOrRight = "left")
                    }
                    if (currentPageLeft == 2) {     // Add content to the new page
                        setUpTheChildViews(content as LinearLayout, type = achievement, leftOrRight = "left")
                    }

                } else {  // change all Layouts here
                    if (currentPageLeft == 1) { //ပထမဆုံးအကြိမ် first pageမှာမဆန့်တာဆိုရင် currentPage, RootView, pageNum အကုန်အရင်ပြောင်း
                        currentPageLeft = 2

                        if (index == 0) {
                            sendOtherLayoutsToAnotherPage(type = education)   // * education ကနေပို့သလို
                        }
                    }
                    setUpTheChildViews(content as LinearLayout, type = achievement, leftOrRight = "left")
                }
            }
        sendOtherLayoutsToAnotherPage(type = achievement)
        }

        // Reference (Left)
        if (mCvVO?.references?.isNotEmpty() == true) {
            Log.d("a3dsrwe", "added different 1-0 $pageHeightLeft + $pageLeftRef0To1 = ${pageHeightLeft + pageLeftRef0To1}")
            pageHeightLeft += pageLeftRef0To1 + (8 * density).toInt()
            referenceContentViewList.forEachIndexed { index, content ->

                // Manually measure and layout the (not need if the layout height is displayed fully) can setUp as val totalHeight = viewPodLayout.height
                val widthMeasureSpec = MeasureSpec.makeMeasureSpec(content.width, MeasureSpec.EXACTLY)
                val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                content.measure(widthMeasureSpec, heightMeasureSpec)
                content.layout(0, 0, content.measuredWidth, content.measuredHeight)

                val contentHeight = content.measuredHeight
                val contentFitsInFirstPage = pageHeightLeft + contentHeight <= rootViewAbsoluteHeightLeft

                if (contentFitsInFirstPage) {

                    if (currentPageLeft == 1) {    // Content fits on the current page
                        Log.d("a3dsrwe", "Ref $index $pageHeightLeft + $contentHeight = ${pageHeightLeft + contentHeight} <= $rootViewAbsoluteHeightLeft")
                        pageHeightLeft += contentHeight
                        setUpTheChildViews(content as LinearLayout, type = reference, leftOrRight = "left")
                    }
                    if (currentPageLeft == 2) {     // Add content to the new page
                        setUpTheChildViews(content as LinearLayout, type = reference, leftOrRight = "left")
                    }

                } else {  // change all Layouts here
                    if (currentPageLeft == 1) { //ပထမဆုံးအကြိမ် first pageမှာမဆန့်တာဆိုရင် currentPage, RootView, pageNum အကုန်အရင်ပြောင်း
                        currentPageLeft = 2

                        if (index == 0) {
                            sendOtherLayoutsToAnotherPage(type = achievement)     // * achievement ကနေပို့သလို
                        }
                    }
                    setUpTheChildViews(content as LinearLayout, type = reference, leftOrRight = "left")
                }
            }
//        sendOtherLayoutsToAnotherPage(type = achievement)  no need for the last element
        }
    }

    // 4. child တစ်ခုချင်းစီကနေ ခေါ်ပြီး container ထဲ addView
    // reusable func asynchronouslyCheckContent ကနေတစ်ခေါက်ပြီးတစ်ခေါက် ခေါ်ပြီး pages တွေထဲကို add and remove
    private fun setUpTheChildViews(childLayout: View, type: String, leftOrRight: String) {

        // Right
        if (leftOrRight == "right") {
            // Right 1
            if (currentPageRight == 1) {
                val oldParent = childLayout.parent as? ViewGroup
                oldParent?.removeView(childLayout) // Remove the childLayout from the current parent

                if (type == name) binding.containerName.addView(childLayout)
                if (type == objective) binding.containerObjective.addView(childLayout)
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

                if (type == contact) binding.containerContact.addView(childLayout)
                if (type == education) binding.containerEducation.addView(childLayout)
                if (type == achievement) binding.containerAchievement.addView(childLayout)
                if (type == reference) binding.containerReference.addView(childLayout)
            }
            // Left 2
            if (currentPageLeft == 2) {
                binding.secondRootViewSecondTwo.visibility = View.VISIBLE

                val oldParent = childLayout.parent as? ViewGroup
                oldParent?.removeView(childLayout) // Remove the childLayout from the current parent

                if (type == education) { containerSecondPageLeftSide.addView(childLayout) }  // isSentToSecondPageRight ကအောက်ကကောင်တွေအတွက်ပဲလိုတယ်

                if (isSentToSecondPageLeft) {  // အပေါ်ဆုံး view မှာထဲက မဆန့်လို့ nextpage ပို့ပြီးသားဆိုရင် container ထဲ တန်းထည့်လိုက်ရုံပဲ
                    if (type == achievement) { binding.containerAchievement.addView(childLayout) }
                    if (type == reference) binding.containerReference.addView(childLayout)

                } else {  // အခုမှပို့ရမှာဆိုရင် root ထဲတန်းထည့်လိုက်ရုံပဲ / first element ဆိုရင် header ကိုပါ ပို့ဖို့အတွက်က checkContent ထဲမှာစစ်ပြီးသား
                    if (type == achievement) { containerSecondPageLeftSide.addView(childLayout) }
                    if (type == reference) { containerSecondPageLeftSide.addView(childLayout) }
                }
            }
        }
    }

    // reusable func if currentPage == 2, page 2 ကို သူ့အောက်က layouts တွေကို ဟိုဘက်ပို့တဲ့ func
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

                    // move Achievement and Reference to second page
                    if (!isSentToSecondPageLeft) {
                        val oldParentAchievement = binding.containerAchievement.parent as? ViewGroup
                        oldParentAchievement?.removeView(binding.containerAchievement)
                        containerSecondPageLeftSide.addView(binding.containerAchievement)

                        val oldParentReference = binding.containerReference.parent as? ViewGroup
                        oldParentReference?.removeView(binding.containerReference)
                        containerSecondPageLeftSide.addView(binding.containerReference)
                        isSentToSecondPageLeft = true
                    }
                }
            }
            achievement -> {
                if (currentPageLeft == 2) {
                    // move Reference to second page
                    if (!isSentToSecondPageLeft) {
                        val oldParentReference = binding.containerReference.parent as? ViewGroup
                        oldParentReference?.removeView(binding.containerReference)
                        containerSecondPageLeftSide.addView(binding.containerReference)
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