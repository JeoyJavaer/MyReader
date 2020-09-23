package master.kotlin.readerpro.ui.main.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import master.kotlin.readerpro.R
import master.kotlin.readerpro.base.VMBaseFragment

/**
 * Created on 2020/9/2.
 * Description
 * Others
 */
class MyFragment :VMBaseFragment<MyViewModel>(R.layout.fragment_my){
    override val viewModel: MyViewModel
        get() = TODO("Not yet implemented")

    override fun onFragmentCreated(view: View, savedInstanceState: Bundle?) {

    }
}