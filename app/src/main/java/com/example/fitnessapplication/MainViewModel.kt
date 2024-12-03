package com.example.fitnessapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var title by mutableStateOf("Welcome to the Fitness App")
    fun changeTitle(newTitle: String) {
        title = newTitle
    }
}
