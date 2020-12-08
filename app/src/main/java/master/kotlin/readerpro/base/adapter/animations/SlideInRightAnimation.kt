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
class SlideInRightAnimation @JvmOverloads constructor():BaseAnimation{
    override fun getAnimators(view: View): Array<Animator> = arrayOf(ObjectAnimator.ofFloat(view,"translationX",view.rootView.width.toFloat(),0f))
}