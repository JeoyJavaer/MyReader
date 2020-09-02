package master.kotlin.readerpro.constant

import android.graphics.Color
import master.kotlin.readerpro.help.Appconfig
import master.kotlin.readerpro.utils.ColorUtils

/**
 * Created on 2020/9/2.
 * Description
 * Others
 */
enum class Theme {
    Dark,Light,Auto,Transparent;

    companion object{
        fun  getTheme():Theme{
            return if (Appconfig.isNightTheme){
                Dark
            }else Light
        }


        fun  getTheme(backgroundColor: Int):Theme{
            return  if (ColorUtils.isColorLight(backgroundColor)){
                Light
            }else{
                Dark
            }
        }
    }


}