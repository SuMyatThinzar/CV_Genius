package com.smtz.cvgenius.presentation.home

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.smtz.cvgenius.R
import com.smtz.cvgenius.BuildConfig
import com.smtz.cvgenius.common.CvSingleton
import com.smtz.cvgenius.common.checkInternetConnection
import com.smtz.cvgenius.common.setUpAppBarTitleManually
import com.smtz.cvgenius.common.setUpLayoutParams
import com.smtz.cvgenius.core.BaseActivity
import com.smtz.cvgenius.data.repository.CvModelImpl
import com.smtz.cvgenius.databinding.ActivityCreateCvBinding
import com.smtz.cvgenius.databinding.ActivityHomeBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.repository.CvModel
import com.smtz.cvgenius.presentation.createcv.CreateCvActivity
import com.smtz.cvgenius.presentation.preview.PreviewActivity
import com.smtz.cvgenius.presentation.template.SampleTemplateActivity
import com.smtz.cvgenius.utils.BACK_PRESSED
import com.smtz.cvgenius.utils.CREATE_CV_ACTIVITY
import com.smtz.cvgenius.utils.INTERSTITIAL_TAG
import com.smtz.cvgenius.utils.PREVIEW_ACTIVITY

class HomeActivity : BaseActivity<ActivityHomeBinding>(), CvDelegate {


    override val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private lateinit var mBannerAdView: AdView

    private val mCvListAdapter = CvListAdapter(delegate = this)

    // Declare a loading flag
    var isInterstitialAdLoading = false
    private var isInternetAvailable = false
    private val mCvModel: CvModel = CvModelImpl
    private var mCvVoList: List<CvVO> = listOf()

    // interstitial ad
    private var mInterstitialAd: InterstitialAd? = null

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

        setUpMarginsAndPaddingAccordingToAndroidVersions()
        setUpAdListener()
        setUpToolBar()
        setUpListener()
        setUpAdapter()
        showEmptyView()
        requestData()

    }

    override fun onResume() {
        super.onResume()

        // Check internet connectivity and AdLoaded or not
        isInternetAvailable = checkInternetConnection(applicationContext)
        Log.d(INTERSTITIAL_TAG, "internet connection $isInternetAvailable")
        if (isInternetAvailable && mInterstitialAd == null && !isInterstitialAdLoading) {
            loadInterstitialAd()
        }

        CvSingleton.instance.cvVO?.let {
            CvSingleton.instance.cleanup()
        }
    }

    // 1. load ad when activity starts and show on button click.
    private fun loadInterstitialAd() {
        isInterstitialAdLoading = true
        Log.d(INTERSTITIAL_TAG, "ad is loading $isInterstitialAdLoading")

        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            applicationContext,
            BuildConfig.INTERSTITIAL_AD_ID,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(INTERSTITIAL_TAG, "AdFailedToLoad: ${adError.message}")
                    mInterstitialAd = null
                    isInterstitialAdLoading = false
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(INTERSTITIAL_TAG, "AdLoaded: ")
                    mInterstitialAd = interstitialAd
                    isInterstitialAdLoading = false
                }
            })
    }

    private fun requestData() {
        mCvModel.getAllCv()?.observe(this) {
            mCvVoList = it

            if (it.isNotEmpty())  hideEmptyView()
            else                  showEmptyView()

            mCvListAdapter.setNewData(it)
        }
    }

    private fun setUpAdapter() {

        binding.rvCreatedCV.apply {
            adapter = mCvListAdapter
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun hideEmptyView() {
        binding.hideEmptyView.visibility = View.VISIBLE
        binding.llEmptyView.visibility = View.GONE
    }

    private fun showEmptyView() {
        binding.hideEmptyView.visibility = View.GONE
        binding.llEmptyView.visibility = View.VISIBLE
    }

    private fun setUpListener() {
        binding.btnMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.fabCreateNewCV.setOnClickListener {
            startActivity(SampleTemplateActivity.newIntent(this, SampleTemplateActivity.CREATE_NEW))
        }
        binding.btnShare.setOnClickListener {
            shareApp()
        }
        binding.btnTemplates.setOnClickListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            startActivity(SampleTemplateActivity.newIntent(this, SampleTemplateActivity.CREATE_NEW))
        }
    }

    private fun shareApp() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this app!")
        shareIntent.putExtra(Intent.EXTRA_TEXT,
            "Hey! I found this amazing app that can create professional Resume easily. Download it on Google Play: [App Store Link]."
        )

        val chooser = Intent.createChooser(shareIntent, "Share app via ...")
        startActivity(chooser)
    }

    private fun setUpToolBar() {
        setSupportActionBar(binding.toolBar)

        setUpAppBarTitleManually(binding.appBarLayout, binding.tvHeading, binding.collapsingToolbarLayout)

        val parentView = binding.btnMenu.parent as ViewGroup
        parentView.removeView(binding.btnMenu)

        val layoutParamsExpanded = binding.btnMenu.layoutParams as CollapsingToolbarLayout.LayoutParams
        layoutParamsExpanded.topMargin = 46
        layoutParamsExpanded.bottomMargin = 32
        layoutParamsExpanded.collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
        layoutParamsExpanded.gravity = Gravity.CENTER_VERTICAL
        binding.btnMenu.layoutParams = layoutParamsExpanded

        binding.collapsingToolbarLayout.addView(binding.btnMenu)
//        binding.collapsingToolbarLayout.collapsedTitleGravity = Gravity.CENTER_HORIZONTAL

    }

    private fun showNextActivity(activityToStart: String) {

        when (activityToStart) {
            PREVIEW_ACTIVITY -> startActivity(PreviewActivity.newIntent(this))
            CREATE_CV_ACTIVITY -> startActivity(CreateCvActivity.newIntent(this, templateId = null)) // will be replaced in requestData()
            BACK_PRESSED -> super.onBackPressed()
        }
    }

    override fun onBackPressed() {
        when {
            binding.drawerLayout.isDrawerOpen(GravityCompat.START) ->
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            else -> {
                showInterstitialAd(BACK_PRESSED)
            }
        }
    }

    override fun onTapCv(cvVO: CvVO) {
        CvSingleton.instance.cvVO = cvVO
        showInterstitialAd(CREATE_CV_ACTIVITY)
//        startActivity(CreateCvActivity.newIntent(this, templateId = null)) // will be replaced in requestData()
    }

    override fun onTapViewCv(cvVO: CvVO) {
        CvSingleton.instance.cvVO = cvVO
        showInterstitialAd(PREVIEW_ACTIVITY)
//        startActivity(PreviewActivity.newIntent(this))  //showInterstitialAd is the Asynchronous function and starting Activity will go first
    }

    override fun onTapDeleteCv(cvId: Long) {
        mCvModel.deleteCv(cvId)
        Snackbar.make(window.decorView, "Successfully deleted", Snackbar.LENGTH_SHORT).show()
    }

    override fun onTapDuplicate(cvVO: CvVO) {
        val copiedCv = cvVO.copy(cvId = System.currentTimeMillis())
        mCvModel.insertCV(copiedCv)
        Snackbar.make(window.decorView, "Resume Duplicated ", Snackbar.LENGTH_SHORT).show()
    }

//    override fun onTapShare(viewPodId: Int) {
//        val viewPodLayout = findViewById<BaseViewPod>(mViewPodId)
//        val pdfDocu = convertToPdf(this,)
//        shareDocument(this, )
//        Snackbar.make(window.decorView, "Successfully Shared", Snackbar.LENGTH_SHORT).show()
//    }

    private fun setUpMarginsAndPaddingAccordingToAndroidVersions() {

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            val marginMediumN = resources.getDimensionPixelSize(R.dimen.margin_medium_n)
            val marginMedium2 = resources.getDimensionPixelSize(R.dimen.margin_medium_2)
            val marginCardMedium3 = resources.getDimensionPixelSize(R.dimen.margin_card_medium_3)
            val marginXLarge = resources.getDimensionPixelSize(R.dimen.margin_xxlarge)

            binding.llRecyclerView.layoutParams = setUpLayoutParams(binding.llRecyclerView, left = marginMediumN, top = marginMedium2, right = marginMediumN, bottom = marginCardMedium3)
            binding.fabImage.layoutParams = setUpLayoutParams(binding.fabImage, left = 0, top = marginMedium2, right = 0, bottom = 0)
            binding.rvCreatedCV.setPadding(0,0,0, marginXLarge)
        }
    }

    private fun showInterstitialAd(NEXT_ACTIVITY: String) {
        // check if ad is loaded or not loaded.
        if (mInterstitialAd != null) {
            //ad is loaded.
            showNextActivity(NEXT_ACTIVITY)     // *****
            mInterstitialAd!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdClicked() {
                    super.onAdClicked()
                    // called when ad is clicked.
                    Log.d(INTERSTITIAL_TAG, "AdClicked: ")
                }

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    // called when ad is dismissed/closed.
                    Log.d(INTERSTITIAL_TAG, "AdDismissedFullScreenContent: ")
                    mInterstitialAd = null
//                    showNextActivity(NEXT_ACTIVITY)    // *****
                    if (NEXT_ACTIVITY != BACK_PRESSED)  loadInterstitialAd()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    super.onAdFailedToShowFullScreenContent(adError)
                    // called when ad is failed to show.
                    Log.d(INTERSTITIAL_TAG, "AdFailedToShowFullScreenContent: ${adError.message}")
                    mInterstitialAd = null
//                    showNextActivity(NEXT_ACTIVITY)     // *****
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    // called when ad impression is recorded.
                    Log.d(INTERSTITIAL_TAG, "onAdImpression: ")
                }

                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()
                    // called when ad is shown.
                    Log.d(INTERSTITIAL_TAG, "onAdShowedFullScreenContent: ")
                }
            }

            //show ad.
            mInterstitialAd!!.show(this as Activity)
        } else {
            showNextActivity(NEXT_ACTIVITY)     // *****
            Log.d(INTERSTITIAL_TAG, "Ad wasn't ready.")
        }
    }

    private fun setUpAdListener() {
        mBannerAdView.adListener = object : AdListener() {
            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
//                Toast.makeText(this@HomeActivity, "clicked on an ad", Toast.LENGTH_SHORT).show()
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
//                Toast.makeText(this@HomeActivity, "clicked on an ad", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                // Code to be executed when an ad request fails.
//                Toast.makeText(this@HomeActivity, "ad request fails.", Toast.LENGTH_SHORT).show()
            }

            override fun onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
//                Toast.makeText(this@HomeActivity, "impression is recorded", Toast.LENGTH_SHORT).show()
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
//                Toast.makeText(this@HomeActivity, "ad finishes loading", Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
//                Toast.makeText(this@HomeActivity, "ad visible", Toast.LENGTH_SHORT).show()
            }
        }
    }

}