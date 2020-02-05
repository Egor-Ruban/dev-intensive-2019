package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.widget.ImageView



class CircleImageView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttrs:Int=0,
        borderColor : Int = Color.BLACK
) : ImageView(
        context, attrs, defStyleAttrs
){

    private var borderColor = Color.BLACK
    private var borderWidth = 20.0f

    override fun onDraw(canvas: Canvas) {
        drawRoundImage(canvas)
        drawStroke(canvas)
    }

    fun setBorderColor(color : Int){
        borderColor = color
    }

    fun getBorderColor() = borderColor

    fun setBorderWidth(value : Float){
        borderWidth = value
    }

    fun getBorderWidth() = borderWidth

    private fun drawStroke(canvas: Canvas) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val radius = width / 2f

        /* Border paint */
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth
        canvas.drawCircle(width / 2f, width / 2f, radius - borderWidth / 2f, paint)
    }

    private fun drawRoundImage(canvas: Canvas) {
        var b: Bitmap = (drawable as BitmapDrawable).bitmap
        val bitmap = b.copy(Bitmap.Config.ARGB_8888, true)

        /* Scale the bitmap */
        val scaledBitmap: Bitmap
        val ratio: Float = bitmap.width.toFloat() / bitmap.height.toFloat()
        val height: Int = Math.round(width / ratio)
        scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false)

        /* Cutting the outer of the circle */
        val shader: Shader
        shader = BitmapShader(scaledBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        val rect = RectF()
        rect.set(0f, 0f, width.toFloat(), height.toFloat())

        val imagePaint = Paint()
        imagePaint.isAntiAlias = true
        imagePaint.shader = shader
        canvas.drawRoundRect(rect, width.toFloat(), height.toFloat(), imagePaint)
    }

}