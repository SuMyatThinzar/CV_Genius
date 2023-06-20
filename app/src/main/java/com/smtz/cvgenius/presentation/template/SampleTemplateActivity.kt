package com.smtz.cvgenius.presentation.template

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.smtz.cvgenius.common.delegates.SampleTemplateDelegate
import com.smtz.cvgenius.databinding.ActivitySampleTemplateBinding
import com.smtz.cvgenius.presentation.createcv.CreateCvActivity
import com.smtz.cvgenius.utils.tabList


class SampleTemplateActivity : AppCompatActivity(), SampleTemplateDelegate {

    private lateinit var binding : ActivitySampleTemplateBinding
    private val mSampleTemplateAdapter = SampleTemplateAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleTemplateBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        layoutParamsExpanded.topMargin = 32
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
        startActivity(CreateCvActivity.newIntent(this, templateId = templateId))
    }
}