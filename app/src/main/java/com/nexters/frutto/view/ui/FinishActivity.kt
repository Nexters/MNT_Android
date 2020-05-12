package com.nexters.frutto.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nexters.frutto.R
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        confirm_btn.setOnClickListener {
            startActivity(Intent(this, SelectManitoActivity::class.java))
            finish()
        }
    }
}
