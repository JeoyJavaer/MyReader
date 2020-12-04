package master.kotlin.readerpro.base.adapter

import android.content.Context

/**
 * Created on 2020/12/4.
 * Description
 * Others
 */
abstract class ItemViewDelegate<ITEM>(protected  val  context: Context,val layoutId:Int) {

    abstract fun concert(holder: ItemViewHolder,item: ITEM,payloads:MutableList<Any>)

    abstract  fun  registerListener(holder: ItemViewHolder)
}