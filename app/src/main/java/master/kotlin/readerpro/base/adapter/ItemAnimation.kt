package master.kotlin.readerpro.base.adapter

import android.view.animation.Interpolator
import master.kotlin.readerpro.base.adapter.animations.*

/**
 * Created on 2020/12/8.
 * Description
 * Others
 */
class ItemAnimation private constructor() {

    var itemAnimEnable = false
    var itemAnimFirstOnly = true
    var itemAnimation: BaseAnimation? = null
    var itemAnimInterpolator: Interpolator? = null
    var itemAnimDuration: Long = 300L
    var itemAnimStartPosition: Int = -1

    companion object {
        const val NONE: Int = 0x00000000
        const val FADE_IN: Int = 0x00000001
        const val SCALE_IN: Int = 0x00000002
        const val BOTTOM_SLIDE_IN: Int = 0x00000003
        const val LEFT_SLIDE_IN: Int = 0x00000004
        const val RIGHT_SLIDE_IN: Int = 0x00000005


        fun  create():ItemAnimation{
            return  ItemAnimation()
        }

    }

    fun interpolator(interpolator: Interpolator): ItemAnimation {
        itemAnimInterpolator = interpolator
        return this
    }

    fun duration(duration: Long): ItemAnimation {
        itemAnimDuration = duration
        return this
    }


    fun startPosition(position: Int): ItemAnimation {
        itemAnimStartPosition = position
        return this
    }

    fun animation(animationType: Int = NONE,animation:BaseAnimation ?=null): ItemAnimation {
        if(animation !=null){
            itemAnimation =animation
        }else{
            when(animationType){
                FADE_IN -> itemAnimation= AlphaInAnimation()
                SCALE_IN ->itemAnimation= ScaleInAnimation()
                BOTTOM_SLIDE_IN -> itemAnimation=SlideInBottomAnimation()
                LEFT_SLIDE_IN ->itemAnimation=SlideInLeftAnimation()
                RIGHT_SLIDE_IN ->itemAnimation=SlideInRightAnimation()
            }
        }

        return this
    }

    fun enabled(enabled: Boolean): ItemAnimation {
        itemAnimEnable = enabled
        return this
    }

    fun firstOnly(firstOnly: Boolean): ItemAnimation {
        itemAnimFirstOnly = firstOnly
        return this
    }


}