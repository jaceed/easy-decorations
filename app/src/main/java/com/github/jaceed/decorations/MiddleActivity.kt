package com.github.jaceed.decorations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.jaceed.decorations.linear.LinearDecoration
import com.github.jaceed.decorations.linear.MiddleDividerItemDecoration
import kotlinx.android.synthetic.main.activity_middle.*

class MiddleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_middle)

        setupList()
        setupListHorizontal()
    }

    private fun setupList() {
        list.adapter = ItemAdapter(30)
        list.addItemDecoration(MiddleDividerItemDecoration(this, LinearDecoration.VERTICAL).apply {
            setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_purple_16dp, null) ?: return@apply)
        })
    }

    private fun setupListHorizontal() {
        list_h.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        list_h.adapter = ItemAdapter(10, LinearDecoration.HORIZONTAL)
        list_h.addItemDecoration(MiddleDividerItemDecoration(this, LinearDecoration.HORIZONTAL).apply {
            setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_blue_16dp, null) ?: return@apply)
        })
    }
}