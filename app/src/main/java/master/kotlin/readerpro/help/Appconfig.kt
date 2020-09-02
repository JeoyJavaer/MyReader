package master.kotlin.readerpro.help

import android.content.Context
import master.kotlin.readerpro.App
import master.kotlin.readerpro.constant.PreferKey
import master.kotlin.readerpro.utils.getPrefString
import master.kotlin.readerpro.utils.putPrefString
import master.kotlin.readerpro.utils.sysIsDarkMode

/**
 * Created on 2020/9/2.
 * Description
 * Others decade
 */
object Appconfig {

    fun  isNightTheme(context: Context):Boolean{
        return when(context.getPrefString(PreferKey.themeMode,"0")){
            "1" -> false
            "2" -> true
            "3"-> false
            else -> context.sysIsDarkMode()
        }
    }

    var isNightTheme:Boolean
        get() = isNightTheme(App.INSTANCE)
        set(value) {
            if (value){
                App.INSTANCE.putPrefString(PreferKey.themeMode,"2")
            }else{
                App.INSTANCE.putPrefString(PreferKey.themeMode,"1")
            }
        }
}