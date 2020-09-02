package master.kotlin.readerpro.utils

import android.graphics.Color
import androidx.annotation.ColorInt

/**
 * Created on 2020/9/2.
 * Description
 * Others
 */
@Suppress("unused")
object ColorUtils{
    fun  isColorLight(@ColorInt color: Int):Boolean{
        val darkness =
            1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
        return darkness < 0.4
    }
}

