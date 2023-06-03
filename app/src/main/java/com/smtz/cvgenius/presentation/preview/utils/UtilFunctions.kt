package com.smtz.cvgenius.presentation.preview.utils

import android.view.View
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
