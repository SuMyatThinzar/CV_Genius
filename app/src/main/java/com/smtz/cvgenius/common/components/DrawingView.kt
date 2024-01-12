package com.smtz.cvgenius.common.components

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Base64
import android.view.MotionEvent
import android.view.View
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var path: Path = Path()
    private var paint: Paint = Paint()

    init {
        paint.isAntiAlias = true
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeWidth = 7f
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val xPos = event.x
        val yPos = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(xPos, yPos)
                // Redraw immediately when starting a new path
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(xPos, yPos)
                // Redraw during the movement
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                // No need to update anything here
            }
        }

        return true
    }

    fun saveDrawingAsImage(context: Context): String? {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        draw(canvas)

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        byteArrayOutputStream.close()

        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

//    fun saveDrawingAsImage(context: Context): String? {
//        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
//        val canvas = Canvas(bitmap)
//        draw(canvas)
//
//        val imagePath = context.getExternalFilesDir(null)?.absolutePath + "/drawing.png"
//        val imageFile = File(imagePath)
//        val fileOutputStream = FileOutputStream(imageFile)
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
//        fileOutputStream.close()
//
//        return imagePath
//    }

    fun clearDrawing() {
        path.reset()
        invalidate()
    }

    // to check if the drawing is empty or exist
    fun isDrawingEmpty(): Boolean {
        return path.isEmpty
    }

}