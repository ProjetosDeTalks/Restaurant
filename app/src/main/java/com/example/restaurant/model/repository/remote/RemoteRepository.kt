package com.example.restaurant.model.repository.remote

import com.example.restaurant.model.RestaurantService
import com.example.restaurant.model.states.StatesAPI
import java.io.IOException

internal class RemoteRepository(private val service: RestaurantService) {

    suspend fun getRestaurant(key: String): StatesAPI {
        try {
            with(service.getUsers(key)) {
                if (isSuccessful) {
                    body()?.let {
                        return StatesAPI.Success(it)
                    }
                } else {
                    return if (code() in 400..499) {
                        StatesAPI.Error(StatesAPI.ClientErrorViewState)
                    } else {
                        StatesAPI.Error(StatesAPI.ServerErrorViewState)
                    }
                }
            }
        } catch (exception: IOException) {
            return StatesAPI.Error(StatesAPI.Exception)
        }
        return StatesAPI.Error(StatesAPI.Generic())
    }
}
