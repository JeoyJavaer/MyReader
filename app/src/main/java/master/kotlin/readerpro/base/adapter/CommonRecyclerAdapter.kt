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
abstract class CommonRecyclerAdapter<ITEM>(protected val context: Context) :
    RecyclerView.Adapter<ItemViewHolder> {

    constructor(context: Context, vararg delegates: ItemViewDelegate<ITEM>) : this(context) {
        addItemViewDelegates(*delegates)
    }

    constructor(
        context: Context,
        vararg delegates: Pair<Int, ItemViewDelegate<ITEM>>
    ) : this(context) {
        addItemViewDelegate(*delegates)
    }

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var headerItems: SparseArray<View>? = null
    private var footerItems: SparseArray<View>? = null

    private val itemDelegates: HashMap<Int, ItemViewDelegate<ITEM>> = hashMapOf()
    private val items: MutableList<ITEM> = mutableListOf()

    private val lock = Object()

    private var itemClickListener: ((holder: ItemViewHolder, item: ITEM) -> Unit)? = null
    private var itemLongClickListener: ((holder: ItemViewHolder, item: ITEM) -> Boolean)? = null

    private var itemAnimation: ItemAnimation? = null

    fun setOnItemClickListener(listener: (holder: ItemViewHolder, item: ITEM) -> Unit) {
        itemClickListener = listener
    }


    fun setOnItemLongClickListener(listener: (holder: ItemViewHolder, item: ITEM) -> Boolean) {
        itemLongClickListener = listener
    }

    fun bindToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = this
    }

    fun <DELEGATE : ItemViewDelegate<ITEM>> addItemVIewDelegate(viewType: Int, delegate: DELEGATE) {
        itemDelegates[viewType] = delegate
    }

    fun <DELEGATE : ItemViewDelegate<ITEM>> addItemViewDelegate(delegate: DELEGATE) {
        itemDelegates[itemDelegates.size] = delegate
    }

    fun <DELEGATE : ItemViewDelegate<ITEM>> addItemViewDelegates(vararg delegates: DELEGATE) {
        delegates.forEach {
            addItemViewDelegate(it)
        }
    }

    fun addItemVIewDelegates(vararg delegate: Pair<Int, ItemViewDelegate<ITEM>>) {
        delegate.forEach {
            addItemVIewDelegate(it.first, it.second)
        }
    }

    fun addHeaderView(header: View) {
        synchronized(lock) {
            if (headerItems == null) {
                headerItems = SparseArray()
            }
            headerItems?.let {
                val index = it.size()
                it.put(TYPE_HEADER_VIEW + it.size(), header)
                notifyItemInserted(index)
            }
        }
    }

    fun  addFooterView(footerView: View){
        synchronized(lock){
            if (footerItems==null){
                footerItems = SparseArray()
            }

            footerItems?.let {
                val  index = getAc
            }
        }
    }



    companion object {
        private const val TYPE_HEADER_VIEW = Int.MIN_VALUE
        private const val TYPE_FOOTER_VIEW = Int.MAX_VALUE - 99
    }

}