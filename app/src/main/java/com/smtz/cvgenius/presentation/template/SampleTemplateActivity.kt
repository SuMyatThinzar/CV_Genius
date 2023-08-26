package com.smtz.cvgenius.presentation.template

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.browseractions.BrowserActionsIntent.EXTRA_TYPE
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.snackbar.Snackbar
import com.smtz.cvgenius.common.CvSingleton
import com.smtz.cvgenius.common.delegates.SampleTemplateDelegate
import com.smtz.cvgenius.data.repository.CvModelImpl
import com.smtz.cvgenius.databinding.ActivitySampleTemplateBinding
import com.smtz.cvgenius.domain.repository.CvModel
import com.smtz.cvgenius.presentation.createcv.CreateCvActivity
import com.smtz.cvgenius.presentation.preview.PreviewActivity
import com.smtz.cvgenius.utils.PREVIEW_ACTIVITY
import com.smtz.cvgenius.utils.tabList


class SampleTemplateActivity : AppCompatActivity(), SampleTemplateDelegate {

    private lateinit var binding : ActivitySampleTemplateBinding
    private lateinit var type : String
    private val mSampleTemplateAdapter = SampleTemplateAdapter(this)
    private lateinit var mBannerAdView: AdView
    private var cvModel: CvModel = CvModelImpl

    companion object {

        private const val EXTRA_TYPE = "EXTRA TYPE"
        const val CREATE_NEW = "CREATE NEW"  // Home ကနေ new button နဲ့လာရင်
        const val CHANGE_ID = "CHANGE ID"    // change template ဆိုရင်

        fun newIntent(context: Context, type: String): Intent {
            val intent = Intent(context, SampleTemplateActivity::class.java)
            intent.putExtra(EXTRA_TYPE, type)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleTemplateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        type = intent.getStringExtra(EXTRA_TYPE).toString()

        // initializing AdMob
        MobileAds.initialize(this) {}

        // banner Ad
        mBannerAdView = binding.bannerAdView
        val adRequest = AdRequest.Builder().build()
        mBannerAdView.loadAd(adRequest)

        setUpToolBar()
        setUpTabLayout()
        setUpListeners()
        setUpAdapters()
    }

    private fun setUpToolBar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = ""

        val parentView = binding.btnBack.parent as ViewGroup
        parentView.removeView(binding.btnBack)

        val layoutParamsExpanded = binding.btnBack.layoutParams as CollapsingToolbarLayout.LayoutParams
        layoutParamsExpanded.topMargin = 46
        layoutParamsExpanded.bottomMargin = 32
        layoutParamsExpanded.collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
        layoutParamsExpanded.gravity = Gravity.CENTER_VERTICAL
        binding.btnBack.layoutParams = layoutParamsExpanded

        binding.collapsingToolbarLayout.addView(binding.btnBack)
        binding.collapsingToolbarLayout.collapsedTitleGravity = Gravity.CENTER_HORIZONTAL

    }

    private fun setUpTabLayout() {
        tabList.forEach { tabName->
            binding.tabLayout.newTab().apply {
                text = tabName
                binding.tabLayout.addTab(this)
            }
        }
    }

    private fun setUpAdapters() {
        binding.rvCreatedCV.apply {
            adapter = mSampleTemplateAdapter
            layoutManager = GridLayoutManager(applicationContext, 2)
        }
    }

    private fun setUpListeners() {
//        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                tabList.getOrNull(tab?.position ?: 0)?.let {
//                    getMoviesByGenres(it)
//                }
////                Snackbar.make(window.decorView,tab?.text?:"", Snackbar.LENGTH_SHORT).show()
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//        })


        binding.btnBack.setOnClickListener{
            onBackPressed()
        }
    }

    override fun onTapSampleTemplate(templateId: Int) {
        if (type == CREATE_NEW) {
            startActivity(CreateCvActivity.newIntent(this, templateId = templateId))
        }

        if (type == CHANGE_ID) {
            CvSingleton.instance.cvVO?.let {

                if (it.templateId == templateId) super.onBackPressed()
                else {
                    it.templateId = templateId
                    cvModel.insertCV(it)

                    val intent = Intent(this, PreviewActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}
