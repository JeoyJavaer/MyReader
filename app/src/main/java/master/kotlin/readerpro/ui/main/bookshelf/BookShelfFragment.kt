package master.kotlin.readerpro.ui.main.bookshelf

import android.os.Bundle
import android.util.Log
import android.view.View
import master.kotlin.readerpro.R
import master.kotlin.readerpro.base.VMBaseFragment
import master.kotlin.readerpro.utils.getViewModel

/**
 * Created on 2020/9/2.
 * Description
 * Other
 */
class BookShelfFragment : VMBaseFragment<BookShelfViewModel>(R.layout.fragment_bookshelf) {
    override val viewModel: BookShelfViewModel
        get() = getViewModel(BookShelfViewModel::class.java)

    override fun onFragmentCreated(view: View, savedInstanceState: Bundle?) {
//        setSupportToolbar(toolbar)

    }

    fun  gotoTop(){
        Log.i("BookShelfFragment","gotoTop")
    }


}