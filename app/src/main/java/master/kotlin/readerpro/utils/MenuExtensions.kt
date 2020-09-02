package master.kotlin.readerpro.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.Menu
import androidx.appcompat.view.menu.MenuBuilder
import master.kotlin.readerpro.R
import master.kotlin.readerpro.constant.Theme


/**
 * Created on 2020/9/2.
 * Description
 * Others
 */
@SuppressLint("RestrictedApi")
fun Menu.applyTint(context: Context, theme: Theme=Theme.Auto):Menu = this.let{ menu ->

    if (menu is MenuBuilder){
        menu.setOptionalIconsVisible(true)
    }
    context.getCompatColor(R.color.primaryText)


    return menu
}

fun Menu.applyOnTint(context: Context) {

}