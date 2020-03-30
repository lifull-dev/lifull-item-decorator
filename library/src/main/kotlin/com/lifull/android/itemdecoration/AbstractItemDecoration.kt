package com.lifull.android.itemdecoration

import android.graphics.Canvas
import android.graphics.Rect
import android.view.*
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * [androidx.recyclerview.widget.RecyclerView.ItemDecoration]
 *
 * @param resId                 layout ResourceId
 * @param type                  draw type. [Type.HEADER] or [Type.FOOTER]
 * @param repeat                true: all element satisfying condition, false: first element satisfying the condition
 * @param condition             drawing condition
 * @param onBindItemDecoration  callback bind item decoration
 * @author inuko
 */
abstract class AbstractItemDecoration(
    @LayoutRes private val resId: Int,
    private val type: Type = Type.HEADER,
    private val repeat: Boolean? = false,
    private val condition: ((child: View, parent: RecyclerView) -> Boolean)? = null,
    private val onBindItemDecoration: ((itemView: View, child: View, parent: RecyclerView) -> Unit)? = null
) : RecyclerView.ItemDecoration() {

    /** itemView */
    private lateinit var itemView: View

    /**
     * @inheritDoc
     */
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val condition = condition ?: when (type) {
            Type.HEADER -> ::isHeader
            Type.FOOTER -> ::isFooter
        }
        drawItemView(c, parent, condition)
    }

    /**
     * @inheritDoc
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        // initialize of itemView
        initializeItemView(view, parent)
        // check the type
        val condition = condition ?: when (type) {
            Type.HEADER -> ::isHeader
            Type.FOOTER -> ::isFooter
        }
        // set offsets
        if (condition(view, parent)) {
            val top = when (type) {
                Type.HEADER -> itemView.measuredHeight
                Type.FOOTER -> 0
            }
            val bottom = when (type) {
                Type.HEADER -> 0
                Type.FOOTER -> itemView.measuredHeight
            }
            outRect.set(0, top, 0, bottom)
        } else {
            outRect.setEmpty()
        }
    }

    /**
     * Initialize of [itemView]
     *
     * @param view [View]
     * @param parent [RecyclerView]
     */
    private fun initializeItemView(
        view: View,
        parent: RecyclerView
    ) {
        if (::itemView.isInitialized) {
            return
        }
        val v: View = LayoutInflater.from(parent.context).inflate(resId, parent, false)
        val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)
        val childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
            parent.paddingLeft + parent.paddingRight,
            view.layoutParams.width)
        val childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
            parent.paddingTop + parent.paddingBottom,
            v.layoutParams.height)
        v.measure(childWidth, childHeight)
        v.layout(0, 0, v.measuredWidth, v.measuredHeight)
        itemView = v
    }

    /**
     * Draw [itemView]
     *
     * @param c [Canvas]
     * @param parent [RecyclerView]
     * @param condition Drawing condition
     */
    private fun drawItemView(
        c: Canvas,
        parent: RecyclerView,
        condition: ((child: View, parent: RecyclerView) -> Boolean)
    ) {
        val adapter = parent.adapter ?: return
        val itemCount = adapter.itemCount
        if (itemCount <= 0) {
            return
        }

        val repeat = repeat ?: false
        val childCount = parent.childCount - 1
        for (i in 0..childCount) {
            val view = parent.getChildAt(i)
            if (condition(view, parent)) {
                onBindItemDecoration?.invoke(itemView, view, parent)
                c.save()
                val top = when (type) {
                    Type.HEADER -> {
                        view.top - itemView.measuredHeight
                    }
                    Type.FOOTER -> {
                        view.top + itemView.measuredHeight
                    }
                }
                c.translate(0f, top.toFloat())
                itemView.draw(c)
                c.restore()

                if (repeat.not()) {
                    break
                }
            }
        }
    }

    /**
     * Check a Header
     *
     * @param child [View]
     * @param parent [RecyclerView]
     * @return Return true if header
     */
    private fun isHeader(
        child: View,
        parent: RecyclerView
    ): Boolean {
        val position = parent.getChildAdapterPosition(child)
        val adapter = parent.adapter ?: return false
        val itemCount = adapter.itemCount
        if (itemCount == 0) {
            return false
        }
        return position == 0
    }

    /**
     * Check a Footer
     *
     * @param child [View]
     * @param parent [RecyclerView]
     * @return Return true if footer
     */
    private fun isFooter(
        child: View,
        parent: RecyclerView
    ): Boolean {
        val position = parent.getChildAdapterPosition(child)
        val adapter = parent.adapter ?: return false
        val itemCount = adapter.itemCount
        if (itemCount == 0) {
            return false
        }
        return itemCount - 1 == position
    }

    /**
     * Draw type
     */
    enum class Type constructor(val type: Int) {
        HEADER(1),
        FOOTER(2);
    }
}