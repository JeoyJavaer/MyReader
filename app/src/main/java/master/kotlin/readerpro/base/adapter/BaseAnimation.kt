package master.kotlin.readerpro.base.adapter

import android.animation.Animator
import android.view.View

/**
 * Created on 2020/12/8.
 * Description
 * Others
 */
interface BaseAnimation {
    fun  getAnimators(view:View):Array<Animator>

}