package com.example.restaurant.view

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.model.MenuItem
import kotlinx.android.synthetic.main.item_view.view.*

class RestaurantViewHolder(private val viewItem: View) : RecyclerView.ViewHolder(viewItem) {

    fun bind(model: MenuItem) {
        viewItem.mealName.text = model.name
        if (model.description.isNotEmpty()) {
            viewItem.mealDescription.text = model.description
        } else {
            viewItem.mealDescription.isVisible = false
        }
        model.pricing.map {
            viewItem.mealPrice.text = it.priceString
        }
    }
}
