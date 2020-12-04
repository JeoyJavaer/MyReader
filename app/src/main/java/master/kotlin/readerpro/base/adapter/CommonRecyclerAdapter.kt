package master.kotlin.readerpro.base.adapter

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.view.menu.MenuView
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

    private val  inflater:LayoutInflater = LayoutInflater.from(context)
    private var headerItems:SparseArray<View>?=null
    private var footerItems:SparseArray<View>?=null

    private val itemDelegates:HashMap<Int,ItemViewDelegate<ITEM>> = hashMapOf()
    private  val items:MutableList<ITEM> = mutableListOf()

    private  val lock =Object()

    private var itemClickListener: ((holder: ItemViewHolder, item: ITEM) -> Unit)? = null
    private var itemLongClickListener: ((holder: ItemViewHolder, item: ITEM) -> Boolean)? = null

    private var itemAnimation:ItemAnimation?=null

    fun <DELEGATE : ItemViewDelegate<ITEM>> addItemViewDelegate(delegate: DELEGATE) {
        itemDelegates[itemDelegates.size] = delegate
    }
    fun <DELEGATE:ItemViewDelegate<ITEM>>addItemViewDelegates(vararg delegates:DELEGATE ){
        delegates.forEach {
            addItemViewDelegate(it)
        }
    }

}