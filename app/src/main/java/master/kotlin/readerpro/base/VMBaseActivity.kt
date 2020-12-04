package master.kotlin.readerpro.base

import androidx.lifecycle.ViewModel
import master.kotlin.readerpro.constant.Theme

/**
 * Created on 2020/9/8.
 * Description
 * Others
 */
abstract class VMBaseActivity<VM : ViewModel>(layoutId:Int,fullScreen:Boolean=true,theme:Theme=Theme.Auto,toolBarTheme: Theme=Theme.Auto)
    :BaseActivity(layoutId,fullScreen,theme,toolBarTheme){
    protected  abstract val  viewModel:VM

}