package com.example.restaurant.utils.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun ViewModel.runTaskOnBackground(task: suspend () -> Unit) {
    viewModelScope.launch {
        task()
    }
}
