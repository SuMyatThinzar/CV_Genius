package com.smtz.cvgenius.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.smtz.cvgenius.R
import com.smtz.cvgenius.core.BaseActivity
import com.smtz.cvgenius.databinding.ActivityChangeTemplateBinding
import com.smtz.cvgenius.databinding.ActivityPreviewBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.presentation.preview.PreviewActivity
import com.smtz.cvgenius.presentation.preview.templateViewPods.BaseViewPod
import com.smtz.cvgenius.presentation.preview.templateViewPods.ResumeFreeOneViewPod

class ChangeTemplateActivity : BaseActivity<ActivityChangeTemplateBinding>() {

    private var mCvVO: CvVO? = null
    private lateinit var mTemplateViewPod: BaseViewPod

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

        mTemplateViewPod = binding.root.findViewById(R.id.vp)
    }

    override fun onPause() {
        super.onPause()

        // 2159, 2748 (1374 * 2 page)
        Log.d("afasfasfda", "${binding.root.height} ${findViewById<ResumeFreeOneViewPod>(R.id.vp).height}")
    }
}