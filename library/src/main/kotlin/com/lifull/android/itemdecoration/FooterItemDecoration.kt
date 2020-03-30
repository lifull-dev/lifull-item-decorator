package com.lifull.android.itemdecoration

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Footer
 *
 * @author inuko
 */
class FooterItemDecoration : AbstractItemDecoration {

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
        type = Type.FOOTER,
        repeat = false,
        condition = null,
        onBindItemDecoration = onBindItemDecoration
    )
}