package master.kotlin.readerpro.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import master.kotlin.readerpro.lib.theme.backgroundColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import master.kotlin.readerpro.constant.Appconst
import master.kotlin.readerpro.constant.Theme
import master.kotlin.readerpro.utils.disableAutoFill

/**
 * Created on 2020/9/2.
 * Description
 * Others
 */
abstract class BaseActivity(
    private val layoutId: Int,
    private val fullsScreen: Boolean,
    private val theme: Theme = Theme.Auto,
    private val toolbarTheme: Theme = Theme.Auto,
    private val transparent: Boolean = false
) : AppCompatActivity() ,CoroutineScope by MainScope(){

    override fun onCreateView(parent:View?,name: String, context: Context, attrs: AttributeSet): View? {
        if (Appconst.menuViewNames.contains(name)&& parent?.parent is FrameLayout){
            (parent.parent as View).setBackgroundColor(backgroundColor)
        }
        return  super.onCreateView(parent, name, context, attrs)
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

    private fun observerLiveBus() {


    }

    private fun onActivityCreated(savedInstanceState: Bundle?) {

    }

    private fun setupSystemBar() {


    }

    private fun initTheme() {


    }
}