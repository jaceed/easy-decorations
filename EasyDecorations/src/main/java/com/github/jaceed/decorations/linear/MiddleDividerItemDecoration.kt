package com.github.jaceed.decorations.linear

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class MiddleDividerItemDecoration(context: Context, orientation: Int) : LinearDecoration(context, orientation) {

    override fun onDrawVertical(canvas: Canvas, parent: RecyclerView) {
        divider?.let {
            canvas.save()
            val left: Int
            val right: Int
            if (parent.clipToPadding) {
                left = parent.paddingLeft
                right = parent.width - parent.paddingRight
                canvas.clipRect(left, parent.paddingTop, right, parent.height - parent.paddingBottom)
            } else {
                left = 0
                right = parent.width
            }
            val childCount = parent.adapter?.itemCount ?: return@let
            var pos: Int
            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)
                pos = parent.getChildAdapterPosition(child)
                if (pos == RecyclerView.NO_POSITION || pos == childCount - 1) {
                    continue
                }
                parent.getDecoratedBoundsWithMargins(child, bounds)
                val bottom = bounds.bottom + child.translationY.roundToInt()
                val top = bottom - it.intrinsicHeight
                it.setBounds(left, top, right, bottom)
                it.draw(canvas)
            }
            canvas.restore()
        }
    }

    override fun onDrawHorizontal(canvas: Canvas, parent: RecyclerView) {
        divider?.let {
            canvas.save()
            val top: Int
            val bottom: Int
            if (parent.clipToPadding) {
                top = parent.paddingTop
                bottom = parent.height - parent.paddingBottom
                canvas.clipRect(parent.paddingLeft, top, parent.width - parent.paddingRight, bottom)
            } else {
                top = 0
                bottom = parent.height
            }
            val childCount = parent.adapter?.itemCount ?: return@let
            var pos: Int
            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)
                pos = parent.getChildAdapterPosition(child)
                if (pos == RecyclerView.NO_POSITION || pos == childCount - 1) {
                    continue
                }
                parent.getDecoratedBoundsWithMargins(child, bounds)
                val right = bounds.right + child.translationX.roundToInt()
                val left = right - it.intrinsicWidth
                it.setBounds(left, top, right, bottom)
                it.draw(canvas)
            }
            canvas.restore()
        }

    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        divider?.let {
            parent.adapter?.itemCount?.let { itemCount ->
                if (orientation == VERTICAL) {
                    if (parent.getChildAdapterPosition(view) < itemCount - 1) outRect[0, 0, 0] = it.intrinsicHeight else outRect[0, 0, 0] = 0
                } else {
                    if (parent.getChildAdapterPosition(view) < itemCount - 1) outRect[0, 0, it.intrinsicWidth] = 0 else outRect[0, 0, 0] = 0
                }
            }
        } ?: run {
            outRect[0, 0, 0] = 0
        }
    }
}