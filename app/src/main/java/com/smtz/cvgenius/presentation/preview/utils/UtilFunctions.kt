package com.smtz.cvgenius.presentation.preview.utils

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

fun setUpContentVisibilityResumeSecondOne(textView: TextView, content: String?, label: TextView?) {
    if (content != null) {
        textView.text = content
        label?.visibility = View.VISIBLE
        textView.visibility = View.VISIBLE
    } else {
        label?.visibility = View.GONE
        textView.visibility = View.GONE
    }
}


fun getCurrentPageHeight(containerToCheckHeight: LinearLayout, childCountToReduce: Int): Float {
    var height = 0f

    for (i in 0 until containerToCheckHeight.childCount -childCountToReduce) {
        val view = containerToCheckHeight.getChildAt(i)
        height += view.height.toFloat()
    }

    return height
}
