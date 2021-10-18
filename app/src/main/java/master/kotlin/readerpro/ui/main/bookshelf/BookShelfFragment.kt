package master.kotlin.readerpro.ui.main.bookshelf

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.view_title_bar.*
import master.kotlin.readerpro.R
import master.kotlin.readerpro.base.VMBaseFragment
import master.kotlin.readerpro.data.entities.BookGroup
import master.kotlin.readerpro.ui.book.group.GroupManagerDialog
import master.kotlin.readerpro.ui.main.MainViewModel
import master.kotlin.readerpro.utils.getViewModel
import master.kotlin.readerpro.utils.getViewModelOfActivity

/**
 * Created on 2020/9/2.
 * Description
 * Other
 */
class BookShelfFragment : VMBaseFragment<BookShelfViewModel>(R.layout.fragment_bookshelf),TabLayout.OnTabSelectedListener,SearchView.OnQueryTextListener,GroupManagerDialog.CallBack {
    override val viewModel: BookShelfViewModel
        get() = getViewModel(BookShelfViewModel::class.java)
    private val  activityViewModel:MainViewModel
        get() = getViewModelOfActivity(MainViewModel::class.java)
    private var bookGroupLivewData:LiveData<List<BookGroup>>?=null

    private var noGroupLiveData:LiveData<Int>?=null
    private  val bookGroups = mutableListOf<BookGroup>()
    private val gragmentMap = hashMapOf<Int,BookShelfFragment>()
    private  var showGroupNone=false



    override fun onFragmentCreated(view: View, savedInstanceState: Bundle?) {
        setSupportToolbar(toolbar)

    }

    fun  gotoTop(){
        Log.i("BookShelfFragment","gotoTop")
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        TODO("Not yet implemented")
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        TODO("Not yet implemented")
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        TODO("Not yet implemented")
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun upGroup() {
        TODO("Not yet implemented")
    }


}