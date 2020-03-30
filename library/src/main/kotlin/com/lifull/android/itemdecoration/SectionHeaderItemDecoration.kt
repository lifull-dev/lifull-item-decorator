package com.lifull.android.itemdecoration

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Section Header
 *
 * @author inuko
 */
class SectionHeaderItemDecoration : AbstractItemDecoration {

    constructor(
        @LayoutRes resId: Int,
        condition: ((child: View, parent: RecyclerView) -> Boolean)?
    ): super(
        resId,
        Type.HEADER,
        true,
        condition,
        null)

    constructor(
        @LayoutRes resId: Int,
        condition: ((child: View, parent: RecyclerView) -> Boolean)?,
        onBindItemDecoration: ((itemView: View, child: View, parent: RecyclerView) -> Unit)? = null
    ): super(
        resId,
        Type.HEADER,
        true,
        condition,
        onBindItemDecoration
    )
}