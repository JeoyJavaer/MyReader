package master.kotlin.readerpro.base.adapter

import android.content.Context

/**
 * Created on 2020/12/4.
 * Description
 * Others
 */
abstract class SimpleRecyclerAdapter<ITEM>(context: Context,private val layoutId:Int)

    :CommonRecyclerAdapter<ITEM>(context){

}