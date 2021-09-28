package com.example.restaurant.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.restaurant.model.MenuItem

class RestaurantPagerAdapter(activity: FragmentActivity, private val itemsList: ArrayList<ArrayList<MenuItem>>) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = itemsList.size

    override fun createFragment(position: Int): Fragment {
        return RestaurantFragment.newInstance(itemsList[position])
    }
}
