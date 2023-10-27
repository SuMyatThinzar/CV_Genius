package com.smtz.cvgenius.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.smtz.cvgenius.R
import com.smtz.cvgenius.common.CvSingleton
import com.smtz.cvgenius.core.BaseActivity
import com.smtz.cvgenius.databinding.ActivityChangeTemplateBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.presentation.preview.templateViewPods.BaseViewPod

class ChangeTemplateActivity : BaseActivity<ActivityChangeTemplateBinding>() {

    private var mCvVO: CvVO? = null
    private lateinit var mTemplateViewPod: BaseViewPod
    private lateinit var mBannerAdView: AdView

    override val binding: ActivityChangeTemplateBinding by lazy {
        ActivityChangeTemplateBinding.inflate(layoutInflater)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, ChangeTemplateActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCvVO = CvSingleton.instance.cvVO!!

        // initializing AdMob
        MobileAds.initialize(this) {}

        // banner Ad
        mBannerAdView = binding.bannerAdView
        val adRequest = AdRequest.Builder().build()
        mBannerAdView.loadAd(adRequest)

        setUpTemplate()
    }

    private fun setUpTemplate() {
        when (mCvVO?.templateId) {
            0 -> {
                setUpViewPod(R.id.vpFreeOne)
                binding.flFreeOne.visibility = View.VISIBLE
            }
            2 -> {
                setUpViewPod(R.id.vpSecondTwo)
                binding.flSecondTwo.visibility = View.VISIBLE
            }
            3 -> {
                setUpViewPod(R.id.vpSecondTwo)
                binding.flSecondTwo.visibility = View.VISIBLE
            }
            4 -> {
                setUpViewPod(R.id.vpSecondTwo)
                binding.flSecondTwo.visibility = View.VISIBLE
            }
            5 -> {
                setUpViewPod(R.id.vpSecondTwo)
                binding.flSecondTwo.visibility = View.VISIBLE
            }
            6 -> {
                setUpViewPod(R.id.vpSecondTwo)
                binding.flSecondTwo.visibility = View.VISIBLE
            }
        }
    }

    private fun setUpViewPod(resumeId: Int) {
        mTemplateViewPod = binding.root.findViewById(resumeId)
    }

    override fun onPause() {
        super.onPause()

        // 2159, 2748 (1374 * 2 page)
//        Log.d("afasfasfda", "${binding.root.height} ${findViewById<ResumeFreeOneViewPod>(R.id.vp).height}")
    }
}