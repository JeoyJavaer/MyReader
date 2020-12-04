package master.kotlin.readerpro.help.coroutine

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created on 2020/12/4.
 * Description
 * Others
 */
class Coroutine<T>(
    val scope: CoroutineScope,
    context: CoroutineContext = Dispatchers.IO,
    block: suspend CoroutineScope.() -> T
) {

    companion object {
        val DEFAULT = MainScope()
        fun <T> async(
            scope: CoroutineScope = DEFAULT,
            context: CoroutineContext = Dispatchers.IO,
            block: suspend CoroutineScope.() -> T
        ): Coroutine<T> {
            return Coroutine(scope, context, block)
        }
    }

    private val job: Job
    private var start: VoidCallback? = null
    private var success: Callback<T>? = null
    private var error: Callback<Throwable>? = null
    private var finally: VoidCallback? = null
    private var cancel: VoidCallback? = null

    private var timeMillis: Long? = null
    private var errorReturn: Result<T>? = null

    init {
        this.job = executeInternal(context, block)
    }

    val isCanceled: Boolean
        get() = job.isCancelled

    val isActive: Boolean
        get() = job.isActive
    val isComplete: Boolean
        get() = job.isCompleted

    fun timeout(timeMillis: () -> Long): Coroutine<T> {
        this.timeMillis = timeMillis()
        return this@Coroutine
    }

    fun timeout(timeMillis: Long): Coroutine<T> {
        this.timeMillis = timeMillis
        return this@Coroutine
    }

    fun onErrorReturn(value: () -> T?): Coroutine<T> {
        this.errorReturn = Result(value())
        return this@Coroutine
    }

    fun onErrorReturn(value: T?): Coroutine<T> {
        this.errorReturn = Result(value)
        return this@Coroutine
    }

    fun conStart(
        context: CoroutineContext? = null,
        block: (suspend CoroutineScope.() -> Unit)
    ): Coroutine<T> {
        this.start = VoidCallback(context, block)
        return this@Coroutine
    }

    fun onSuccess(
        context: CoroutineContext? = null,
        block: suspend CoroutineScope.(T) -> Unit
    ): Coroutine<T> {
        this.success = Callback(context, block)
        return this@Coroutine
    }


    fun onError(
        context: CoroutineContext? = null,
        block: suspend CoroutineScope.(Throwable) -> Unit
    ): Coroutine<T> {
        this.error = Callback(context, block)
        return this@Coroutine
    }

    fun  onFinally(
        context: CoroutineContext?=null,
        block: suspend CoroutineScope.() -> Unit
    ):Coroutine<T>{
        this.finally= VoidCallback(context, block)
        return  this@Coroutine
    }

    fun onCancel(
        context: CoroutineContext?=null,
        block: suspend CoroutineScope.() -> Unit
    ):Coroutine<T>{
        this.cancel = VoidCallback(context, block)
        return  this@Coroutine
    }

    fun cancel(cause:CancellationException ?=null){
        job.cancel(cause)
        cancel?.let {
            MainScope().launch {
                if (null==it.context){
                    it.block.invoke(scope)
                }else{
                    withContext(scope.coroutineContext.plus(it.context)){
                        it.block.invoke(this)
                    }
                }
            }
        }
    }

    fun  invokeOnCompletion(handler: CompletionHandler):DisposableHandle{
        return  job.invokeOnCompletion(handler)
    }

    private fun  executeInternal(
        context: CoroutineContext,
        block: suspend CoroutineScope.() -> T
    ):Job{
        return  scope.plus(Dispatchers.Main).launch {
            try {
                start?.let { dispatchVoidCallback(this,it) }
            }catch (e:Throwable){

            }finally {

            }
        }
    }

    private  suspend inline fun dispatchVoidCallback(scope: CoroutineScope,callback: VoidCallback) {
        if(null ==callback.context){
            callback.block.invoke(scope)
        }else{
            withContext(scope.coroutineContext.plus(callback.context)){
                callback.block.invoke(this)
            }
        }
    }

    private  suspend  inline fun <R> dispatchCallback(
        scope: CoroutineScope,
        value: R,
        callback:Callback<R>
    ){
        if (!scope.isActive)  return
        if (null == callback.context){
            callback.block.invoke(scope,value)
        }else{
            withContext(scope.coroutineContext.plus(callback.context)){
                callback.block.invoke(scope,value)
            }
        }
    }


    private suspend inline fun executeBlock(
        scope: CoroutineScope,
        context: CoroutineContext,
        timeMillis: Long,
        noinline block: suspend CoroutineScope.() -> T
    ): T {
        return withContext(scope.coroutineContext.plus(context)) {
            if (timeMillis > 0L) withTimeout(timeMillis) {
                block()
            } else block()
        }
    }


    private data class Result<out T>(val value: T?)

    private inner class VoidCallback(
        val context: CoroutineContext?,
        val block: suspend CoroutineScope.() -> Unit
    )

    private inner class Callback<VALUE>(
        val context: CoroutineContext?,
        val block: suspend CoroutineScope.(VALUE) -> Unit
    )
}