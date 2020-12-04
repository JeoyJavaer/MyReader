package master.kotlin.readerpro.ui.widget.prefs

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.preference.PreferenceViewHolder
import master.kotlin.readerpro.R
import master.kotlin.readerpro.lib.theme.accentColor
import master.kotlin.readerpro.lib.theme.bottomBackground
import master.kotlin.readerpro.lib.theme.getPrimaryTextColor
import master.kotlin.readerpro.lib.theme.getSecondaryTextColor
import master.kotlin.readerpro.utils.ColorUtils
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.sdk27.listeners.onLongClick
import kotlin.math.roundToInt

/**
 * Created on 2020/12/4.
 * Description
 * Others
 */
class Preference(context: Context, attrs: AttributeSet) : androidx.preference.Preference(context) {

    var onLongClick: (() -> Unit)? = null
    private val isBottomBackground: Boolean

    init {
        layoutResource = R.layout.view_preference
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.Preference)
        isBottomBackground = typeArray.getBoolean(R.styleable.Preference_isBottomBackground, false)
        typeArray.recycle()
    }

    companion object {
        fun <T : View> bindView(
            context: Context,
            it: PreferenceViewHolder?,
            icon: Drawable?,
            title: CharSequence?,
            summary: CharSequence?,
            weightLayoutRes: Int? = null,
            viewId: Int? = null,
            weightWidth: Int = 0,
            weightHeight: Int = 0,
            isBottomBackground: Boolean = false
        ): T? {

            if (it == null) return null
            val tvTitle = it.findViewById(R.id.preference_title) as TextView
            tvTitle.text = title

            tvTitle.isVisible = title != null && title.isNotEmpty()

            val tvSummary = it.findViewById(R.id.preference_desc) as TextView
            tvSummary?.let {
                it.text = summary
                it.isGone = summary.isNullOrEmpty()
            }

            if (isBottomBackground && !tvTitle.isInEditMode) {
                val isLight = ColorUtils.isColorLight(context.bottomBackground)
                val pTextColor = context.getPrimaryTextColor(isLight)
                tvTitle.setTextColor(pTextColor)
                val sTextColor = context.getSecondaryTextColor(isLight)
                tvSummary?.setTextColor(sTextColor)
            }

            val iconView =it.findViewById(R.id.preference_icon)
            if (iconView is ImageView){
                iconView.isVisible= icon != null
                iconView.setImageDrawable(icon)
                iconView.setColorFilter(context.accentColor)

            }

            if (weightLayoutRes != null && weightLayoutRes != 0 && viewId != null && viewId != 0) {
                val lay = it.findViewById(R.id.preference_widget)
                if (lay is FrameLayout) {
                    var needRequestLayout = false
                    var v = it.itemView.findViewById<T>(viewId)
                    if (v == null) {
                        val inflater: LayoutInflater = context.layoutInflater
                        val childView = inflater.inflate(weightLayoutRes, null)
                        lay.removeAllViews()
                        lay.addView(childView)
                        lay.isVisible = true
                        v = lay.findViewById(viewId)
                    } else
                        needRequestLayout = true

                    if (weightWidth > 0 || weightHeight > 0) {
                        val lp = lay.layoutParams
                        if (weightHeight > 0)
                            lp.height =
                                (context.resources.displayMetrics.density * weightHeight).roundToInt()
                        if (weightWidth > 0)
                            lp.width =
                                (context.resources.displayMetrics.density * weightWidth).roundToInt()
                        lay.layoutParams = lp
                    } else if (needRequestLayout)
                        v.requestLayout()

                    return v
                }
            }
            return null
        }
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        bindView<View>(
            context,
            holder,
            icon,
            title,
            summary,
            isBottomBackground = isBottomBackground
        )
        super.onBindViewHolder(holder)
        holder?.itemView?.onLongClick {
            onLongClick?.invoke()
            true
        }
    }
}