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
import com.smtz.cvgenius.common.components.CvSingleton
import com.smtz.cvgenius.databinding.ViewPodZresumeFreeOneBinding
import com.smtz.cvgenius.domain.model.AchievementVO
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.EducationDetailVO
import com.smtz.cvgenius.domain.model.ProjectDetailVO
import com.smtz.cvgenius.domain.model.ReferenceVO
import com.smtz.cvgenius.domain.model.SkillsVO
import com.smtz.cvgenius.domain.model.WorkExperienceVO
import com.smtz.cvgenius.common.utils.getCurrentPageHeight
import com.smtz.cvgenius.common.utils.setUpMargins
import com.smtz.cvgenius.utils.achievement
import com.smtz.cvgenius.utils.boldText
import com.smtz.cvgenius.utils.bottomMargin
import com.smtz.cvgenius.utils.education
import com.smtz.cvgenius.utils.endMargin
import com.smtz.cvgenius.utils.leftSide
import com.smtz.cvgenius.utils.mid
import com.smtz.cvgenius.utils.projectDetail
import com.smtz.cvgenius.utils.resumeFreeOneTopOne
import com.smtz.cvgenius.utils.resumeFreeOneTopTwo
import com.smtz.cvgenius.utils.rightSide
import com.smtz.cvgenius.utils.signature
import com.smtz.cvgenius.utils.skill
import com.smtz.cvgenius.utils.topBottomMargin
import com.smtz.cvgenius.utils.topMargin
import com.smtz.cvgenius.utils.workExperience

class ResumeFreeOneViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : BaseViewPod(context, attrs) {

    private lateinit var binding: ViewPodZresumeFreeOneBinding
    private val density = resources.displayMetrics.density

    private var mWorkExperienceList: List<WorkExperienceVO> = listOf()
    private var mEducationList: List<EducationDetailVO> = listOf()
    private var mProjectDetailList: List<ProjectDetailVO> = listOf()
    private var mAchievementList: List<AchievementVO> = listOf()
    private var mReferenceList: List<ReferenceVO> = listOf()
    private var mSkillList: List<SkillsVO> = listOf()

    private var firstElementContentViewList: MutableList<View> = mutableListOf()
    private var objectiveContentView: View = View(context)
    private var experienceContentViewList: MutableList<View> = mutableListOf()
    private var educationContentViewList: MutableList<View> = mutableListOf()
    private var projectContentViewList: MutableList<View> = mutableListOf()
    private var achievementContentViewList: MutableList<View> = mutableListOf()
    private var referenceContentViewList: MutableList<View> = mutableListOf()
    private var skillContentViewList: MutableList<View> = mutableListOf()

    private var mCvVO: CvVO? = null
    private var isInitialSetupDone = false
    private var isSentToSecondPageRight = false
    private var isSentToSecondPageLeft = false
    private var currentPageRight = 1
    private var currentPageLeft = 1

    private var rootViewAbsoluteFullHeight = 0F     // page တစ်ခုလုံးရဲ့ height
    private var rootViewAbsoluteHeightRight = 0
    private var rootViewAbsoluteHeightLeft = 0

    private var profileImageHeight = 0F         // pageHeightTop နဲ့ ယှဉ်ဖို့အတွက်ပဲ
    private var pageHeightTop = 0F              // content တွေရဲ့ထပ်ထပ်ပေါင်းလာမယ့် height / Absolute height နဲ့တွက်ဖို့

    private var pageHeightLeft = 0F
    private var pageHeightRight = 0F


    override fun onFinishInflate() {
        binding = ViewPodZresumeFreeOneBinding.bind(this)
        mCvVO = CvSingleton.instance.cvVO!!

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

        // 1.contentView အဖြစ်အရင် save
        setUpPersonalDetails()

        if (mCvVO?.objective?.isNotEmpty() == true) {
            val childLayout = LinearLayout(context)
            childLayout.orientation = VERTICAL

            var heading = setLayouts("CARRIER OVERVIEW", 9.96f, R.font.nunito_medium, "", 0, R.color.black, boldText)
            var objective = setLayouts("${mCvVO?.objective}", 7.13f, R.font.nunito_medium, "", 0, R.color.black, null)
            heading = setUpMargins(heading, R.dimen.margin_medium, R.dimen.margin_small, null, null)
            objective = setUpMargins(objective, null, R.dimen.margin_medium, null, null)

            childLayout.addView(heading)
            childLayout.addView(objective)
            objectiveContentView = childLayout  // to calculate view height
            // အရင်ထည့်ပြီးမှစစ်
            binding.containerObjective.addView(childLayout)    // စမ်းထည့်ကြည့်ရုံပဲ တကယ်တမ်းမပေါ်ဘူး setUpTheChildViews မှတကယ်ပေါ်တာ

        } else binding.viewBelowObjective.visibility = View.INVISIBLE

        if (mCvVO?.workExperiences?.isNotEmpty() == true) {
            mWorkExperienceList = mCvVO?.workExperiences!!
            setUpWorkExp()
        } else binding.containerWorkExp.visibility = View.GONE

        if (mCvVO?.projectDetails?.isNotEmpty() == true) {
            mProjectDetailList = mCvVO?.projectDetails!!
            setUpProjectDetail()
        } else binding.containerProject.visibility = View.GONE

        if (mCvVO?.signature != null) {
            val decodedBytes = Base64.decode(mCvVO?.signature, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
            binding.ivSignature.setImageBitmap(bitmap)

        } else binding.ivSignature.visibility = View.GONE


        if (mCvVO?.educationDetails?.isNotEmpty() == true) {
            mEducationList = mCvVO?.educationDetails!!
            setUpEducation()
        } else binding.containerEducation.visibility = View.GONE

        if (mCvVO?.achievements?.isNotEmpty() == true) {
            mAchievementList = mCvVO?.achievements!!
            setUpAchievement()
        } else {
            binding.containerAchievement.visibility = View.GONE
        }

        if (mCvVO?.skills?.isNotEmpty() == true) {
            mSkillList = mCvVO?.skills!!
            setUpSkill()
        } else binding.containerSkill.visibility = View.GONE

        checkContentsVisibility()
    }

    private fun setUpPersonalDetails() {
        binding.apply {

            if (mCvVO?.personalDetails?.firstName?.isNotEmpty() == true) {
                val contentView = setLayouts("${mCvVO?.personalDetails?.firstName} ${mCvVO?.personalDetails?.lastName}", 22.87F, R.font.bebas_neue_regular, "top", R.dimen.margin_small, R.color.black, "" )
                contentView.isAllCaps = true
                containerName.addView(contentView)
                firstElementContentViewList.add(containerName)   // to calculate dynamic height
            }

            if (mCvVO?.personalDetails?.professionalTitle?.isNotEmpty() == true) {
                val contentView = setLayouts(mCvVO?.personalDetails?.professionalTitle?:"", 10f, R.font.nunito_medium, "topbottom", R.dimen.margin_small, R.color.black, "bold" )
                contentView.isAllCaps = true
                contentView.letterSpacing = 0.02F
                containerPosition.addView(contentView)
                firstElementContentViewList.add(containerPosition)   // to calculate dynamic height
            }

            if (mCvVO?.personalDetails?.address?.isNotEmpty() == true) {
                val contentView = setLayouts("Address: ${mCvVO?.personalDetails?.address}   ", 7.13F, R.font.nunito_medium, topMargin, R.dimen.margin_small, R.color.black, null )
                containerAddress.addView(contentView)
                firstElementContentViewList.add(containerAddress)   // to calculate dynamic height
            }

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
            firstElementContentViewList.add(gridLayoutPersonalDetail)     // to calculate dynamic height
        }
    }

    private fun setUpWorkExp() {

//        val headingLayout = LinearLayout(context)
//        headingLayout.orientation = VERTICAL

        var experienceHeadingView = setLayouts("EXPERIENCE", 11f, R.font.nunito_medium, "", 0, R.color.black, boldText)
        experienceHeadingView = setUpMargins(experienceHeadingView, R.dimen.margin_medium, R.dimen.margin_small, null, null)
        experienceContentViewList.add(experienceHeadingView)
        binding.containerWorkExp.addView(experienceHeadingView)

        mWorkExperienceList.forEachIndexed { index, workExperienceVO ->

            val childLayout = LinearLayout(context)
            childLayout.orientation = VERTICAL

            val workPositionTextView = setLayouts(workExperienceVO.position, 9.95f, R.font.nunito_medium, topMargin, R.dimen.margin_small, R.color.black, null)
            childLayout.addView(workPositionTextView)

            val companyNameAndDateTextView: TextView =
                if (workExperienceVO.startDate?.isNotEmpty() == true) {
                    if (workExperienceVO.endDate?.isNotEmpty() == true) {
                        setLayouts("${workExperienceVO.company}  |  ${workExperienceVO.startDate} - ${workExperienceVO.endDate}", 7.13f, R.font.nunito_medium, "", 0, R.color.black, null)
                    } else
                        setLayouts("${workExperienceVO.company}  |  ${workExperienceVO.startDate}", 7.13f, R.font.nunito_medium,  "", 0, R.color.black, null)
                } else
                setLayouts(workExperienceVO.company, 7.13f, R.font.nunito_medium,  "", 0, R.color.black, null)
            childLayout.addView(companyNameAndDateTextView)

            if (workExperienceVO.description?.isNotEmpty() == true) {
                val workDescriptionTextView = setLayouts(workExperienceVO.description, 7.13f, R.font.nunito_medium, topMargin, R.dimen.margin_ultra_small, R.color.black, null)
                childLayout.addView(workDescriptionTextView)
            }
//            Log.d("bbbbb", "${workExperienceVO.position}  ${workExperienceVO.description?.isNotEmpty() == true}")
            experienceContentViewList.add(childLayout)
            // အရင်ထည့်ပြီးမှစစ်တာ
            binding.containerWorkExp.addView(childLayout)
        }
//        addContentToPagesWorkExp(contentList)
    }

    private fun setUpProjectDetail() {
        var headingView = setLayouts("PROJECT", 11f, R.font.nunito_medium, "", 0, R.color.black, boldText)
        headingView = setUpMargins(headingView, R.dimen.margin_medium, null, null, null)
        projectContentViewList.add(headingView)
        binding.containerProject.addView(headingView)

        mProjectDetailList.forEachIndexed { index, projectDetailVO ->

            val childLayout = LinearLayout(context)
            childLayout.orientation = VERTICAL

            val titleTextView = setLayouts(projectDetailVO.projectTitle, 10f, R.font.nunito_medium, topMargin, R.dimen.margin_card_medium, R.color.black, null)
            childLayout.addView(titleTextView)

            val positionAndDurationTextView: TextView
            if (projectDetailVO.position?.isNotEmpty() != true) {  // no position
                if (projectDetailVO.endDate?.isNotEmpty() == true) {
                    positionAndDurationTextView = setLayouts("${projectDetailVO.startDate} - ${projectDetailVO.endDate}", 7.13f, R.font.nunito_medium, "", 0, R.color.black, null)
                    childLayout.addView(positionAndDurationTextView)
                }

            }else    // sure with position
                if (projectDetailVO.endDate?.isNotEmpty() == true) {
                    positionAndDurationTextView = setLayouts("${projectDetailVO.position}  |  ${projectDetailVO.startDate} - ${projectDetailVO.endDate}", 7.13f, R.font.nunito_medium, "", 0, R.color.black, null)
                    childLayout.addView(positionAndDurationTextView)
                }

            if (projectDetailVO.projectDescription?.isNotEmpty() == true) {
                val projectDescriptionTextView = setLayouts(projectDetailVO.projectDescription?:"", 7.13f, R.font.nunito_medium,  topMargin, R.dimen.margin_ultra_small, R.color.black, null)
                childLayout.addView(projectDescriptionTextView)
            }

            if (projectDetailVO.credentialURL?.isNotEmpty() == true) {
                val credentialURL = setLayouts(projectDetailVO.credentialURL?:"", 7.13f, R.font.nunito_medium, "", 0, R.color.black, null)
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
    }

    private fun setUpEducation() {
        var headingView = setLayouts("EDUCATION", 11f, R.font.nunito_medium, "", 0, R.color.black, boldText)
        headingView = setUpMargins(headingView, R.dimen.margin_medium, R.dimen.margin_small, null, null)
        educationContentViewList.add(headingView)
        binding.containerEducation.addView(headingView)

        mEducationList.forEachIndexed { index, detailVO ->
            val host = LinearLayout(context)
            host.orientation = VERTICAL

            val parentLayout = LinearLayout(context)
            parentLayout.orientation = HORIZONTAL
            parentLayout.weightSum = 5f

            val firstChildLayout = LinearLayout(context)
            firstChildLayout.orientation = VERTICAL
            firstChildLayout.layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT, 3f)

            val degreeTextView =
                if (detailVO.levelOfEducation.isNotEmpty()) setLayouts("${detailVO.levelOfEducation} of ${detailVO.diplomaName}", 7.13f, R.font.nunito_medium, topMargin, R.dimen.margin_small, R.color.black, null)
                else setLayouts(detailVO.diplomaName, 7.13f, R.font.nunito_medium, topMargin, R.dimen.margin_small, R.color.black, null)
            firstChildLayout.addView(degreeTextView)

            val universityTextView = setLayouts(detailVO.schoolName, 7.13f, R.font.nunito_medium, "", 0, R.color.black, null)
            firstChildLayout.addView(universityTextView)

            parentLayout.addView(firstChildLayout)

            val secondChildLayout = setLayouts("${detailVO.startDate}  -  ${detailVO.endDate}", 7.13f, R.font.nunito_medium, "", 0, R.color.black, null)
            secondChildLayout.layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT, 2f)
            secondChildLayout.gravity = TEXT_ALIGNMENT_CENTER
            parentLayout.addView(secondChildLayout)

            host.addView(parentLayout)

            if (detailVO.credentialURL?.isNotEmpty() == true) {
                val credentialURL = setLayouts(detailVO.credentialURL?:"", 7.13f, R.font.nunito_medium, "", 0, R.color.black, null)
                Linkify.addLinks(credentialURL, Patterns.WEB_URL, "http://")
                credentialURL.autoLinkMask = Linkify.WEB_URLS
                credentialURL.isClickable = true
                credentialURL.setLinkTextColor(resources.getColor(R.color.colorLinkText))
                credentialURL.movementMethod = LinkMovementMethod.getInstance()
                host.addView(credentialURL)
            }

            educationContentViewList.add(host)
            // အရင်ထည့်ပြီးမှစစ်တာ
            binding.containerEducation.addView(host)
        }
    }

    private fun setUpAchievement() {

        var headingView = setLayouts("ACHIEVEMENT", 11f, R.font.nunito_medium, "", 0, R.color.black, boldText)
        headingView = setUpMargins(headingView, R.dimen.margin_medium, null, null, null)
        achievementContentViewList.add(headingView)
        binding.containerAchievement.addView(headingView)

        mAchievementList.forEachIndexed { index, achievementVO ->

            val childLayout = LinearLayout(context)

            val skillName = setLayouts("● ${achievementVO.achievement}", 7.13f, R.font.nunito_medium, topMargin, R.dimen.margin_small, R.color.black, null)
            childLayout.addView(skillName)

            achievementContentViewList.add(childLayout)

            // အရင်ထည့်ပြီးမှစစ်တာ
            binding.containerAchievement.addView(childLayout)
        }
    }

    private fun setUpSkill() {

        var headingView = setLayouts("SKILL", 11f, R.font.nunito_medium, topMargin, R.dimen.ten_dp, R.color.black, boldText)
//        headingView = setUpMargins(headingView, "top24", "bottom-16", null, null)
        skillContentViewList.add(headingView)
        binding.containerSkill.addView(headingView)

        mSkillList.forEachIndexed { index, skillsVO ->

            val childLayout = LinearLayout(context)

            val skillName = setLayouts("● ${skillsVO.skill}", 7.13f, R.font.nunito_medium, topMargin, R.dimen.margin_small, R.color.black, null)
            childLayout.addView(skillName)

            skillContentViewList.add(childLayout)

            // အရင်ထည့်ပြီးမှစစ်တာ
            binding.containerSkill.addView(childLayout)
        }
    }

    // reusable func left and right side        views အကုန်ထည့်ပြီးမှ စစ်လိုက် ပို့လိုက်လုပ်မယ်
    private fun checkContentsVisibility() {

        rootViewAbsoluteFullHeight = binding.firstRootView.height - (20 * density).toInt() - (20 * density)

        // Asynchronous fun အထဲမှာပဲ လုပ်တော့တယ် မဟုတ်ရင် xml ထဲက အသေ size ပဲ ယူလို့
        // အပေါ်မှာ view တွေကို အစမ်း add ထားပြီး match parent လုပ်ထားလို့ full height ကို တွက်လို့ရသွားတယ် မဟုတ်ရင် အသေ size ပဲယူတော့မှာ
//        rootViewAbsoluteHeightRight = binding.containerFirstPageRight.height  // profile အောက်က margin, Both side အောက်က margin, 2 underlines
//        rootViewAbsoluteHeightLeft = binding.containerFirstPageLeft.height   // profile အောက်က margin, Both side အောက်က margin, 2 underlines

        // Mid
        pageHeightTop = getCurrentPageHeight(containerToCheckHeight = binding.firstRootView, 6)  // height is 0 because reduced all children

        val handler = Handler()
        handler.post {
            asynchronouslyCheckContent()
        }
    }

    // 3. visibility စစ်ပြီး contentViewList ထဲက content တစ်ခုချင်းစစ် ပြီး setUpTheChildViews method ကနေတစ်ဆင့် container အပြောင်းအလဲလုပ်
    // reusable func check visibility for all
    private fun asynchronouslyCheckContent() {

//        rootViewAbsoluteFullHeight -= if (pageHeightTop > profileImageHeight) pageHeightTop else profileImageHeight   // မမှန်ဘူး
        rootViewAbsoluteHeightRight = binding.containerFirstPageRight.height // profile အောက်က margin, Both side အောက်က margin
        rootViewAbsoluteHeightLeft = binding.containerFirstPageLeft.height  // profile အောက်က margin, Both side အောက်က margin
//        Log.d("aaaaaaaaaaaaaaa", "$pageHeightTop, $profileImageHeight, $rootViewAbsoluteFullHeight $rootViewAbsoluteHeightRight $rootViewAbsoluteHeightLeft ")


        // First element (Top)
        firstElementContentViewList.forEachIndexed { index, content ->

            // Manually measure and layout the (not need if the layout height is displayed fully) can setUp as val totalHeight = viewPodLayout.height
            val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(content.width, View.MeasureSpec.EXACTLY)
            val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            content.measure(widthMeasureSpec, heightMeasureSpec)
            content.layout(0, 0, content.measuredWidth, content.measuredHeight)

            val contentHeight = content.measuredHeight

//            Log.d("aaaaaaaaaaaaaaa", "top 1 $pageHeightTop + $contentHeight = ${pageHeightTop + contentHeight}")
            pageHeightTop += contentHeight        // add contact height

            setUpTheChildViews(content, type = resumeFreeOneTopOne, leftOrRight = mid)
        }

        // second element Objective (Top)
        mCvVO?.objective?.let {
            val widthMeasure = View.MeasureSpec.makeMeasureSpec(objectiveContentView.width, View.MeasureSpec.EXACTLY)
            val heightMeasure = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            objectiveContentView.measure(widthMeasure, heightMeasure)
            objectiveContentView.layout(0, 0, objectiveContentView.measuredWidth, objectiveContentView.measuredHeight)
            val objectiveHeight = objectiveContentView.measuredHeight

//            Log.d("aaaaaaaaaaaaaaa", "obj $pageHeightTop + $objectiveHeight = ${pageHeightTop + objectiveHeight}")
            pageHeightTop += objectiveHeight        // add objective height
            setUpTheChildViews(objectiveContentView, type = resumeFreeOneTopTwo, leftOrRight = mid)
        }

        mCvVO?.profileImage?.let {
            val profileImageView : View = binding.ivProfileImage
            profileImageHeight = profileImageView.measuredHeight * 1.6F            // မညီလို့ ညီအောင်ညှိထားသည်

//            Log.d("aaaaaaaaaaaaaaa", "profile  $profileImageHeight ")
        }

        // Right

        // Work Experience (Right)
        if (mCvVO?.workExperiences?.isNotEmpty() == true) {
            for (content in experienceContentViewList) {

                val widthMeasureSpec = MeasureSpec.makeMeasureSpec(content.width, MeasureSpec.EXACTLY)
                val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                content.measure(widthMeasureSpec, heightMeasureSpec)
                content.layout(0, 0, content.measuredWidth, content.measuredHeight)

                val contentHeight = content.measuredHeight

                if (pageHeightRight + contentHeight <= rootViewAbsoluteHeightRight) {

                    if (currentPageRight == 1) {    // Content fits on the current page
                        Log.d("aaaaaaaaaaaaaaa", "Exp $pageHeightRight + $contentHeight = ${pageHeightRight + contentHeight} <= $rootViewAbsoluteHeightRight")
                        pageHeightRight += contentHeight    // add each content height
                        setUpTheChildViews(content, type = workExperience, leftOrRight = rightSide)
                    }
                    if (currentPageRight == 2) {     // Add content to the new page
                        setUpTheChildViews(content, type = workExperience, leftOrRight = rightSide)
                    }
                } else {
                    if (currentPageRight == 1) {     // ပထမဆုံးအကြိမ် first pageမှာမဆန့်တာဆိုရင် pageNum အရင်ပြောင်း
                        currentPageRight = 2
                    }
                    setUpTheChildViews(content, type = workExperience, leftOrRight = rightSide)
                }
            }
            sendOtherLayoutsToAnotherPage(type = workExperience)
        }

        // Project Detail (Right)
        if (mCvVO?.projectDetails?.isNotEmpty() == true) {
            projectContentViewList.forEachIndexed { index, content ->

                val widthMeasureSpec = MeasureSpec.makeMeasureSpec(content.width, MeasureSpec.EXACTLY)
                val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                content.measure(widthMeasureSpec, heightMeasureSpec)
                content.layout(0, 0, content.measuredWidth, content.measuredHeight)

                val contentHeight = content.measuredHeight

                if (pageHeightRight + contentHeight <= rootViewAbsoluteHeightRight) {

                    if (currentPageRight == 1) {    // Content fits on the current page
                        Log.d("aaaaaaaaaaaaaaa", "Proj $pageHeightRight + $contentHeight = ${pageHeightRight + contentHeight} <= $rootViewAbsoluteHeightRight")
                        pageHeightRight += contentHeight    // add each content height
                        setUpTheChildViews(content, type = projectDetail, leftOrRight = rightSide)
                    }
                    if (currentPageRight == 2) {     // Add content to the new page
                        setUpTheChildViews(content, type = projectDetail, leftOrRight = rightSide)
                    }
                } else {
                    if (currentPageRight == 1) {     // ပထမဆုံးအကြိမ် first pageမှာမဆန့်တာဆိုရင် pageNum အရင်ပြောင်း
                        currentPageRight = 2

                        if (index == 1)
                            sendOtherLayoutsToAnotherPage(type = workExperience)
                    }
                    setUpTheChildViews(content, type = projectDetail, leftOrRight = rightSide)
                }
            }
            sendOtherLayoutsToAnotherPage(type = projectDetail)
        }

        // Signature  (Right)
        mCvVO?.signature?.let {
            val signatureView : View = binding.ivSignature
            val signatureHeight = signatureView.measuredHeight  // static height
            val contentFitsInFirstPage = pageHeightRight + signatureHeight <= rootViewAbsoluteHeightRight

            if (contentFitsInFirstPage) {

                if (currentPageRight == 1) {    // Content fits on the current page
                    Log.d("aaaaaaaaaaaaaaa", "Sign $pageHeightRight + $signatureHeight = ${pageHeightRight + signatureHeight} <= $rootViewAbsoluteHeightRight")

                    pageHeightRight += signatureHeight
                    setUpTheChildViews(signatureView, type = signature, leftOrRight = rightSide)
                }
                if (currentPageRight == 2){     // Add content to the new page
                    setUpTheChildViews(signatureView, type = signature, leftOrRight = rightSide)
                }
            }
            else {
                currentPageRight = 2
                setUpTheChildViews(signatureView, type = signature, leftOrRight = rightSide)
            }
        }

        // LEFT

        // Education (Left)
        if (mCvVO?.educationDetails?.isNotEmpty() == true) {
            educationContentViewList.forEachIndexed { index, content ->

                val widthMeasureSpec = MeasureSpec.makeMeasureSpec(content.width, MeasureSpec.EXACTLY)
                val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                content.measure(widthMeasureSpec, heightMeasureSpec)
                content.layout(0, 0, content.measuredWidth, content.measuredHeight)

                val contentHeight = content.measuredHeight

                if (pageHeightLeft + contentHeight <= rootViewAbsoluteHeightLeft) { // content ကဆန့်တယ်ဆိုရင်

                    if (currentPageLeft == 1) {    // Content fits on the current page
                        Log.d("bbbbbbbbbbbb", "Edu $index $pageHeightLeft + $contentHeight = ${pageHeightLeft + contentHeight} <= $rootViewAbsoluteHeightLeft")
                        pageHeightLeft += contentHeight
                        setUpTheChildViews(content, type = education, leftOrRight = leftSide)
                    }
                    if (currentPageLeft == 2) {     // Add content to the new page
                        setUpTheChildViews(content, type = education, leftOrRight = leftSide)
                    }
                } else {
                    if (currentPageLeft == 1) {
                        currentPageLeft = 2
                    }
                    setUpTheChildViews(content, type = education, leftOrRight = leftSide)
                }
            }
            sendOtherLayoutsToAnotherPage(type = education)
        }

        // Achievement (Left)
        if (mCvVO?.achievements?.isNotEmpty() == true) {
            achievementContentViewList.forEachIndexed { index, content ->

                val widthMeasureSpec = MeasureSpec.makeMeasureSpec(content.width, MeasureSpec.EXACTLY)
                val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                content.measure(widthMeasureSpec, heightMeasureSpec)
                content.layout(0, 0, content.measuredWidth, content.measuredHeight)

                val contentHeight = content.measuredHeight

                if (pageHeightLeft + contentHeight <= rootViewAbsoluteHeightLeft) {

                    if (currentPageLeft == 1) {    // Content fits on the current page
                        Log.d("bbbbbbbbbbbb", "Achi $pageHeightLeft + $contentHeight = ${pageHeightLeft + contentHeight} <= $rootViewAbsoluteHeightLeft")
                        pageHeightLeft += contentHeight    // add each content height
                        setUpTheChildViews(content, type = achievement, leftOrRight = leftSide)
                    }
                    if (currentPageLeft == 2) {     // Add content to the new page
                        setUpTheChildViews(content, type = achievement, leftOrRight = leftSide)
                    }
                } else {
                    if (currentPageLeft == 1) {     // ပထမဆုံးအကြိမ် first pageမှာမဆန့်တာဆိုရင် pageNum အရင်ပြောင်း
                        currentPageLeft = 2

                        if (index == 1)
                            sendOtherLayoutsToAnotherPage(type = education)
                    }
                    setUpTheChildViews(content, type = achievement, leftOrRight = leftSide)
                }
            }
            sendOtherLayoutsToAnotherPage(type = achievement)
        }

        // Skill (Left)
        if (mCvVO?.skills?.isNotEmpty() == true) {
            skillContentViewList.forEachIndexed { index, content ->

                val widthMeasureSpec = MeasureSpec.makeMeasureSpec(content.width, MeasureSpec.EXACTLY)
                val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                content.measure(widthMeasureSpec, heightMeasureSpec)
                content.layout(0, 0, content.measuredWidth, content.measuredHeight)

                val contentHeight = content.measuredHeight

                if (pageHeightLeft + contentHeight <= rootViewAbsoluteHeightLeft) {

                    if (currentPageLeft == 1) {    // Content fits on the current page
                        Log.d("bbbbbbbbbbbb", "Skill $pageHeightLeft + $contentHeight = ${pageHeightLeft + contentHeight} <= $rootViewAbsoluteHeightLeft")
                        pageHeightLeft += contentHeight    // add each content height
                        setUpTheChildViews(content, type = skill, leftOrRight = leftSide)
                    }
                    if (currentPageLeft == 2) {     // Add content to the new page
                        setUpTheChildViews(content, type = skill, leftOrRight = leftSide)
                    }
                } else {
                    if (currentPageLeft == 1) {     // ပထမဆုံးအကြိမ် first pageမှာမဆန့်တာဆိုရင် pageNum အရင်ပြောင်း
                        currentPageLeft = 2

                        if (index == 1)
                            sendOtherLayoutsToAnotherPage(type = achievement)
                    }
                    setUpTheChildViews(content, type = skill, leftOrRight = leftSide)
                }
            }
            sendOtherLayoutsToAnotherPage(type = skill)
        }


        Log.d("aaaaaaaaaaaaaaa", "")
        Log.d("bbbbbbbbbbbb", "")
    }

    private fun setUpTheChildViews(childLayout: View, type: String, leftOrRight: String) {
        val oldParent = childLayout.parent as? ViewGroup
        oldParent?.removeView(childLayout) // Remove the childLayout from the current parent

        if (leftOrRight == mid) {
            when (type) {
                resumeFreeOneTopOne -> binding.firstElement.addView(childLayout)
                resumeFreeOneTopTwo -> binding.containerObjective.addView(childLayout)
            }
        }

        // Right
        if (leftOrRight == rightSide) {
            // Right 1
            if (currentPageRight == 1) {

                if (type == workExperience) binding.containerWorkExp.addView(childLayout)
                if (type == projectDetail) binding.containerProject.addView(childLayout)
                if (type == signature) { binding.containerFirstPageRight.addView(childLayout) }
            }

            // Right 2
            if (currentPageRight == 2) {
                binding.secondRootViewFreeOne.visibility = View.VISIBLE

                if (type == workExperience) { binding.containerSecondPageRightSide.addView(childLayout) }  // isSentToSecondPageRight က ပထမဆုံးဟာတွက်မလိုဘူး အောက်ကကောင်တွေအတွက်ပဲလိုတယ်

                if (isSentToSecondPageRight) {  // အပေါ်ဆုံး view မှာထဲက မဆန့်လို့ nextpage ပို့ပြီးသားဆိုရင် container ထဲ တန်းထည့်လိုက်ရုံပဲ
                    if (type == projectDetail) { binding.containerProject.addView(childLayout) }
                    if (type == signature) { binding.containerSecondPageRightSide.addView(childLayout) }

                } else {  // အခုမှပို့ရမှာဆိုရင် root ထဲတန်းထည့်လိုက်ရုံပဲ / first element ဆိုရင် header ကိုပါ ပို့ဖို့အတွက်က checkContent ထဲမှာစစ်ပြီးသား
                    if (type == projectDetail)  binding.containerSecondPageRightSide.addView(childLayout)
                    if (type == signature)  binding.containerSecondPageRightSide.addView(childLayout)
                }
            }
        }

        // Left
        if (leftOrRight == leftSide) {
            // Left 1
            if (currentPageLeft == 1) {

                if (type == education) binding.containerEducation.addView(childLayout)
                if (type == achievement) binding.containerAchievement.addView(childLayout)
                if (type == skill) binding.containerSkill.addView(childLayout)
//                if (type == reference) binding.containerReference.addView(childLayout)
            }
            // Left 2
            if (currentPageLeft == 2) {
                binding.secondRootViewFreeOne.visibility = View.VISIBLE

                if (type == education) { binding.containerSecondPageLeftSide.addView(childLayout) }  // isSentToSecondPageRight ကအောက်ကကောင်တွေအတွက်ပဲလိုတယ်
//
                if (isSentToSecondPageLeft) {  // အပေါ်ဆုံး view မှာထဲက မဆန့်လို့ nextpage ပို့ပြီးသားဆိုရင် container ထဲ တန်းထည့်လိုက်ရုံပဲ
                    if (type == achievement) { binding.containerAchievement.addView(childLayout) }
                    if (type == skill) binding.containerSkill.addView(childLayout)
//                    if (type == reference) binding.containerReference.addView(childLayout)
//
                } else {  // အခုမှပို့ရမှာဆိုရင် root ထဲတန်းထည့်လိုက်ရုံပဲ / first element ဆိုရင် header ကိုပါ ပို့ဖို့အတွက်က checkContent ထဲမှာစစ်ပြီးသား
                    if (type == achievement) { binding.containerSecondPageLeftSide.addView(childLayout) }
                    if (type == skill) { binding.containerSecondPageLeftSide.addView(childLayout) }
//                    if (type == reference) { containerSecondPageLeftSide.addView(childLayout) }
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
                        isSentToSecondPageRight = true

                        val oldParentProjectDetail = binding.containerProject.parent as? ViewGroup
                        oldParentProjectDetail?.removeView(binding.containerProject)
                        binding.containerSecondPageRightSide.addView(binding.containerProject)

                        val oldParentSignature = binding.ivSignature.parent as? ViewGroup
                        oldParentSignature?.removeView(binding.ivSignature)
                        binding.containerSecondPageRightSide.addView(binding.ivSignature)
                    }
                }
            }
            projectDetail -> {
                if (currentPageRight == 2) {

                    // move Signature to second page
                    if (!isSentToSecondPageRight) {
                        isSentToSecondPageRight = true

                        val oldParentSignature = binding.ivSignature.parent as? ViewGroup
                        oldParentSignature?.removeView(binding.ivSignature)
                        binding.containerSecondPageRightSide.addView(binding.ivSignature)
                    }
                } else {
                    if (currentPageRight == 1 || currentPageLeft == 1) {
                        binding.containerFirstPageBothSides.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                        binding.containerFirstPageBothSides.requestLayout()
                    }
                }
            }
            education -> {
            if (currentPageLeft == 2) {

                // move Skill, Achievement and Reference to second page
                if (!isSentToSecondPageLeft) {
                    isSentToSecondPageLeft = true

                    val oldParentAchievement = binding.containerAchievement.parent as? ViewGroup
                    oldParentAchievement?.removeView(binding.containerAchievement)
                    binding.containerSecondPageLeftSide.addView(binding.containerAchievement)

                    val oldParentSkills = binding.containerSkill.parent as? ViewGroup
                    oldParentSkills?.removeView(binding.containerSkill)
                    binding.containerSecondPageLeftSide.addView(binding.containerSkill)

                    val oldParentReference = binding.containerReference.parent as? ViewGroup
                    oldParentReference?.removeView(binding.containerReference)
                    binding.containerSecondPageLeftSide.addView(binding.containerReference)
                }
            }
        }

            achievement -> {
                if (currentPageLeft == 2) {

                    // move Achievement and Reference to second page
                    if (!isSentToSecondPageLeft) {
                        isSentToSecondPageLeft = true

                        val oldParentSkills = binding.containerSkill.parent as? ViewGroup
                        oldParentSkills?.removeView(binding.containerSkill)
                        binding.containerSecondPageLeftSide.addView(binding.containerSkill)

                        val oldParentReference = binding.containerReference.parent as? ViewGroup
                        oldParentReference?.removeView(binding.containerReference)
                        binding.containerSecondPageLeftSide.addView(binding.containerReference)
                    }
                }
            }

            skill -> {
                if (currentPageRight == 2) {

                    // move Reference to second page
                    if (!isSentToSecondPageLeft) {
                        isSentToSecondPageLeft = true

                        val oldParentReference = binding.containerReference.parent as? ViewGroup
                        oldParentReference?.removeView(binding.containerReference)
                        binding.containerSecondPageLeftSide.addView(binding.containerReference)
                    }
                }
            }

        }
        if (currentPageRight == 2 || currentPageLeft == 2) {
            val oldParentBottomLine = binding.viewFirstBottomost.parent as? ViewGroup
            oldParentBottomLine?.removeView(binding.viewFirstBottomost)
            binding.secondRootViewFreeOne.addView(binding.viewFirstBottomost)
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
        textView.letterSpacing = 0.02f  // letterSpacing
        var marginTopBottom = 0
        if ( marginSize != 0) marginTopBottom = resources.getDimensionPixelSize(marginSize)

        val layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        when (marginType) {
            topMargin -> layoutParams.setMargins(0, marginTopBottom, 0, 0)
            bottomMargin -> setPadding(0, 0, 0, marginTopBottom)
            topBottomMargin -> layoutParams.setMargins(0, marginTopBottom, 0, marginTopBottom)
            endMargin -> layoutParams.setMargins(0, 0, marginTopBottom, 0)
        }

        textView.layoutParams = layoutParams

        // fontFamily
        val typeface = ResourcesCompat.getFont(context, fontFamily)

        // fontStyle
        if (bold != null) {
            val textStyle = Typeface.BOLD
            textView.setTypeface(typeface, textStyle)
        } else textView.typeface = typeface

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

}