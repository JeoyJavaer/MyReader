package master.kotlin.readerpro.base

import androidx.lifecycle.ViewModel

/**
 * Created on 2020/9/8.
 * Description
 * Others
 */
abstract class VMBaseFragment<VM:ViewModel>(layoutId:Int):BaseFragment(layoutId) {
    protected abstract val viewModel:VM
}