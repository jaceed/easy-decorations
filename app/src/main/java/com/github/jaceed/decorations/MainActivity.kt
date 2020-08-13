package com.github.jaceed.decorations

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        middle.setOnClickListener {
            startActivity(Intent(this, MiddleActivity::class.java))
        }

        ranged.setOnClickListener {
            startActivity(Intent(this, RangedActivity::class.java))
        }
    }

}