package com.example.restaurant.viewModel

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

internal class RestaurantViewModelTest {

    private lateinit var viewModel: RestaurantViewModel

    @BeforeEach
    fun setup() {
        viewModel = RestaurantViewModel(mock())
    }

    @Test
    fun checkIfASuccessfulCallAlreadyBeenMade() {
        val hasASuccessfulCallAlreadyBeenMade = viewModel.hasASuccessfulCallAlreadyBeenMade()
        assertEquals(hasASuccessfulCallAlreadyBeenMade, false)
    }
}
