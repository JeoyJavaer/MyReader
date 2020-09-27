package master.kotlin.readerpro.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import master.kotlin.readerpro.R
import master.kotlin.readerpro.constant.Appconst
import master.kotlin.readerpro.constant.Theme
import master.kotlin.readerpro.lib.theme.ATH
import master.kotlin.readerpro.lib.theme.backgroundColor
import master.kotlin.readerpro.lib.theme.primaryColor
import master.kotlin.readerpro.utils.*

/**
 * Created on 2020/9/2.
 * Description
 * Others
 */
abstract class BaseActivity(
    private val layoutId: Int,
    private val fullsScreen: Boolean=true,
    private val theme: Theme = Theme.Auto,
    private val toolbarTheme: Theme = Theme.Auto,
    private val transparent: Boolean = false
) : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        if (Appconst.menuViewNames.contains(name) && parent?.parent is FrameLayout) {
            (parent.parent as View).setBackgroundColor(backgroundColor)
        }
        return super.onCreateView(parent, name, context, attrs)
    }


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        window.decorView.disableAutoFill()
        initTheme()
        setupSystemBar()
        super.onCreate(savedInstanceState, persistentState)
        setContentView(layoutId)
        onActivityCreated(savedInstanceState)
        observerLiveBus()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    abstract fun onActivityCreated(savedInstanceState: Bundle?)

    final override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return menu?.let {
            val bool: Boolean = onCompactCreateOptionsMenu(it)
            it.applyTint(this, toolbarTheme)
            bool
        } ?: super.onCreateOptionsMenu(menu)
    }

    override fun onMenuOpened(featureId: Int, menu: Menu): Boolean {
        menu?.let {
            menu.applyOpenTint(this)
            return super.onMenuOpened(featureId, menu)
        }
        return true
    }

    open fun onCompactCreateOptionsMenu(it: Menu): Boolean {
        return super.onCreateOptionsMenu(it)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item?.let {
            if (it.itemId == android.R.id.home) {
                supportFinishAfterTransition()
                return true
            }
        }
        return item != null && onCompactOptionsItemSelected(item)
    }

    open fun onCompactOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun initTheme() {
        when (theme) {
            Theme.Transparent ->setTheme(R.style.AppTheme_Transparent)
            Theme.Dark ->{
                setTheme(R.style.AppTheme_Dark)
                ATH.applyBackgroundTint(window.decorView)
            }
            Theme.Light -> {
                setTheme(R.style.AppTheme_Light)
                ATH.applyBackgroundTint(window.decorView)
            }

            else ->{
                if (ColorUtils.isColorLight(primaryColor)) {
                    setTheme(R.style.AppTheme_Light)
                } else {
                    setTheme(R.style.AppTheme_Dark)
                }
                ATH.applyBackgroundTint(window.decorView)
            }
        }
    }


    private fun setupSystemBar() {
        if (fullsScreen) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
        ATH.setStatusBarColorAuto(this,fullsScreen)
        if (toolbarTheme == Theme.Dark) {
            ATH.setLightStatusBar(window,false)
        }else if (toolbarTheme == Theme.Light) {
            ATH.setLightStatusBar(window,true)
        }
        updateNavigationBarColor()

    }


    open fun updateNavigationBarColor() {
        ATH.setNavigationBarColorAuto(this)
    }

    open fun observerLiveBus() {}

    override fun finish() {
        currentFocus?.hideSoftInput()
        super.finish()
    }

}
