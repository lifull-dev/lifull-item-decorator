package com.lifull.android.itemdecoration

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Header
 *
 * @author inuko
 */
class HeaderItemDecoration : AbstractItemDecoration {

    constructor(
        @LayoutRes resId: Int
    ): this(
        resId = resId,
        onBindItemDecoration = null
    )

    constructor(
        @LayoutRes resId: Int,
        onBindItemDecoration: ((itemView: View, child: View, parent: RecyclerView) -> Unit)? = null
    ): super(
        resId = resId,
        type = Type.HEADER,
        repeat = false,
        condition = null,
        onBindItemDecoration = onBindItemDecoration
    )
}