package com.nexters.frutto.view.component

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView


class TopCornerRoundImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {
    private val path = Path()
    private val rectF = RectF()

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            val scale = context.resources.displayMetrics.density
            val top = 0f
            val left = 0f
            val right = width.toFloat()
            val bottom = height.toFloat()
            val round = 15f * scale
            val sweepAngle = -90f

            rectF.run {
                this.right = right
                this.bottom = bottom
            }
            path.run {
                moveTo(right, bottom)
                rLineTo(right, top + round)
                arcTo(right - (2 * round), top, right, top + (2 * round), 0f, sweepAngle, false) // Top-Right Corner
                rLineTo(left + round, top)
                arcTo(left, top, left + (2 * round), top + (2 * round), 270f, sweepAngle, false) // Top-Left Corner
                rLineTo(left, bottom)
                close()
                addArc(rectF, 0f, 0f)
            }

            canvas.clipPath(path)
        }
        super.onDraw(canvas)
    }
}