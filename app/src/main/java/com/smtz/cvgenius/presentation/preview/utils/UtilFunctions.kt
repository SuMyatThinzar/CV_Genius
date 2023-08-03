package com.smtz.cvgenius.presentation.preview.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.FileProvider
import com.itextpdf.text.PageSize
import java.io.File
import kotlin.math.ceil

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

    // childCountToReduce က အောက်ကဟိုဘက်ပို့ရမယ့် child တွေကိုဖယ်ဖို့
    for (i in 0 until containerToCheckHeight.childCount -childCountToReduce) {
        val view = containerToCheckHeight.getChildAt(i)
        height += view.height.toFloat()
    }

    return height
}


fun convertToPdfDocument(context: Context, viewPodLayout: View): PdfDocument {
    val document = PdfDocument()

    // 1374 the actual fix height of 1 page for this activity
    // val viewPodLayout = findViewById<ResumeFreeOneViewPod>(R.id.vpResumeFreeOne)

//    val viewPodLayout = findViewById<BaseViewPod>(mViewPodId)

    // Manually measure and layout the viewPodLayout (not need if the layout height is displayed fully) can setUp as val totalHeight = viewPodLayout.height
    val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(viewPodLayout.width, View.MeasureSpec.EXACTLY)
    val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    viewPodLayout.measure(widthMeasureSpec, heightMeasureSpec)
    viewPodLayout.layout(0, 0, viewPodLayout.measuredWidth, viewPodLayout.measuredHeight)

    val totalHeight = viewPodLayout.measuredHeight

    Log.d("adsfasfasdf","${totalHeight} ${PageSize.A4.height*4.5}")

    // Calculate the number of pages required
    val numPages = ceil((totalHeight / 1374.0)).toInt()  // instead of PageSize.A4.height*4.5 = 3789, used fixed size 1374

    for (pageNo in 0 until numPages) {
        val pageInfo = PdfDocument.PageInfo.Builder(
            (PageSize.A4.width * 4.5).toInt(),
            (PageSize.A4.height * 4.5).toInt(),
            pageNo + 1
        ).create()
        val page = document.startPage(pageInfo)

        val density = context.resources.displayMetrics.density
        val bitmap = Bitmap.createBitmap(
            (pageInfo.pageWidth * density).toInt(),
            (pageInfo.pageHeight * density).toInt(),
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        canvas.scale(density, density)

        val startY = pageNo * 1374.toFloat()                       // use the actual 1 page height
        val endY = (startY + 1374).toInt().coerceAtMost(totalHeight)   // content

        // Adjust the viewport on the canvas to render the section of content
        canvas.translate(0f, -startY)
        canvas.clipRect(0, startY.toInt(), viewPodLayout.width, endY)

        viewPodLayout.draw(canvas)

        val pdfCanvas = page.canvas
        pdfCanvas.drawBitmap(bitmap, 0f, 0f, null)

        document.finishPage(page)
    }

    return document
}


fun createTemporaryPdfFile(context: Context): File {
    val cacheDir = context.cacheDir
    return File.createTempFile("temp_pdf", ".pdf", cacheDir)
}


fun shareDocument(context: Context, pdfFile: File) {
    val contentUri: Uri = FileProvider.getUriForFile(context, "com.smtz.cvgenius.fileprovider", pdfFile)

    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "application/pdf"
    shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

    val chooserIntent = Intent.createChooser(shareIntent, "Share PDF Document")
    chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    try {
        context.startActivity(chooserIntent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}