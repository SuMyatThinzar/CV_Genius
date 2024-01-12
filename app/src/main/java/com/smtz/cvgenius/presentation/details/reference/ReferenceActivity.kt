package com.smtz.cvgenius.presentation.details.reference

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.MobileAds
import com.smtz.cvgenius.R
import com.smtz.cvgenius.common.components.CvSingleton
import com.smtz.cvgenius.core.BaseActivity
import com.smtz.cvgenius.databinding.ActivityReferenceBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.model.ReferenceVO

class ReferenceActivity : BaseActivity<ActivityReferenceBinding>(), ButtonSaveReferenceDelegate {

    private var mCvVO: CvVO? = null
    private var mReferenceList: MutableList<ReferenceVO> = mutableListOf()
    private var mAddDetailAdapter: ReferenceAdapter = ReferenceAdapter(this)

    private lateinit var mReferenceViewPod: ReferenceViewPod

    override val binding: ActivityReferenceBinding by lazy {
        ActivityReferenceBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // initializing AdMob
        MobileAds.initialize(this) {}

        mCvVO = CvSingleton.instance.cvVO

        setUpViewPods()
        setUpListeners()
        setUpAdapters()
        setUpData()

    }

    private fun setUpData() {
        if (mCvVO?.references != null) {
            mReferenceList = mCvVO?.references!!

            mAddDetailAdapter.setNewData(mReferenceList)
        }
    }

    private fun setUpAdapters() {
        binding.rvReference.apply {
            adapter = mAddDetailAdapter
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setUpListeners() {
        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpViewPods() {
        mReferenceViewPod = findViewById(R.id.vpAddReference)
        mReferenceViewPod.setUpReferenceViewPod(this, true)
    }

    override fun onTapSaveReference(referenceVO: ReferenceVO) {
        val iterator = mReferenceList.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item.id == referenceVO.id) {
                iterator.remove()
            }
        }
        mReferenceList.add(referenceVO)
        mCvVO!!.references = mReferenceList
//        mCvModel.insertCV(mCvVO!!)        inserted in onDestroy or onBackPressed or navigating Preview
        mAddDetailAdapter.setNewData(mReferenceList)
        Toast.makeText(applicationContext, "saved", Toast.LENGTH_SHORT).show()
    }

    override fun onTapDelete(id: Long) {
        val iterator = mReferenceList.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item.id == id) {
                iterator.remove()
            }
        }
//        mCvModel.insertCV(mCvVO!!)
        mAddDetailAdapter.setNewData(mReferenceList)
        Toast.makeText(applicationContext, "Deleted", Toast.LENGTH_SHORT).show()
    }
}