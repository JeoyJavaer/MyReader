package master.kotlin.readerpro.base.adapter

import android.content.Context

/**
 * Created on 2020/12/4.
 * Description
 * Others
 */
abstract class SimpleRecyclerAdapter<ITEM>(context: Context,private val layoutId:Int):CommonRecyclerAdapter<ITEM>(context){

    init {
        addItemViewDelegate(object :ItemViewDelegate<ITEM>(context, layoutId){
            override fun concert(holder: ItemViewHolder, item: ITEM, payloads: MutableList<Any>) {
                this@SimpleRecyclerAdapter.convert(holder,item,payloads)
            }

            override fun registerListener(holder: ItemViewHolder) {
                this@SimpleRecyclerAdapter.registerListener(holder)
            }

        })
    }


    abstract  fun  convert(holder: ItemViewHolder, item: ITEM, payloads: MutableList<Any>);
    abstract  fun  registerListener(holder: ItemViewHolder)
}