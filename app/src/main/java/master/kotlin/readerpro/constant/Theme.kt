package master.kotlin.readerpro.constant

import master.kotlin.readerpro.help.AppConfig
import master.kotlin.readerpro.utils.ColorUtils

/**
 * Created on 2020/9/2.
 * Description
 *
 */
enum class Theme {
    Dark,Light,Auto,Transparent;

    companion object{
        fun  getTheme():Theme{
            return if (AppConfig.isNightTheme){
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