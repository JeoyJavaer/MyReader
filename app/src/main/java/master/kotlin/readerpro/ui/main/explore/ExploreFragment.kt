package master.kotlin.readerpro.ui.main.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import master.kotlin.readerpro.R
import master.kotlin.readerpro.base.VMBaseFragment
import master.kotlin.readerpro.ui.main.bookshelf.BookShelfViewModel
import master.kotlin.readerpro.utils.getViewModel

/**
 * Created on 2020/9/2.
 * Description
 * Others
 */
class ExploreFragment:VMBaseFragment<ExploreViewModel>(R.layout.fragment_explore) {

    override val viewModel: ExploreViewModel
        get() = getViewModel(ExploreViewModel::class.java)


    override fun onFragmentCreated(view: View, savedInstanceState: Bundle?) {

    }
}