package com.nexters.frutto.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nexters.frutto.R
import kotlinx.android.synthetic.main.activity_time_line_filter.*

class TimeLineFilterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line_filter)

        close_btn.setOnClickListener { finish() }
    }
}
