package com.usl.usl.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.usl.usl.fragment.DSectionFragment
import com.usl.usl.fragment.HSectionFragment
import com.usl.usl.fragment.MSectionFragment

class ViewPagerAdapter(fm: FragmentManager, var bundle: Bundle?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MSectionFragment(bundle)
            1 -> fragment = DSectionFragment(bundle)
            2 -> fragment = HSectionFragment(bundle)
            else -> {
            }
        }
        return fragment!!
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if (position == 0) {
            title = "M Section"
        } else if (position == 1) {
            title = "D Section"
        } else if (position == 2) {
            title = "H Section"
        }
        return title
    }


}