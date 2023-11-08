package com.smtz.cvgenius.common

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.smtz.cvgenius.presentation.details.workExperiences.WorkExperienceViewPod
import com.smtz.cvgenius.utils.topMargin

fun checkInternetConnection(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}


fun setUpLayoutParams(view: View, left: Int, top: Int, right: Int, bottom: Int): ViewGroup.MarginLayoutParams {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.setMargins(left, top, right, bottom)
    return layoutParams
}

// to monitor the collapsing toolbar's offset and change necessary sizes
fun setUpAppBarTitleManually(appBarBinding: AppBarLayout, tvHeading: TextView, collapsingToolbarLayout: CollapsingToolbarLayout, isSampleTemplate: Boolean = false) {

    appBarBinding.addOnOffsetChangedListener { appBarLayout, verticalOffset ->

        val parentViewHeading = tvHeading.parent as ViewGroup
        parentViewHeading.removeView(tvHeading)

        // Calculate the fraction of toolbar collapse (0.0 for fully expanded, 1.0 for fully collapsed)
        val collapseFactor = -verticalOffset / appBarLayout.totalScrollRange.toFloat()

        var topMarginExpand = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) 162 else 166
        var topMarginCollapse = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) 130 else 134

        if (isSampleTemplate) {
            topMarginExpand -= 50
            topMarginCollapse -= 50
        }

        val layoutParams = tvHeading.layoutParams as CollapsingToolbarLayout.LayoutParams
        layoutParams.topMargin = if (collapseFactor >= 0.6) topMarginCollapse else topMarginExpand
        layoutParams.collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL

        tvHeading.layoutParams = layoutParams
        tvHeading.textSize = if (collapseFactor >= 0.6) 19F else 23F
        collapsingToolbarLayout.addView(tvHeading)
    }
}

fun expandCardView(expandView: View, collapseView: View, root: CardView) {
    // Expand the layout
    expandView.visibility = View.VISIBLE
    root.radius = 48F

    expandView.measure(
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    )
    val targetHeight = expandView.measuredHeight - 160

    val anim = ValueAnimator.ofInt(collapseView.height, targetHeight)   // starting, ending
    anim.addUpdateListener { valueAnimator ->
        val value = valueAnimator.animatedValue as Int
        val params = expandView.layoutParams
        params.height = value
        expandView.layoutParams = params
    }
    anim.duration = 500
    anim.start()
}

fun collapseCardView(expandView: View, collapseView: View, root: CardView) {
    // Collapse the layout
    val anim = ValueAnimator.ofInt(expandView.height, collapseView.height)
    anim.addUpdateListener { valueAnimator ->
        val value = valueAnimator.animatedValue as Int
        val params = expandView.layoutParams
        params.height = value
        expandView.layoutParams = params
    }
    anim.addListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator) {}
        override fun onAnimationEnd(animation: Animator) {
            expandView.visibility = View.GONE
            root.radius = 8F
        }
        override fun onAnimationCancel(animation: Animator) {}
        override fun onAnimationRepeat(animation: Animator) {}
    })
    anim.duration = 500
    anim.start()
}