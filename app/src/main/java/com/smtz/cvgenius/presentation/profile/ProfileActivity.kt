package com.smtz.cvgenius.presentation.profile

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.smtz.cvgenius.core.BaseActivity
import com.smtz.cvgenius.data.repository.CvModelImpl
import com.smtz.cvgenius.databinding.ActivityProfileBinding
import com.smtz.cvgenius.domain.repository.CvModel
import com.smtz.cvgenius.presentation.home.CvListAdapter

class ProfileActivity : BaseActivity<ActivityProfileBinding>() {

    override val binding: ActivityProfileBinding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

}