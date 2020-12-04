package master.kotlin.readerpro.utils

import android.content.res.Resources

/**
 * Created on 2020/12/4.
 * Description
 * Others
 */
val Float.dp: Float
    get() = android.util.TypedValue.applyDimension(
        android.util.TypedValue.COMPLEX_UNIT_DIP,this,Resources.getSystem().displayMetrics
    )

val Float.sp: Float
    get() = android.util.TypedValue.applyDimension(
        android.util.TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics
    )