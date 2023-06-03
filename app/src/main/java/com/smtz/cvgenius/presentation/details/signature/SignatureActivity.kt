package com.smtz.cvgenius.presentation.details.signature

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import com.smtz.cvgenius.R
import com.smtz.cvgenius.common.CvSingleton
import com.smtz.cvgenius.common.DrawingView
import com.smtz.cvgenius.core.BaseActivity
import com.smtz.cvgenius.data.repository.CvModelImpl
import com.smtz.cvgenius.databinding.ActivitySignatureBinding
import com.smtz.cvgenius.domain.model.*
import com.smtz.cvgenius.domain.repository.CvModel
import com.smtz.cvgenius.utils.setUpTitleAndButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SignatureActivity : BaseActivity<ActivitySignatureBinding>() {

    private lateinit var drawingView: DrawingView
    private var mImagePath: String? = null
    private var mSignature: String? = null

    private var mCvModel: CvModel = CvModelImpl

    private var mCvVO: CvVO? = null

    override val binding: ActivitySignatureBinding by lazy {
        ActivitySignatureBinding.inflate(layoutInflater)
    }

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, SignatureActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mCvVO = CvSingleton.instance.cvVO

        drawingView = binding.drawingView
        setUpTitleAndButton(expand = true, binding.tvTitle, binding.btnBack, binding.frameLayout)
        setUpListeners()
        requestData()
    }

    private fun requestData() {
        mCvVO?.signature?.let {
            mSignature = it
            val bitmap = BitmapFactory.decodeFile(it)
            val drawable = BitmapDrawable(resources, bitmap)
            binding.drawingView.background = drawable
        }
    }

    private fun setUpListeners() {

        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }

        // Save drawing button click listener
        binding.btnSave.setOnClickListener {
            if (drawingView.isDrawingEmpty()) {
                mCvVO?.signature = null
                mCvModel.insertCV(mCvVO!!)
                finish()
            } else {
                saveDrawing()
            }
        }

        binding.btnClear.setOnClickListener {
            drawingView.clearDrawing()
            binding.drawingView.background = AppCompatResources.getDrawable(this, R.drawable.background_edit_text)
        }
    }

    private fun saveDrawing() {
        val imagePath = drawingView.saveDrawingAsImage(this)
        if (imagePath != null) {
            mImagePath = imagePath
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    mCvVO?.signature = imagePath
                    mCvModel.insertCV(mCvVO!!)
                }
            }
            Toast.makeText(applicationContext, "saved", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onBackPressed() {
        if (drawingView.isDrawingEmpty() && drawingView.background == AppCompatResources.getDrawable(this, R.drawable.background_edit_text)) {
            mCvVO?.signature = null
            mCvModel.insertCV(mCvVO!!)
        }
        super.onBackPressed()
    }
}