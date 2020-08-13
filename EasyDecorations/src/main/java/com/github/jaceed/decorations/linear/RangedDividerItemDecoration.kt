package com.github.jaceed.decorations.linear

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

/**
 * @param startIndex The start index of the decoration range, included
 * @param endIndex The end index of the decoration range, excluded
 * @param frontEnabled Put decoration front or not, default false as normal
 */
class RangedDividerItemDecoration @JvmOverloads constructor(
    context: Context,
    orientation: Int,
    private var startIndex: Int = -1,
    private var endIndex: Int = -1,
    private var frontEnabled: Boolean = false
) : LinearDecoration(context, orientation) {

    fun setFrontEnabled(enabled: Boolean) {
        frontEnabled = enabled
    }

    override fun onDrawVertical(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        divider?.let {
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
            val childCount = parent.childCount
            val start = startIndex.coerceAtLeast(0)
            val itemCount = parent.adapter?.itemCount ?: return@let
            val end = if (endIndex < 0) itemCount else endIndex.coerceAtMost(itemCount)
            if (debug) Log.d(TAG, "draw: [$start, $end)")
            var pos: Int
            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)
                pos = parent.getChildAdapterPosition(child)
                if (debug) Log.d(TAG, "pos: $pos")
                if (pos == RecyclerView.NO_POSITION || pos !in start until end) {
                    continue
                }
                parent.getDecoratedBoundsWithMargins(child, bounds)
                if (!frontEnabled) {
                    val bottom = bounds.bottom + child.translationY.roundToInt()
                    val top = bottom - it.intrinsicHeight
                    if (debug) Log.d(TAG, "$left, $top, $right, $bottom")
                    it.setBounds(left, top, right, bottom)
                } else {
                    val top = bounds.top - child.translationY.roundToInt()
                    val bottom = top + it.intrinsicHeight
                    it.setBounds(left, top, right, bottom)
                }
                it.draw(canvas)
            }
        }

        canvas.restore()
    }

    override fun onDrawHorizontal(canvas: Canvas, parent: RecyclerView) {
        divider?.let {
            canvas.save()
            val top: Int
            val bottom: Int
            if (parent.clipToPadding) {
                top = parent.paddingTop
                bottom = parent.height - parent.paddingBottom
                canvas.clipRect(
                    parent.paddingLeft, top,
                    parent.width - parent.paddingRight, bottom
                )
            } else {
                top = 0
                bottom = parent.height
            }
            val childCount = parent.childCount
            val start = startIndex.coerceAtLeast(0)
            val itemCount = parent.adapter?.itemCount ?: return@let
            val end = if (endIndex < 0) itemCount else endIndex.coerceAtMost(itemCount)
            var pos: Int
            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)
                pos = parent.getChildAdapterPosition(child)
                if (debug) Log.d(TAG, "pos: $pos")
                if (pos == RecyclerView.NO_POSITION || pos !in start until end) {
                    continue
                }
                parent.getDecoratedBoundsWithMargins(child, bounds)
                if (!frontEnabled) {
                    val right = bounds.right + child.translationX.roundToInt()
                    val left = right - it.intrinsicWidth
                    it.setBounds(left, top, right, bottom)
                } else {
                    val left = bounds.left - child.translationY.roundToInt()
                    val right = left + it.intrinsicWidth
                    it.setBounds(left, top, right, bottom)
                }
                it.draw(canvas)
            }
            canvas.restore()
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        divider?.let {
            parent.adapter?.itemCount?.let { itemCount ->
                val cur = parent.getChildAdapterPosition(view)
                val start = startIndex.coerceAtLeast(0)
                val end = if (endIndex < 0) itemCount else endIndex.coerceAtMost(itemCount)
                if (orientation == VERTICAL) {
                    if (cur in start until end) outRect[0, if (frontEnabled) it.intrinsicHeight else 0, 0] = if (frontEnabled) 0 else it.intrinsicHeight else outRect[0, 0, 0] = 0
                } else {
                    if (cur in start until end) outRect[if (frontEnabled) it.intrinsicWidth else 0, 0, if (frontEnabled) 0 else it.intrinsicWidth] = 0
                }
            }
        } ?: run {
            outRect[0, 0, 0] = 0
        }
    }

    companion object {
        private const val TAG = "RangedDecor"
    }

}