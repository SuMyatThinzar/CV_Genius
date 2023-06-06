package com.smtz.cvgenius.presentation.preview

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.CancellationSignal
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.print.*
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.itextpdf.text.PageSize
import com.smtz.cvgenius.R
import com.smtz.cvgenius.common.CvSingleton
import com.smtz.cvgenius.core.BaseActivity
import com.smtz.cvgenius.databinding.ActivityPreviewBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.presentation.ChangeTemplateActivity
import com.smtz.cvgenius.presentation.preview.templateViewPods.BaseViewPod
import com.smtz.cvgenius.presentation.preview.templateViewPods.ResumeFreeOneViewPod
import java.io.*
import kotlin.math.ceil

class PreviewActivity : BaseActivity<ActivityPreviewBinding>() {

    val storageDirectory = Environment.getExternalStorageDirectory().path

    private var mCvVO: CvVO? = null
    private lateinit var mTemplateViewPod: BaseViewPod
    private var mViewPodId: Int = 1111

    override val binding: ActivityPreviewBinding by lazy {
        ActivityPreviewBinding.inflate(layoutInflater)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, PreviewActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE),
            PackageManager.PERMISSION_GRANTED
        )

        mCvVO = CvSingleton.instance.cvVO
        setUpTemplate()

//        mTemplateViewPod = binding.root.findViewById(R.id.vpResumeFreeOne)
        setUpListeners()

    }

    private fun setUpTemplate() {

//        if (mCvVO?.templateId in 2..6) {
//            setUpViewPod(R.id.vpResumeSecondTwo)
//            binding.cardViewFirst.visibility = View.GONE
//            binding.cardViewSecond.visibility = View.VISIBLE
//        }
//        if (mCvVO?.templateId == 0) {
//            setUpViewPod(R.id.vpResumeFreeOne)
//            binding.cardViewFirst.visibility = View.VISIBLE
//        }
        when (mCvVO?.templateId) {
            0 -> {
                setUpViewPod(R.id.vpResumeFreeOne)
                binding.cardViewFirst.visibility = View.VISIBLE
            }
            2 -> {
                setUpViewPod(R.id.vpResumeSecondTwo)
                binding.cardViewSecondTwo.visibility = View.VISIBLE
            }
            3 -> {
                setUpViewPod(R.id.vpResumeSecondTwo)
                binding.cardViewSecondTwo.visibility = View.VISIBLE
            }
            4 -> {
                setUpViewPod(R.id.vpResumeSecondTwo)
                binding.cardViewSecondTwo.visibility = View.VISIBLE
            }
            5 -> {
                setUpViewPod(R.id.vpResumeSecondTwo)
                binding.cardViewSecondTwo.visibility = View.VISIBLE
            }
            6 -> {
                setUpViewPod(R.id.vpResumeSecondTwo)
                binding.cardViewSecondTwo.visibility = View.VISIBLE
            }
        }
    }

    private fun setUpViewPod(resumeId: Int) {
        mViewPodId = resumeId
        mTemplateViewPod = binding.root.findViewById(resumeId)
    }

    private fun setUpListeners() {
        binding.btnChangeTemplate.setOnClickListener {
            startActivity(Intent(ChangeTemplateActivity.newIntent(this)))
        }
        binding.btnDownload.setOnClickListener {
//            val pageInfo = PdfDocument.PageInfo.Builder(
//                PageSize.A4.width.toInt(),
//                PageSize.A4.height.toInt(),
//                1
//            ).create()

            convertViewPodToPdf(this)
        }

        binding.btnPrint.setOnClickListener {
            val pdfDocument = convertToPdf(this)
            printPdfDocument(pdfDocument, System.currentTimeMillis().toString(), this)

        }
    }

    private fun printPdfDocument(pdfDocument: PdfDocument, printJobName: String, context: Context) {
        val printManager = context.getSystemService(Context.PRINT_SERVICE) as? PrintManager

        printManager?.let {
            val printAttributes = PrintAttributes.Builder()
                .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                .setResolution(PrintAttributes.Resolution("pdf", "pdf", 600, 600))
                .setColorMode(PrintAttributes.COLOR_MODE_COLOR)
                .build()

            val printAdapter = object : PrintDocumentAdapter() {
                override fun onLayout(
                    oldAttributes: PrintAttributes?,
                    newAttributes: PrintAttributes,
                    cancellationSignal: CancellationSignal?,
                    callback: LayoutResultCallback,
                    extras: Bundle?
                ) {
                    val info = PrintDocumentInfo.Builder(printJobName)
                        .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                        .setPageCount(pdfDocument.pages.size)
                        .build()

                    callback.onLayoutFinished(info, newAttributes != oldAttributes)
                }

                override fun onWrite(
                    pages: Array<out PageRange>?,
                    destination: ParcelFileDescriptor?,
                    cancellationSignal: CancellationSignal?,
                    callback: WriteResultCallback?
                ) {
                    pdfDocument.writeTo(FileOutputStream(destination?.fileDescriptor))
                    callback?.onWriteFinished(arrayOf(PageRange.ALL_PAGES))
                }
            }

            printManager.print(printJobName, printAdapter, printAttributes)
        }
    }

    private fun convertViewPodToPdf(context: Context) {
        val document = convertToPdf(context)

        // create private self directory
        val file = File(
            this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
            "${mCvVO?.personalDetails?.firstName ?: System.currentTimeMillis()} ${mCvVO?.personalDetails?.lastName} ${System.currentTimeMillis()}.pdf"
        )

        // create in public default directory
//        val file = File(
//            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
//            "${mCvVO?.personalDetails?.firstName ?: System.currentTimeMillis()} ${mCvVO?.personalDetails?.lastName} ${System.currentTimeMillis()}.pdf"
//        )

        try {
            val fileOutputStream = FileOutputStream(file)
            document.writeTo(fileOutputStream)
            document.close()
            fileOutputStream.close()

            Toast.makeText(context, "PDF saved successfully", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun convertToPdf(context: Context): PdfDocument {
        val document = PdfDocument()

        // 1374 the actual fix height of 1 page for this activity
//        val viewPodLayout = findViewById<ResumeFreeOneViewPod>(R.id.vpResumeFreeOne)

        val viewPodLayout = findViewById<BaseViewPod>(mViewPodId)

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


//    private fun convertViewPodToPdf(context: Context) {
//
//        val document = convertToPdf(context)
//
//        // Save the PDF to a file
//        val file = File(this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "${mCvVO?.personalDetails?.firstName?:System.currentTimeMillis()} ${mCvVO?.personalDetails?.lastName}.pdf")
//
//        try {
//            val fileOutputStream = FileOutputStream(file)
//            document.writeTo(fileOutputStream)
//            document.close()
//            fileOutputStream.close()
//
//            // Show a toast or perform other actions to indicate successful conversion
//            Toast.makeText(context, "PDF saved successfully", Toast.LENGTH_SHORT).show()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun convertToPdf(context: Context): PdfDocument {
//        val document = PdfDocument()
//
//        val displayMetrics = resources.displayMetrics
//        val width: Float = displayMetrics.widthPixels.toFloat()
//        val height: Float = displayMetrics.heightPixels.toFloat()
//        val convertWidth = width.toInt()
//        val convertHeight = height.toInt()
//
//        // Create a blank page
//        val pageInfo = PdfDocument.PageInfo.Builder((PageSize.A4.width*4.5).toInt(), (PageSize.A4.height*4.5).toInt(), 1).create()
//        val page = document.startPage(pageInfo)
//
//        // Create a bitmap with a higher density for better resolution
//        val density = context.resources.displayMetrics.density
//        val bitmap = Bitmap.createBitmap((pageInfo.pageWidth * density).toInt(), (pageInfo.pageHeight * density).toInt(), Bitmap.Config.ARGB_8888)
//        val canvas = Canvas(bitmap)
//        canvas.scale(density, density)
//
//        // Inflate the custom view pod layout
//        val viewPodLayout = findViewById<ResumeFreeOneViewPod>(R.id.vpResumeFreeOne)
//
//        // Measure and layout the view
//        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(viewPodLayout.width, View.MeasureSpec.EXACTLY)
//        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(viewPodLayout.height, View.MeasureSpec.EXACTLY)
//        viewPodLayout.measure(widthMeasureSpec, heightMeasureSpec)
//        viewPodLayout.layout(0, 0, viewPodLayout.measuredWidth, viewPodLayout.measuredHeight)
//
//        // Draw the view onto the canvas
//        viewPodLayout.draw(canvas)
//
//        // Draw the bitmap onto the PDF page
//        val pdfCanvas = page.canvas
//        pdfCanvas.drawBitmap(bitmap, 0f, 0f, null)
//
//        // Finish the page
//        document.finishPage(page)
//
//        return document
//
//    }

}