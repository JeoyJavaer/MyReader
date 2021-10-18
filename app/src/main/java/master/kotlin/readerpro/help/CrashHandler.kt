package master.kotlin.readerpro.help

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.widget.Toast
import java.text.SimpleDateFormat

/**
 * Created on 2021/10/18.
 * Description
 * Others
 */
class CrashHandler : Thread.UncaughtExceptionHandler {
    private val TAG = this.javaClass.simpleName

    private var mDefaultHandler: Thread.UncaughtExceptionHandler? = null

    private var mContext: Context? = null

    private val paramsMap = HashMap<String, String>()

    @SuppressLint("SimpleDateFormat")
    private val format = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")


    fun init(context: Context) {
        mContext = context
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }


    override fun uncaughtException(t: Thread, e: Throwable) {
//        TTSReadAloudService.clearTTS()
        handleException(e)
        mDefaultHandler?.uncaughtException(t, e)
    }

    fun handleException(ex: Throwable) {
        if (ex == null) return
        collectionDeviceInfo(mContext!!)
        addCustomInfo()
        kotlin.runCatching {
           android.os.Handler(Looper.getMainLooper()).post{
                   Toast.makeText(
                       mContext,
                       ex.message,
                       Toast.LENGTH_LONG
                   ).show()

           }
        }

        saveCrashInfo2File(ex)
    }

    private fun collectionDeviceInfo(ctx: Context) {

    }

    private fun addCustomInfo() {

    }

    fun  saveCrashInfo2File(ex: Throwable){

    }
}