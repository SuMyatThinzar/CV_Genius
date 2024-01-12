package com.smtz.cvgenius.presentation.createcv

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.smtz.cvgenius.BuildConfig
import com.smtz.cvgenius.R
import com.smtz.cvgenius.common.components.CvSingleton
import com.smtz.cvgenius.common.utils.checkInternetConnection
import com.smtz.cvgenius.common.utils.setUpAppBarTitleManually
import com.smtz.cvgenius.common.utils.setUpLayoutParams
import com.smtz.cvgenius.core.BaseActivity
import com.smtz.cvgenius.data.repository.CvModelImpl
import com.smtz.cvgenius.databinding.ActivityCreateCvBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.repository.CvModel
import com.smtz.cvgenius.presentation.details.DetailButtonDelegate
import com.smtz.cvgenius.presentation.details.achievements.AchievementActivity
import com.smtz.cvgenius.presentation.details.educationDetails.EducationDetailActivity
import com.smtz.cvgenius.presentation.details.objectives.ObjectiveActivity
import com.smtz.cvgenius.presentation.details.personalDetails.PersonalDetailActivity
import com.smtz.cvgenius.presentation.details.projectDetails.ProjectDetailActivity
import com.smtz.cvgenius.presentation.details.signature.SignatureActivity
import com.smtz.cvgenius.presentation.details.skills.SkillActivity
import com.smtz.cvgenius.presentation.details.workExperiences.WorkExperienceActivity
import com.smtz.cvgenius.presentation.details.reference.ReferenceActivity
import com.smtz.cvgenius.presentation.home.HomeActivity
import com.smtz.cvgenius.presentation.preview.PreviewActivity
import com.smtz.cvgenius.utils.BACK_PRESSED
import com.smtz.cvgenius.utils.INTERSTITIAL_TAG
import com.smtz.cvgenius.utils.PREVIEW_ACTIVITY
import com.theartofdev.edmodo.cropper.CropImage

class CreateCvActivity : BaseActivity<ActivityCreateCvBinding>(), DetailButtonDelegate {

    override val binding: ActivityCreateCvBinding by lazy {
        ActivityCreateCvBinding.inflate(layoutInflater)
    }

    private var mAddDetailButtonAdapter: AddDetailButtonAdapter = AddDetailButtonAdapter(this)
    private lateinit var mBannerAdView: AdView

    // Declare a loading flag
    private val REQUEST_CODE_PERMISSION_STORAGE = 100
    private var isInterstitialAdLoading = false
    private var isInternetAvailable = false
    private var mCvId = System.currentTimeMillis()
    private var mCvModel: CvModel = CvModelImpl
    private var mCvVo: CvVO? = null
    private var nullCv: CvVO? = null
    private var selectedImage: ByteArray? = null
    private var mTemplateId: Int? = null
    // interstitial ad
    private var mInterstitialAd: InterstitialAd? = null

    companion object {

        private const val EXTRA_TEMPLATE_ID = "EXTRA TEMPLATE ID"
        fun newIntent(context: Context, templateId: Int?): Intent {
            val intent = Intent(context, CreateCvActivity::class.java)
//            intent.putExtra(EXTRA_ID, cvId)
            intent.putExtra(EXTRA_TEMPLATE_ID, templateId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // initializing AdMob
        MobileAds.initialize(this) {}

        // banner Ad
        mBannerAdView = binding.bannerAdView
        val adRequest = AdRequest.Builder().build()
        mBannerAdView.loadAd(adRequest)

        // load interstitial ad.
        isInternetAvailable = checkInternetConnection(applicationContext)
        Log.d(INTERSTITIAL_TAG, "internet connection $isInternetAvailable")
        if (isInternetAvailable && mInterstitialAd == null && !isInterstitialAdLoading) {
            loadInterstitialAd()
        }

        mTemplateId = intent.getIntExtra(EXTRA_TEMPLATE_ID, 10000)

        requestStoragePermission()

        setUpMarginsAndPaddingAccordingToAndroidVersions()
        setUpToolBar()
        setUpAdapter()
        setUpListener()
        requestData()
    }

    override fun onResume() {
        super.onResume()
        mCvVo = CvSingleton.instance.cvVO

        // Check internet connectivity and AdLoaded or not
        isInternetAvailable = checkInternetConnection(applicationContext)
        Log.d(INTERSTITIAL_TAG, "internet connection $isInternetAvailable")
        if (isInternetAvailable && mInterstitialAd == null && !isInterstitialAdLoading) {
            loadInterstitialAd()
        }
    }

    private fun requestData() {

        setUpEmptyCv(mTemplateId)

        mCvVo = CvSingleton.instance.cvVO

        if (mCvVo == null) {
            mCvVo = CvVO(
                cvId = mCvId,
                templateId = mTemplateId!!,
                profileImage = null,
                personalDetails = null,
                educationDetails = mutableListOf(),
                workExperiences = mutableListOf(),
                skills = mutableListOf(),
                achievements = mutableListOf(),
                objective = null,
                signature = null,
                references = mutableListOf(),
                projectDetails = mutableListOf()
            )
            Log.d("assasdfasdf", "new null cv created")

        }
        mCvVo?.let{                         // if the object is exist, observing returns it
            setUpEmptyCv(it.templateId)
            mCvId = it.cvId
            setUpData()
            Log.d("assasdfasdf", "replace created cv")
        }

        // save CvVO as a singleton and access it in Details
        CvSingleton.instance.cvVO = mCvVo
    }

    private fun setUpAdapter() {
        binding.rvDetailButton.apply {
            adapter = mAddDetailButtonAdapter
            layoutManager = GridLayoutManager(applicationContext, 3)
        }
    }

    private fun setUpData() {
        mCvVo!!.profileImage?.let {
            selectedImage = it

            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            binding.ivSelectedImage.setImageBitmap(bitmap)
        }
    }

    private fun setUpListener() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.btnUploadPhoto.setOnClickListener {
//            val intent = Intent(Intent.ACTION_GET_CONTENT)
//            intent.type = "image/*"
//            intent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png")) // Add more supported image formats if needed
//            startActivityForResult(intent, IMAGE_REQUEST_CODE)

//            val builder = AlertDialog.Builder(this)
//                .setTitle("Pick Image From")
//                .setPositiveButton("Gallery") { dialog, _->
                    if (checkStoragePermission()) {
                        CropImage.activity().start(this)
                    } else {
                        requestStoragePermission()
                    }
//                }

//            builder.show()
        }
        binding.btnRemovePhoto.setOnClickListener {
            binding.ivSelectedImage.setImageResource(R.drawable.ic_select_photo)
            selectedImage = null
            mCvVo!!.profileImage = null
        }
        binding.btnPreviewCv.setOnClickListener {
            if (mCvVo?.personalDetails != null) {
                // save again for new changes
                mCvModel.insertCV(mCvVo!!)
                showInterstitialAd(PREVIEW_ACTIVITY)
            } else {
                showDialogOnBackPressedAndPreview("preview")
            }
        }
    }

    private fun setUpToolBar() {
        setSupportActionBar(binding.toolBar)

        setUpAppBarTitleManually(binding.appBarLayout, binding.tvHeading, binding.collapsingToolbarLayout)

        val parentView = binding.btnBack.parent as ViewGroup
        parentView.removeView(binding.btnBack)

        val layoutParamsExpanded = binding.btnBack.layoutParams as CollapsingToolbarLayout.LayoutParams
        layoutParamsExpanded.topMargin = 46
        layoutParamsExpanded.bottomMargin = 32
        layoutParamsExpanded.collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
        layoutParamsExpanded.gravity = Gravity.CENTER_VERTICAL
        binding.btnBack.layoutParams = layoutParamsExpanded

        binding.collapsingToolbarLayout.addView(binding.btnBack)
//        binding.collapsingToolbarLayout.collapsedTitleGravity = Gravity.CENTER_HORIZONTAL  // CollapsingToolbarLayout ထဲက build in ပါပြီးသား title or toolBar ထဲက ပါပြီးသား title ကို set up လုပ်တာ


    }

    private fun showDialogOnBackPressedAndPreview(previewOrBackPressed: String) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_layout, null)
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)

        val dialog = dialogBuilder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // for rounded corner background , set background of the dialog's root view to transparent,

        dialog.show()

        val tvMessage = dialogView.findViewById<TextView>(R.id.dialog_message)
        val btnCancel = dialogView.findViewById<Button>(R.id.btn_cancel)
        val btnAbandon = dialogView.findViewById<Button>(R.id.btn_abandon)

        btnCancel.setOnClickListener {
            // Handle Cancel button click
            dialog.dismiss()
        }

        btnAbandon.setOnClickListener {
            // Handle Abandon button click
            dialog.dismiss()
            showInterstitialAd(BACK_PRESSED)
        }

        // change text and buttons for Dialog box
        if (previewOrBackPressed == "preview") {
//            parent.removeView(btnAbandon)
            tvMessage.text = "Please fill personal information for previewing CV"
            btnAbandon.visibility = View.GONE
            btnCancel.text = "OK"
        }
    }

    private fun setUpEmptyCv(templateId: Int?) {
        nullCv = CvVO(
            cvId = mCvId,
            templateId = templateId!!,
            profileImage = null,
            personalDetails = null,
            educationDetails = mutableListOf(),
            workExperiences = mutableListOf(),
            skills = mutableListOf(),
            achievements = mutableListOf(),
            objective = null,
            signature = null,
            references = mutableListOf(),
            projectDetails = mutableListOf()
        )
    }

    // 1. load ad when activity starts and show on button click.
    private fun loadInterstitialAd() {
        isInterstitialAdLoading = true
        Log.d("adsdfsdfsd", "ad is loading $isInterstitialAdLoading")

        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(applicationContext, BuildConfig.INTERSTITIAL_AD_ID, adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("adsdfsdfsd", "AdFailedToLoad: ${adError.message}")
                    mInterstitialAd = null
                    isInterstitialAdLoading = false
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d("adsdfsdfsd", "AdLoaded: ")
                    mInterstitialAd = interstitialAd
                    isInterstitialAdLoading = false
                }
            })
    }

    private fun showInterstitialAd(NEXT_ACTIVITY: String) {
        // check if ad is loaded or not loaded.
        if (mInterstitialAd != null) {
            showNextActivity(NEXT_ACTIVITY)    // *****
            //ad is loaded.
            mInterstitialAd!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdClicked() {
                    super.onAdClicked()
                    // called when ad is clicked.
                    Log.d("adsdfsdfsd", "AdClicked: ")
                }

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    // called when ad is dismissed/closed.
                    Log.d("adsdfsdfsd", "AdDismissedFullScreenContent: ")
                    mInterstitialAd = null
                    if (NEXT_ACTIVITY != BACK_PRESSED) loadInterstitialAd()
//                    showNextActivity(NEXT_ACTIVITY)    // *****
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    super.onAdFailedToShowFullScreenContent(adError)
                    // called when ad is failed to show.
                    Log.d("adsdfsdfsd", "AdFailedToShowFullScreenContent: ${adError.message}")
                    mInterstitialAd = null
                    if (NEXT_ACTIVITY != BACK_PRESSED)  loadInterstitialAd()
//                    showNextActivity(NEXT_ACTIVITY)     // *****
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    // called when ad impression is recorded.
                    Log.d("adsdfsdfsd", "onAdImpression: ")
                }

                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()
                    // called when ad is shown.
                    Log.d("adsdfsdfsd", "onAdShowedFullScreenContent: ")
                }
            }

            //show ad.
            mInterstitialAd!!.show(this as Activity)
        } else {
            showNextActivity(NEXT_ACTIVITY)     // *****
            Log.d("adsdfsdfsd", "Ad wasn't ready.")
        }
    }

    private fun showNextActivity(activityToStart: String) {

        when (activityToStart) {
            PREVIEW_ACTIVITY -> startActivity(PreviewActivity.newIntent(this))
            BACK_PRESSED -> {
                // start Activity A with FLAG_ACTIVITY_CLEAR_TOP and FLAG_ACTIVITY_SINGLE_TOP flags skip Activity B
                val intent = Intent(applicationContext, HomeActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                }
                startActivity(intent)
                // finish Activity C
                finish()
            }
        }
    }

    override fun onTapButton(id: Int) {
        when (id) {
            1 -> startActivity(PersonalDetailActivity.newIntent(this))
            2 -> startActivity(EducationDetailActivity.newIntent(this))
            3 -> startActivity(WorkExperienceActivity.newIntent(this))
            4 -> startActivity(SkillActivity.newIntent(this))
            5 -> startActivity(AchievementActivity.newIntent(this))
            6 -> startActivity(ObjectiveActivity.newIntent(this))
            7 -> startActivity(ProjectDetailActivity.newIntent(this))
            8 -> startActivity(Intent(applicationContext, ReferenceActivity::class.java))
            9 -> startActivity(SignatureActivity.newIntent(this))
        }
    }

    override fun onActivityResult(requestCode:Int,resultCode:Int , data:Intent? ) {
        super.onActivityResult(requestCode, resultCode, data)

        // Default Image Picker
//        if(requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data?.data != null) {
//            val selectedImageUri = data.data
//            binding.ivSelectedImage.setImageURI(selectedImageUri)

        // Crop Image
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            val result = CropImage.getActivityResult(data).uri
            binding.ivSelectedImage.setImageURI(result)

            // Change to ByteArray to save in Database
            val inputStream = contentResolver.openInputStream(result!!)
            val byteArray = inputStream?.readBytes()
            inputStream?.close()

            mCvVo!!.profileImage = byteArray
            selectedImage = byteArray
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {
//            mCvVo = data.getSerializableExtra(EXTRA_RESULT) as CvVO
//            Toast.makeText(this, mCvVo.toString(), Toast.LENGTH_SHORT).show()
//        }
//    }

    override fun onBackPressed() {

        if (mCvVo!!.personalDetails != null) {
            mCvModel.insertCV(mCvVo!!)  // save new changes
            showInterstitialAd(BACK_PRESSED)
            super.onBackPressed()

        } else {
            showDialogOnBackPressedAndPreview("")
        }
    }

    private fun checkStoragePermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestStoragePermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_PERMISSION_STORAGE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION_STORAGE) {
            if (grantResults.size >= 0) {
                val writeStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                if (writeStorageAccepted)
//                    CropImage.activity().start(this)
                else
                    Toast.makeText(applicationContext, "Please Enable Storage Permission", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUpMarginsAndPaddingAccordingToAndroidVersions() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            val marginMedium3 = resources.getDimensionPixelSize(R.dimen.margin_medium_3)
            val marginCardMedium3 = resources.getDimensionPixelSize(R.dimen.margin_card_medium_3)

            binding.rvDetailButton.layoutParams = setUpLayoutParams(binding.rvDetailButton, left = marginMedium3, top = 0, right = marginCardMedium3, bottom = 0)
//            binding.neumorphCardView.setPadding(marginLargeN, marginMedium3, marginMedium3, marginXXXLarge)
        }
    }
}

private const val IMAGE_REQUEST_CODE = 100