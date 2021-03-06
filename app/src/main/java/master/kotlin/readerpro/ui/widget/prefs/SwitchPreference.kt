package io.legado.app.ui.widget.prefs

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SwitchCompat
import androidx.preference.PreferenceViewHolder
import androidx.preference.SwitchPreferenceCompat
import master.kotlin.readerpro.R
import master.kotlin.readerpro.lib.theme.ATH
import master.kotlin.readerpro.lib.theme.accentColor
import master.kotlin.readerpro.ui.widget.prefs.Preference


class SwitchPreference(context: Context, attrs: AttributeSet) :
    SwitchPreferenceCompat(context, attrs) {

    private val isBottomBackground: Boolean

    init {
        layoutResource = R.layout.view_preference
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Preference)
        isBottomBackground = typedArray.getBoolean(R.styleable.Preference_isBottomBackground, false)
        typedArray.recycle()
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        val v = Preference.bindView<SwitchCompat>(
            context,
            holder,
            icon,
            title,
            summary,
            widgetLayoutResource,
            R.id.switchWidget,
            isBottomBackground = isBottomBackground
        )
        if (v is SwitchCompat && !v.isInEditMode) {
            ATH.setTint(v, context.accentColor)
        }
        super.onBindViewHolder(holder)
    }

}
