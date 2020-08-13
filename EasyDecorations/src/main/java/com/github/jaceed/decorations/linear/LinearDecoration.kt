package com.github.jaceed.decorations.linear

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.LinearLayout
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

/**
 * @param context
 * @param orientation Divider orientation. Should be [HORIZONTAL] or [VERTICAL].
 */
abstract class LinearDecoration(context: Context, orientation: Int): RecyclerView.ItemDecoration() {

    var debug = false

    protected var divider: Drawable?

    /**
     * Current orientation. Either [HORIZONTAL] or [VERTICAL].
     */
    private var _orientation = 0
    protected val bounds = Rect()

    protected val orientation get() = _orientation

    init {
        val a = context.obtainStyledAttributes(ATTRS)
        divider = a.getDrawable(0)
        if (divider == null) {
            Log.w(TAG, "@android:attr/listDivider was not set in the theme used for this "
                    + "DividerItemDecoration. Please set that attribute all call setDrawable()")
        }
        a.recycle()
        setOrientation(orientation)
    }

    /**
     * Sets the orientation for this divider. This should be called if
     * [RecyclerView.LayoutManager] changes orientation.
     *
     * @param orientation [HORIZONTAL] or [VERTICAL]
     */
    fun setOrientation(orientation: Int) {
        require(!(orientation != HORIZONTAL && orientation != VERTICAL)) { "Invalid orientation. It should be either HORIZONTAL or VERTICAL" }
        _orientation = orientation
    }

    /**
     * Sets the [Drawable] for this divider.
     *
     * @param drawable Drawable that should be used as a divider.
     */
    fun setDrawable(@NonNull drawable: Drawable) {
        divider = drawable
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null || divider == null) {
            return
        }
        if (orientation == VERTICAL) {
            onDrawVertical(c, parent)
        } else {
            onDrawHorizontal(c, parent)
        }
    }

    protected abstract fun onDrawVertical(canvas: Canvas, parent: RecyclerView)

    protected abstract fun onDrawHorizontal(canvas: Canvas, parent: RecyclerView)

    companion object {
        const val HORIZONTAL = LinearLayout.HORIZONTAL
        const val VERTICAL = LinearLayout.VERTICAL
        private const val TAG = "LinearDecoration"
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }

}