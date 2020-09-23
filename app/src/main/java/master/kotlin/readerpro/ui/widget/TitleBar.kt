package master.kotlin.readerpro.ui.widget

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.Menu
import androidx.appcompat.widget.Toolbar

import com.google.android.material.appbar.AppBarLayout
import master.kotlin.readerpro.R

/**
 * Created on 2020/9/8.
 * Description
 * Others
 */
class TitleBar(context: Context, attrs: AttributeSet?) : AppBarLayout(context, attrs) {
    val toolbar: Toolbar
    val menu: Menu
        get() = toolbar.menu

    var title: CharSequence?
        get() = toolbar.title
        set(value) {
            toolbar.title = value
        }
    var subTitle: CharSequence?
        get() = toolbar.subtitle
        set(value) {
            toolbar.subtitle = value
        }

    private val displayHomeAsUp: Boolean
    private val navigationIconTint: ColorStateList?
    private val navigationIconTintMode: Int
    private val attachToActivity: Boolean

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.TitleBar, R.attr.titleBarStyle, 0)
        navigationIconTint = a.getColorStateList(R.styleable.TitleBar_navigationIconTint)
        navigationIconTintMode = a.getInt(R.styleable.TitleBar_navigationIconTintMode,9)
        attachToActivity = a.getBoolean(R.styleable.TitleBar_attachToActivity,true)
        displayHomeAsUp = a.getBoolean(R.styleable.TitleBar_displayHomeAsUp,true)


        toolbar = findViewById(R.id.toolbar)

    }

}






