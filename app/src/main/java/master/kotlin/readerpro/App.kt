package master.kotlin.readerpro

import android.app.Application
import androidx.multidex.MultiDexApplication

/**
 * Created on 2020/9/2.
 * Description
 * Others
 */

class App :Application() {

    companion object{
        @JvmStatic
        lateinit var  INSTANCE:App
            private  set



    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE =this
    }
}