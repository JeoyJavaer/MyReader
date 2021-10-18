package master.kotlin.readerpro.base

import android.app.Application
import android.content.Context
import androidx.annotation.CallSuper
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.*
import master.kotlin.readerpro.App
import master.kotlin.readerpro.help.coroutine.Coroutine
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import kotlin.coroutines.CoroutineContext

/**
 * Created on 2020/9/2.
 * Description
 * Others
 */
open class BaseViewModel (app:Application):AndroidViewModel(app),CoroutineScope by MainScope(),AnkoLogger{
    val context:Context by lazy { this.getApplication<App>() }

    fun <T>execute(scope: CoroutineScope=this,context: CoroutineContext=Dispatchers.IO,block:suspend CoroutineScope.() ->T): Coroutine<T> {
        return  Coroutine.async(scope, context, block)
    }

    fun <R> submit(scope: CoroutineScope=this,context: CoroutineContext=Dispatchers.IO,block:suspend  CoroutineScope.() -> Deferred<R>):Coroutine<R>{
        return  Coroutine.async(scope, context){block().await()}
    }


    @CallSuper
    override fun onCleared() {
        super.onCleared()
        cancel()
    }

    open fun  toast(message:Int){
            launch {
                context.toast(message)
            }
    }

    open fun  toast(message:CharSequence){
        launch {
            context.toast(message)
        }
    }

    open fun longToast(message: Int) {
        launch {
            context.toast(message)
        }
    }

    open fun longToast(message: CharSequence?) {
        launch {
            context.toast(message ?: toString())
        }
    }




}