package com.example.fitnessapplication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

enum class MainScreen() {
    Start,
    Macro,
    Workouts,
}

@Composable
fun SharedViewModel(navController: NavHostController, mainViewModel: MainViewModel) {

    // Observe the current destination
    val currentDestination by navController.currentBackStackEntryAsState()

    // Trigger when the destination changes
    LaunchedEffect(currentDestination) {
        changeTopAppBarName(currentDestination?.destination?.route, mainViewModel)
    }
}

fun changeTopAppBarName(route: String?, viewModel: MainViewModel) {
    when (route) {
        MainScreen.Workouts.name -> viewModel.changeTitle("Workouts")
        MainScreen.Macro.name -> viewModel.changeTitle("Macros")
        else -> viewModel.changeTitle("PlaceHolder")
    }
}