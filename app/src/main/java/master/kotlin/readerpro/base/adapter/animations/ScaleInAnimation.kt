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
class ScaleInAnimation @JvmOverloads constructor(private val from: Float = DEFAULT_SCALE_FROM) :
    BaseAnimation {
    override fun getAnimators(view: View): Array<Animator> {
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", from, 1f)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", from, 1f)
        return arrayOf(scaleX, scaleY)
    }

    companion object {
        private const val DEFAULT_SCALE_FROM = 0.5F
    }


}