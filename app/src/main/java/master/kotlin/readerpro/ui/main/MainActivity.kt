package master.kotlin.readerpro.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import master.kotlin.readerpro.R
import master.kotlin.readerpro.base.VMBaseActivity
import master.kotlin.readerpro.help.AppConfig
import master.kotlin.readerpro.lib.theme.ATH
import master.kotlin.readerpro.ui.main.bookshelf.BookShelfFragment
import master.kotlin.readerpro.ui.main.explore.ExploreFragment
import master.kotlin.readerpro.ui.main.my.MyFragment
import master.kotlin.readerpro.ui.main.rss.RssFragment
import master.kotlin.readerpro.utils.getViewModel
import master.kotlin.readerpro.utils.hideSoftInput

/**
 *主页面
 */
class MainActivity : VMBaseActivity<MainViewModel>(R.layout.activity_main) ,
    ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemReselectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {
    override val viewModel: MainViewModel
        get() = getViewModel(MainViewModel::class.java)

    private var pagePosition = 0 // 记录导航栏被选中的位置
    private var bookshelfReseleted :Long =0
    private var exitTIme:Long=0


    private val fragmentId = arrayOf(0, 1, 2, 3)
    private val fragmentMap = mapOf<Int, Fragment>(
        Pair(fragmentId[0], BookShelfFragment()),
        Pair(fragmentId[1], ExploreFragment()),
        Pair(fragmentId[2], RssFragment()),
        Pair(fragmentId[3], MyFragment())
    )


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        ATH.applyEdgeEffectColor(view_pager_main)
        ATH.applyBottomNavigationColor(bottom_navigation_view)
        view_pager_main.offscreenPageLimit = 3
        view_pager_main.adapter = TabFragmentAdapter(supportFragmentManager)
        view_pager_main.addOnPageChangeListener(this)
        bottom_navigation_view.setOnNavigationItemReselectedListener(this)
        bottom_navigation_view.setOnNavigationItemSelectedListener(this)
        bottom_navigation_view.menu.findItem(R.id.menu_rss).isVisible = AppConfig.isShowRSS
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        updateVersion()
        if (AppConfig.autoRefreshBook) {
            view_pager_main.postDelayed({viewModel.upAllBookToc()},1000)
        }
        view_pager_main.postDelayed({viewModel.postLoad()},1000)
    }

    private fun updateVersion() {

    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
      view_pager_main.hideSoftInput()
        pagePosition = position
        when (position) {
            0, 1, 3 -> bottom_navigation_view.menu.getItem(position).isChecked = true
            2 -> if (AppConfig.isShowRSS) {
                bottom_navigation_view.menu.getItem(position).isChecked = true
            } else {
                bottom_navigation_view.menu.getItem(3).isChecked = true
            }
        }

    }

    override fun onNavigationItemReselected(item: MenuItem) {
        when (item.itemId) {
            R.id.menu_bookshelf ->{
                if (System.currentTimeMillis() - bookshelfReseleted > 300) {
                    bookshelfReseleted=System.currentTimeMillis()
                }else{
                    (fragmentMap[0] as? BookShelfFragment)?.gotoTop();
                }

            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_bookshelf -> view_pager_main.setCurrentItem(0, false)
            R.id.menu_find_book -> view_pager_main.setCurrentItem(1, false)
            R.id.menu_rss -> view_pager_main.setCurrentItem(2, false)
            R.id.menu_my_config -> view_pager_main.setCurrentItem(3, false)

        }

        return false
    }


    private inner class TabFragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> fragmentMap.getValue(fragmentId[0])
                1 -> fragmentMap.getValue(fragmentId[1])
                2 -> if (AppConfig.isShowRSS) {
                    fragmentMap.getValue(fragmentId[2])
                } else {
                    fragmentMap.getValue(fragmentId[3])
                }

                else -> fragmentMap.getValue(fragmentId[3])
            }

        }

        override fun getCount(): Int {
            return if (AppConfig.isShowRSS) 4 else 3
        }

        override fun getItemPosition(`object`: Any): Int {
            return PagerAdapter.POSITION_NONE
        }

    }


}