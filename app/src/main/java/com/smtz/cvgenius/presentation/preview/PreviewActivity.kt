package com.smtz.cvgenius.presentation.preview

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.os.*
import android.print.*
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.google.android.material.snackbar.Snackbar
import com.smtz.cvgenius.R
import com.smtz.cvgenius.common.CvSingleton
import com.smtz.cvgenius.data.repository.CvModelImpl
import com.smtz.cvgenius.databinding.ActivityPreviewBinding
import com.smtz.cvgenius.domain.model.CvVO
import com.smtz.cvgenius.domain.repository.CvModel
import com.smtz.cvgenius.presentation.ChangeTemplateActivity
import com.smtz.cvgenius.presentation.preview.templateViewPods.BaseViewPod
import com.smtz.cvgenius.presentation.preview.utils.convertToPdfDocument
import com.smtz.cvgenius.presentation.preview.utils.createTemporaryPdfFile
import com.smtz.cvgenius.presentation.preview.utils.shareDocument
import com.smtz.cvgenius.presentation.template.SampleTemplateActivity
import kotlinx.coroutines.*
import java.io.*

class PreviewActivity : AppCompatActivity() {

    private val REQUEST_CODE_PERMISSION_STORAGE = 100
    private lateinit var binding: ActivityPreviewBinding

    var cacheFile: File? = null

    private var mCvModel: CvModel = CvModelImpl
    private var mCvId: Long = 0L
    private var mCvVO: CvVO? = null
    private lateinit var mTemplateViewPod: BaseViewPod
    private var mViewPodId: Int = 1111

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, PreviewActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val documentsDirectory = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)  /storage/emulated/0/Android/data/com.smtz.cvgenius/files/Documents
        val documentsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) //storage/emulated/0/Documents
//        val documentsDirectory2 = Environment.getExternalStorageDirectory().path  /storage/emulated/0

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_PERMISSION_STORAGE)

        mCvVO = CvSingleton.instance.cvVO     // get from singleton
        setUpTemplate()
        setUpListeners()

    }

    private fun setUpTemplate() {

        if (mCvVO?.templateId in 2..6) {
            setUpViewPod(R.id.vpResumeSecondTwo)
            binding.cardViewSecondTwo.visibility = View.VISIBLE
        }
        if (mCvVO?.templateId == 0) {
            setUpViewPod(R.id.vpResumeFreeOne)
            binding.cardViewFirst.visibility = View.VISIBLE
        }
    }

    private fun setUpViewPod(resumeId: Int) {
        mViewPodId = resumeId
        mTemplateViewPod = binding.root.findViewById(resumeId)
//        mTemplateViewPod = binding.root.findViewById(R.id.vpResumeFreeOne)
    }

    private fun setUpListeners() {
        val viewPodLayout = findViewById<BaseViewPod>(mViewPodId)

        binding.flPreview.setOnClickListener {
            startActivity(Intent(ChangeTemplateActivity.newIntent(this)))
        }
        binding.btnDownload.setOnClickListener {
            convertAndSaveViewPodAsPdf(this, viewPodLayout)
        }

        binding.btnPrint.setOnClickListener {
            handleCacheFile(viewPodLayout) { cache ->
                printCacheFile(this, cache)
            }
        }

        binding.btnShare.setOnClickListener {
            handleCacheFile(viewPodLayout, callback = { cache ->
                shareDocument(this, cache)
            })
        }

        binding.btnChangeTemplate.setOnClickListener {
            startActivity(SampleTemplateActivity.newIntent(this, SampleTemplateActivity.CHANGE_ID))
        }
    }

    private fun handleCacheFile(viewPodLayout: View, callback: (File) -> Unit){
        if (cacheFile == null) {
            setViewsClickable(false)

            GlobalScope.launch(Dispatchers.IO) {
                val pdfDocument = convertToPdfDocument(this@PreviewActivity, viewPodLayout)
                cacheFile = createTemporaryPdfFile(this@PreviewActivity)

                val outputStream = FileOutputStream(cacheFile)
                pdfDocument.writeTo(outputStream)
                outputStream.flush()
                outputStream.close()

                withContext(Dispatchers.Main) {
                    setViewsClickable(true)
                    callback(cacheFile!!)
                }
            }
        } else {
            callback(cacheFile!!)
        }
    }

    private fun printCacheFile(context: Context, cacheFile: File) {
        val printManager = context.getSystemService(Context.PRINT_SERVICE) as PrintManager
        val printJobName = context.getString(R.string.app_name)

        printManager.let {
            val printAttributes = PrintAttributes.Builder()
                .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                .setResolution(PrintAttributes.Resolution("pdf", "pdf", 600, 600))
                .setColorMode(PrintAttributes.COLOR_MODE_COLOR)
                .build()

            val printAdapter = object : PrintDocumentAdapter() {
                override fun onLayout(
                    oldAttributes: PrintAttributes?,
                    newAttributes: PrintAttributes?,
                    cancellationSignal: CancellationSignal?,
                    callback: LayoutResultCallback?,
                    extras: Bundle?
                ) {
                    if (cancellationSignal?.isCanceled == true) {
                        callback?.onLayoutCancelled()
                        return
                    }

                    // Respond with the print document's layout info
                    val layoutResult = PrintDocumentInfo.Builder(printJobName)
                        .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                        .build()

                    callback?.onLayoutFinished(layoutResult, newAttributes != oldAttributes)
                }

                override fun onWrite(
                    pages: Array<out PageRange>?,
                    destination: ParcelFileDescriptor?,
                    cancellationSignal: CancellationSignal?,
                    callback: WriteResultCallback?
                ) {
                    // Open the input and output streams
                    val inputStream = FileInputStream(cacheFile)
                    val outputStream = FileOutputStream(destination?.fileDescriptor)

                    // Copy the content from input stream to output stream
                    val buffer = ByteArray(1024)
                    var bytesRead: Int
                    while (inputStream.read(buffer).also { bytesRead = it } > 0) {
                        outputStream.write(buffer, 0, bytesRead)
                    }

                    inputStream.close()
                    outputStream.close()

                    // Signal the system that the write operation is complete
                    callback?.onWriteFinished(arrayOf(PageRange.ALL_PAGES))
                }
            }
            printManager.print(printJobName, printAdapter, printAttributes)
        }
    }

    private fun convertAndSaveViewPodAsPdf(context: Context, viewPodLayout: View) {
//      val pageInfo = PdfDocument.PageInfo.Builder(PageSize.A4.width.toInt(), PageSize.A4.height.toInt(), 1).create()

        setViewsClickable(false)

        // send to background thread
        GlobalScope.launch(Dispatchers.IO) {
            val document = convertToPdfDocument(context, viewPodLayout)

            // create private self directory
//            val file = File(
//                this@PreviewActivity.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
//                "${mCvVO?.personalDetails?.firstName ?: System.currentTimeMillis()} ${mCvVO?.personalDetails?.lastName} ${System.currentTimeMillis()}.pdf"
//            )

            // create in public default directory
            val fileName = "${mCvVO?.personalDetails?.firstName ?: System.currentTimeMillis()} ${mCvVO?.personalDetails?.lastName} ${System.currentTimeMillis()}.pdf"

            // above Api 29, to save the file in the public directory, you need to use the MediaStore API to save in that directory. Unless you have to save in your specific directory with your app's package name
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")

                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS)
                }

                val resolver = context.contentResolver
                val uri = resolver.insert(
                    MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL),
                    contentValues
                )
                uri?.let { fileUri ->
                    resolver.openOutputStream(fileUri)?.use { outputStream ->
                        val fileOutputStream = outputStream as FileOutputStream
                        document.writeTo(fileOutputStream)
                        document.close()

                        withContext(Dispatchers.Main) {
                            setViewsClickable(true)
                            Snackbar.make(window.decorView, "saved PDF file successfully", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                val documentsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                val file = File(documentsDirectory, fileName)
                try {
                    val fileOutputStream = FileOutputStream(file)
                    document.writeTo(fileOutputStream)
                    document.close()
                    fileOutputStream.close()

                    withContext(Dispatchers.Main) {
                        setViewsClickable(true)
                        Snackbar.make(window.decorView, "saved PDF file successfully", Snackbar.LENGTH_SHORT).show()
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    withContext(Dispatchers.Main) {
                        setViewsClickable(true)
                        Snackbar.make(window.decorView, "failed to save PDF file $e", Snackbar.LENGTH_SHORT).show()
                        Log.d("adfafd", "$e")
                    }
                }
            }
        }
    }

    private fun setViewsClickable(clickable: Boolean) {
        binding.btnDownload.isClickable = clickable
        binding.btnSend.isClickable = clickable
        binding.btnPrint.isClickable = clickable
        binding.btnShare.isClickable = clickable
        binding.btnChangeTemplate.isClickable = clickable

        if (!clickable) {
            binding.progressBar.visibility = View.VISIBLE
            binding.darkenBackground.visibility = View.VISIBLE
            binding.tvPleaseWait.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.darkenBackground.visibility = View.GONE
            binding.tvPleaseWait.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cacheFile?.let {
            if (it.exists()) {
                it.delete()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_PERMISSION_STORAGE -> {
                // Check if all permissions were granted
                val allPermissionsGranted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
                if (allPermissionsGranted) {
                    // All permissions were granted

                } else {
                    // At least one permission was denied, handle accordingly
                    println("Storage permission denied")
                }
            }
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

}