package master.kotlin.readerpro.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import master.kotlin.readerpro.R
import master.kotlin.readerpro.ui.main.bookshelf.BookShelfFragment
import master.kotlin.readerpro.ui.main.explore.ExploreFragment
import master.kotlin.readerpro.ui.main.my.MyFragment
import master.kotlin.readerpro.ui.main.rss.RssFragment
import  kotlinx.android.synthetic.main.activity_main.*
import master.kotlin.readerpro.help.Appconfig

/**
 *
 */
class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemReselectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private var pagePosition =0 // 记录导航栏被选中的位置
    private val fragmentId = arrayOf(0, 1, 2, 3)
    private val fragmentMap = mapOf<Int, Fragment>(
        Pair(fragmentId[0], BookShelfFragment()),
        Pair(fragmentId[1], ExploreFragment()),
        Pair(fragmentId[2], RssFragment()),
        Pair(fragmentId[3], MyFragment())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view_pager_main.offscreenPageLimit = 3
        view_pager_main.adapter = TabFragmentAdapter(supportFragmentManager)
        view_pager_main.addOnPageChangeListener(this)
        bottom_navigation_view.setOnNavigationItemReselectedListener(this)
        bottom_navigation_view.setOnNavigationItemSelectedListener(this)
        bottom_navigation_view.menu.findItem(R.id.menu_rss).isVisible = Appconfig.isShowRSS
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
//       view_pager_main.hideSoftInput()
        pagePosition = position
        when(position){
            0,1,3 -> bottom_navigation_view.menu.getItem(position).isChecked=true
            2 -> if (Appconfig.isShowRSS){
                bottom_navigation_view.menu.getItem(position).isChecked=true
            }else{
                bottom_navigation_view.menu.getItem(3).isChecked=true
            }
        }

    }

    override fun onNavigationItemReselected(item: MenuItem) {
        TODO("Not yet implemented")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }


    private inner class TabFragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(
        fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> fragmentMap.getValue(fragmentId[0])
                1 -> fragmentMap.getValue(fragmentId[0])
                2 -> if (Appconfig.isShowRSS) {
                    fragmentMap.getValue(fragmentId[2])
                } else {
                    fragmentMap.getValue(fragmentId[3])
                }

                else -> fragmentMap.getValue(fragmentId[3])
            }

        }

        override fun getCount(): Int {
            return if (Appconfig.isShowRSS) 4 else 3
        }

        override fun getItemPosition(`object`: Any): Int {
            return PagerAdapter.POSITION_NONE
        }

    }


}