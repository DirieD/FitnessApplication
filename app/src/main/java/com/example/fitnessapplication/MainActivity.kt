package com.example.fitnessapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fitnessapplication.ui.theme.FitnessApplicationTheme
import kotlinx.coroutines.launch
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessApplicationTheme {
                Screen()
            }
        }
    }
}

enum class MainScreen() {
    Start,
    Macro,
    Workouts,
}

@Composable
fun Screen(
    modifier: Modifier = Modifier,
) {

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(navController = navController)
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    onOpenDrawer = {
                        scope.launch {
                            drawerState.apply { if (isClosed) open() else close() }
                        }
                    }

                )

            }
        ) { padding ->
            ScreenContent(modifier = Modifier.padding(padding))
        }
    }
}

@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = MainScreen.Start.name,
    ) {
        composable(route = MainScreen.Start.name) {
            Text(text = "Welcome to the Start Screen", modifier = Modifier.padding(16.dp))
        }

        composable(route = MainScreen.Macro.name) {
            MacronutrientScreen()
        }

        composable(route = MainScreen.Workouts.name) {
            WorkoutScreen()
        }
    }

    Text(
        text = "App Name",
        fontSize = 24.sp,
        modifier = Modifier.padding(16.dp)
    )
    Divider()
    Spacer(modifier = Modifier.height(8.dp))

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Account"
            )
        },
        label = {
            Text(
                text = "Macronutrient Calculator",
                fontSize = 17.sp,
                modifier = Modifier.padding(2.dp)
            )
        },
        selected = false,
        onClick = { navController.navigate(MainScreen.Macro.name) }
    )

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = "Account"
            )
        },
        label = {
            Text(
                text = "Workouts",
                fontSize = 17.sp,
                modifier = Modifier.padding(2.dp)
            )
        },
        selected = false,
        onClick = { navController.navigate(MainScreen.Workouts.name) }
    )
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onOpenDrawer: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .size(27.dp)
                    .clickable {
                        onOpenDrawer()
                    }
            )
        },
        title = {
            Text(text = "Fitness App")
        }
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FitnessApplicationTheme {

    }
}