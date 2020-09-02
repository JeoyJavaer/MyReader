package master.kotlin.readerpro.base


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.view.SupportMenuInflater
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import master.kotlin.readerpro.utils.applyTint
import kotlin.coroutines.CoroutineContext

/**
 * Created on 2020/9/2.
 * Description
 * Others
 */
abstract class BaseFragment(layoutId: Int) : Fragment(layoutId), CoroutineScope {
    lateinit var job: Job

    var supportToolbar: Toolbar? = null
        private set

    val menuInflate: MenuInflater
        @SuppressLint("RestrictedApi")
        get() = SupportMenuInflater(requireContext())

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        job = Job()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentCreated(view, savedInstanceState)
        observeLiveBus()
    }

    fun setSupportToolbar(toolbar: Toolbar) {
        supportToolbar = toolbar
        supportToolbar?.let {
            it.menu.apply {
                onCompatCreateOptionsMenu(this)
                applyTint(requireContext())
            }

            it.setOnMenuItemClickListener { item ->
                onCompatOptionsItemSelected(item)
                true
            }
        }
    }

    open fun onCompatCreateOptionsMenu(menu: Menu) {

    }


    open fun onCompatOptionsItemSelected(item: MenuItem) {

    }

    open fun observeLiveBus() {

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    abstract fun onFragmentCreated(view: View, savedInstanceState: Bundle?)

}