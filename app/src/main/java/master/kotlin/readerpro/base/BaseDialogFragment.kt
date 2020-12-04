package master.kotlin.readerpro.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import master.kotlin.readerpro.help.coroutine.Coroutine
import master.kotlin.readerpro.lib.theme.ThemeStore
import kotlin.coroutines.CoroutineContext

/**
 * Created on 2020/12/4.
 * Description
 * Others
 */
abstract class BaseDialogFragment : DialogFragment(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundColor(ThemeStore.backgroundColor())
        onFragmentCreated(view, savedInstanceState)
        observeLiveBus()
    }

    abstract fun onFragmentCreated(view: View, savedInstanceState: Bundle?)

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            manager.beginTransaction().remove(this).commit()
            super.show(manager, tag)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    fun <T> execute(
        scope: CoroutineScope=this,
        context: CoroutineContext=Dispatchers.IO,
        block:suspend  CoroutineScope.() ->T
    ): Coroutine<T> {
        return  Coroutine.async(scope,context){block()}
    }

    open  fun  observeLiveBus(){}
}