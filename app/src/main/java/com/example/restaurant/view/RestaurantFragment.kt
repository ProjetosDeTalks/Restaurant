package com.example.restaurant.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.restaurant.R
import com.example.restaurant.model.MenuItem
import kotlinx.android.synthetic.main.list_layout.*

class RestaurantFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.apply {
            getParcelableArrayList<MenuItem>(ITEMS_LIST)?.let {
                val adapter = RestaurantAdapter()
                adapter.setList(it)
                dishesList.adapter = adapter
                dishesList.adapter?.notifyDataSetChanged()
            }
        }
    }

    companion object {
        const val ITEMS_LIST = "itemsList"
        fun newInstance(list: ArrayList<MenuItem>): RestaurantFragment {
            val bundle = Bundle()
            bundle.putParcelableArrayList(ITEMS_LIST, list)
            val fragment = RestaurantFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
