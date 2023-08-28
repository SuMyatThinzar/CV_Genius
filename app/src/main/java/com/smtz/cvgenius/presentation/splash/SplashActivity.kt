package com.smtz.cvgenius.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.smtz.cvgenius.R
import com.smtz.cvgenius.common.defaultPrefs
import com.smtz.cvgenius.common.get
import com.smtz.cvgenius.databinding.ActivitySplashBinding
import com.smtz.cvgenius.presentation.home.HomeActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // statusBar color ကို screen color အလိုက် ပြောင်းအောင်
        window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }

        binding.btnGetStarted.visibility = View.INVISIBLE

        Handler().postDelayed({
            val preference = defaultPrefs(applicationContext)
            val user = preference.get("new_user", "")

            if (user.isNotEmpty()) {
                startActivity(Intent(applicationContext, HomeActivity::class.java))
                finish() // remove from activity stack
            } else
                binding.btnGetStarted.visibility = View.VISIBLE

        }, 1500L)

        backgroundColorAnimation()                   // Background colors change Animation
        crossFadeTextChangeAnimation()               // Texts fade in/out Animation
        setUpListeners()
    }

    override fun onStart() {
        super.onStart()

        crossFadeAnimation(binding.btnGetStarted)     // Button fade Animation
        moveImageAnimation()                          // img move position Animation
    }

    private fun setUpListeners() {
        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(applicationContext, HomeActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun crossFadeAnimation(view: View) {
        view.alpha = 0.0f              // 0% visible

        view.animate()
            .alpha(1.0f)         // 100% visible
            .setDuration(1000L)
            .setStartDelay(1500L)
            .setListener(null)
    }

    private fun crossFadeTextChangeAnimation() {

        val fadeInAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        val fadeOutAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)

        val animationSet = AnimationSet(true)
        animationSet.addAnimation(fadeOutAnimation)
        animationSet.addAnimation(fadeInAnimation)

        fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                binding.tvAppNameLabel.visibility = View.GONE
                binding.tvLabel.visibility = View.VISIBLE
                binding.tvLabel.startAnimation(fadeInAnimation)
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })

        Handler(Looper.getMainLooper()).postDelayed({
            binding.tvAppNameLabel.startAnimation(animationSet)
        }, 500)
    }

    private fun backgroundColorAnimation() {
        // 1. set 2bg_list.xml in drawable, 2.set bg in layout, 3.
        val animationDrawable: AnimationDrawable = binding.root.background as AnimationDrawable

        animationDrawable.apply {
            setEnterFadeDuration(500)
            isOneShot = true           // no looping
            start()
        }
    }

    @SuppressLint("ResourceType")
    private fun moveImageAnimation() {
        // 1. set direction in xml, 2.
        binding.ivSplash.startAnimation(AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.move_image
        ))
    }
}