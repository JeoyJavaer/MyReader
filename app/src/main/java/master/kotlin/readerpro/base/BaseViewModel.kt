package master.kotlin.readerpro.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import master.kotlin.readerpro.App
import master.kotlin.readerpro.help.coroutine.Coroutine
import org.jetbrains.anko.AnkoLogger
import kotlin.coroutines.CoroutineContext

/**
 * Created on 2020/9/2.
 * Description
 * Others
 */
open class BaseViewModel (app:Application):AndroidViewModel(app),CoroutineScope by MainScope(),AnkoLogger{
    val context:Context by lazy { this.getApplication<App>() }

    fun <T>execute(scope: CoroutineScope=this,context: CoroutineContext=Dispatchers.IO,block:suspend CoroutineScope.() ->T): Coroutine<T> {

    }





}