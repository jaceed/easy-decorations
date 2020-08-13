package com.github.jaceed.decorations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.jaceed.decorations.linear.LinearDecoration
import com.github.jaceed.decorations.linear.RangedDividerItemDecoration
import kotlinx.android.synthetic.main.activity_ranged.*

class RangedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranged)

        setupList()
        setupListHorizontal()
    }

    private fun setupList() {
        list.adapter = ItemAdapter(30)
        list.addItemDecoration(RangedDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 0, 1).apply {
            setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_red_16dp, null) ?: return@apply)
        })
        list.addItemDecoration(RangedDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 3, 6).apply {
            setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_blue_16dp, null) ?: return@apply)
        })
        list.addItemDecoration(RangedDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 29, 30).apply {
            setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_purple_16dp, null) ?: return@apply)
        })
        list.addItemDecoration(RangedDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 11, 13, true).apply {
            setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_gradient_green_32dp, null) ?: return@apply)
        })
        list.addItemDecoration(RangedDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 0, 1, true).apply {
            setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_gradient_green_32dp, null) ?: return@apply)
        })
        list.addItemDecoration(RangedDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 5, 8, true).apply {
            setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_gradient_green_32dp, null) ?: return@apply)
        })
    }

    private fun setupListHorizontal() {
        list_h.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        list_h.adapter = ItemAdapter(20, LinearDecoration.HORIZONTAL)
        list_h.addItemDecoration(RangedDividerItemDecoration(this, DividerItemDecoration.HORIZONTAL, 0, 2).apply {
            setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_red_16dp, null) ?: return@apply)
        })
        list_h.addItemDecoration(RangedDividerItemDecoration(this, DividerItemDecoration.HORIZONTAL, 3, 4).apply {
            setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_blue_16dp, null) ?: return@apply)
        })
        list_h.addItemDecoration(RangedDividerItemDecoration(this, DividerItemDecoration.HORIZONTAL, 9, 10).apply {
            setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_purple_16dp, null) ?: return@apply)
        })
        list_h.addItemDecoration(RangedDividerItemDecoration(this, DividerItemDecoration.HORIZONTAL, 19, 20).apply {
            setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_orange_16dp, null) ?: return@apply)
        })
        list_h.addItemDecoration(RangedDividerItemDecoration(this, DividerItemDecoration.HORIZONTAL, 0, 1, true).apply {
            setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_gradient_green_v_32dp, null) ?: return@apply)
        })
        list_h.addItemDecoration(RangedDividerItemDecoration(this, DividerItemDecoration.HORIZONTAL, 2, 5, true).apply {
            setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_gradient_green_v_32dp, null) ?: return@apply)
        })
        list_h.addItemDecoration(RangedDividerItemDecoration(this, DividerItemDecoration.HORIZONTAL, 9, 14, true).apply {
            setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_gradient_green_v_32dp, null) ?: return@apply)
        })
        list_h.addItemDecoration(RangedDividerItemDecoration(this, DividerItemDecoration.HORIZONTAL, 19, 20, true).apply {
            setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_gradient_green_v_32dp, null) ?: return@apply)
        })
    }
}