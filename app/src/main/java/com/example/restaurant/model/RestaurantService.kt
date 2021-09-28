package com.example.restaurant.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantService {
    @GET("restaurant/3819401685645462")
    suspend fun getUsers(@Query("key") key: String): Response<Restaurant>
}
