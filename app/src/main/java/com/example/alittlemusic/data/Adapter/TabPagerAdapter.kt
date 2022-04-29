package com.example.alittlemusic.data.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *
 * @author liuxin
 * Created on 2022/4/15
 * @description：---
 */

class TabPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val mFragmentList = ArrayList<Fragment>()

    fun setNewFragments(data: ArrayList<Fragment>) {
        if (data.isNotEmpty()) {
            mFragmentList.clear()
            mFragmentList.addAll(data)
            notifyDataSetChanged()
        }
    }

    fun removeFragment(position: Int) {
        if (mFragmentList.size > position) {
            mFragmentList.remove(mFragmentList[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return mFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }

}