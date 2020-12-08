package master.kotlin.readerpro.base.adapter.animations

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import master.kotlin.readerpro.base.adapter.BaseAnimation

/**
 * Created on 2020/12/8.
 * Description
 * Others
 */
class SlideInBottomAnimation :BaseAnimation{
    override fun getAnimators(view: View): Array<Animator> = arrayOf(ObjectAnimator.ofFloat(view,"translationY",view.measuredHeight.toFloat(),0f))
}