package master.kotlin.readerpro.base.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate

/**
 * Created on 2020/12/4.
 * Description
 * Others
 */
abstract class CommonRecyclerAdapter<ITEM>(protected val  context: Context):RecyclerView.Adapter<ItemViewHolder> {

    constructor(context: Context,vararg delegates: ItemViewDelegate<ITEM> ):this(context){
        addItemViewDelegates(*delegates)
    }

    constructor(
        context: Context,
        vararg  delegates: Pair<Int,ItemViewDelegate<ITEM>>
    ):this(context){
        addItemViewDelegate(*delegates)
    }

    fun <DELEGATE : ItemViewDelegate<ITEM>> addItemViewDelegate(delegate: DELEGATE) {
        itemDelegates[itemDelegates.size] = delegate
    }
    fun <DELEGATE:ItemViewDelegate<ITEM>>addItemViewDelegates(vararg delegates:DELEGATE ){
        delegates.forEach {
            addItemViewDelegate(it)
        }
    }

}