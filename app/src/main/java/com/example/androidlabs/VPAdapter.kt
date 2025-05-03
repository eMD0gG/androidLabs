package com.example.androidlabs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class VPAdapter(fm: Fragment) : FragmentStateAdapter(fm) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyActivitiesFragment()
            1 -> UsersActivitiesFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

}