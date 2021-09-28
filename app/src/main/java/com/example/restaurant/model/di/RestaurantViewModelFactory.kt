package com.example.restaurant.model.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restaurant.model.repository.APIConnector
import com.example.restaurant.model.repository.remote.RemoteRepository

class RestaurantViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return modelClass.getConstructor(RemoteRepository::class.java)
                .newInstance(RemoteRepository(APIConnector.provideAPIInstance()))
        } catch (exception: InstantiationException) {
            throw RuntimeException("Cannot create an instance of: $modelClass", exception)
        } catch (exception: IllegalAccessException) {
            throw RuntimeException("Cannot create an instance of: $modelClass", exception)
        }
    }

    companion object {
        fun newInstance() = RestaurantViewModelFactory()
    }
}
