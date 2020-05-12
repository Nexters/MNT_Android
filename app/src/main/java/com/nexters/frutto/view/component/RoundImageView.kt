package com.nexters.frutto.view.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.ImageView


class RoundImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {
    private val scale = context.resources.displayMetrics.density
    private val radius = 10f * scale

    private val path = Path()
    private val rectF = RectF()

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            rectF.run {
                this.right = width.toFloat()
                this.bottom = height.toFloat()
            }
            path.addRoundRect(rectF, radius, radius, Path.Direction.CW)
            canvas.clipPath(path)
        }
        super.onDraw(canvas)
    }
}