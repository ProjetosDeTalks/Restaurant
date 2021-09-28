package com.example.restaurant.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

data class Restaurant(
    @SerializedName("result")
    val result: Result
)

data class Result(
    @SerializedName("menus")
    val menus: List<Menu>,
    @SerializedName("restaurant_name")
    val restaurantName: String
)

data class Menu(
    @SerializedName("menu_name")
    val menuName: String,
    @SerializedName("menu_sections")
    val menuSections: List<MenuSection>
)

@Parcelize
data class MenuSection(
    @SerializedName("menu_items")
    val menuItems: ArrayList<MenuItem>,
    @SerializedName("section_name")
    val sectionName: String
) : Parcelable

@Parcelize
data class MenuItem(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("pricing")
    val pricing: @RawValue List<Pricing>
) : Parcelable

@Parcelize
data class Pricing(
    @SerializedName("priceString")
    val priceString: String
) : Parcelable