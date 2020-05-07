package com.nexters.frutto.view.listener

import android.view.MotionEvent
import android.view.View
import com.nexters.frutto.bus.SWIPE_DOWN
import com.nexters.frutto.bus.SWIPE_NOT
import com.nexters.frutto.bus.SWIPE_UP
import com.nexters.frutto.bus.sendSwipeEvent
import com.nexters.frutto.extension.isNegative
import com.nexters.frutto.extension.isPositive
import kotlin.math.absoluteValue

class SwipeDetecter : View.OnTouchListener {
    companion object {
        private const val MIN_DISTANCE = 20
    }

    private var sendEvent = false
    private var downY = 0f
    private var moveY = 0f

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        event?.let {
            when (it.action) {
                MotionEvent.ACTION_DOWN -> downY = it.y

                MotionEvent.ACTION_MOVE -> {
                    moveY += downY - it.y
                    checkSwipe(moveY)
                }

                MotionEvent.ACTION_UP -> {
                    sendEvent = false
                    moveY = 0f
                }
            }
        }
        return false
    }

    private fun checkSwipe(diffY: Float) {
        if (diffY.absoluteValue > MIN_DISTANCE && !sendEvent) {
            sendEvent = true
            val swipe = when {
                diffY.isPositive -> SWIPE_UP
                diffY.isNegative -> SWIPE_DOWN
                else -> SWIPE_NOT
            }
            sendSwipeEvent(swipe)
        }
    }
}