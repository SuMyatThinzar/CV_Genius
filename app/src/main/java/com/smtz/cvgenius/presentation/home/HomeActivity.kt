package com.smtz.cvgenius.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.smtz.cvgenius.data.repository.CvModelImpl
import com.smtz.cvgenius.databinding.ActivityHomeBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.repository.CvModel
import com.smtz.cvgenius.presentation.createcv.CreateCvActivity
import com.smtz.cvgenius.presentation.template.SampleTemplateActivity

class HomeActivity : AppCompatActivity(), CvDelegate {

    private lateinit var binding: ActivityHomeBinding

    private val mCvListAdapter = CvListAdapter(delegate = this)

    private val mCvModel: CvModel = CvModelImpl
    private var mCvVoList: List<CvVO> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()
        setUpListener()
        setUpAdapter()
        showEmptyView()
        requestData()
    }

    private fun requestData() {
        mCvModel.getAllCv()?.observe(this) {
            mCvVoList = it

            if (it.isNotEmpty()) {
                hideEmptyView()
            } else {
                showEmptyView()
            }
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
        if (mCvVoList.isNotEmpty()) {
            binding.llEmptyView.visibility = View.INVISIBLE
        }
    }

    private fun showEmptyView() {
        binding.llEmptyView.visibility = View.VISIBLE
    }

    private fun setUpListener() {
        binding.btnMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.fabCreateNewCV.setOnClickListener {
            startActivity(Intent(this, SampleTemplateActivity::class.java))
        }
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.nav_camera -> {
//                Toast.makeText(this, "Camera", Toast.LENGTH_SHORT).show()
//                //Logic
//                drawerLayout.closeDrawer(GravityCompat.START)
//            }
//            R.id.nav_album -> {
//                Toast.makeText(this, "Album", Toast.LENGTH_SHORT).show()
//            }
//            R.id.nav_slideshow -> {
//                Toast.makeText(this, "Slideshow", Toast.LENGTH_SHORT).show()
//            }
//            R.id.nav_tool -> {
//                Toast.makeText(this, "Tools", Toast.LENGTH_SHORT).show()
//            }
//            R.id.nav_constraint -> {
//                val intent = Intent(this, ConstraintActivity::class.java)
//                startActivity(intent)
//            }
//        }
//        return true
//    }

    private fun setUpToolBar() {
        setSupportActionBar(binding.toolBar)

        val parentView = binding.btnMenu.parent as ViewGroup
        parentView.removeView(binding.btnMenu)

        val layoutParamsExpanded =
            binding.btnMenu.layoutParams as CollapsingToolbarLayout.LayoutParams
        layoutParamsExpanded.topMargin = 46
        layoutParamsExpanded.bottomMargin = 32
        layoutParamsExpanded.collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
        layoutParamsExpanded.gravity = Gravity.CENTER_VERTICAL
        binding.btnMenu.layoutParams = layoutParamsExpanded

        binding.collapsingToolbarLayout.addView(binding.btnMenu)
        binding.collapsingToolbarLayout.collapsedTitleGravity = Gravity.CENTER_HORIZONTAL

    }

    override fun onBackPressed() {
        when {
            binding.drawerLayout.isDrawerOpen(GravityCompat.START) ->
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            else ->
                super.onBackPressed()
        }
    }

    override fun onTapCv(cvId: Long) {
        startActivity(CreateCvActivity.newIntent(this, cvId, templateId = null))
    }

}