package com.example.fitnessapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
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
import androidx.compose.runtime.getValue
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessApplicationTheme {
                Screen(viewModel = viewModel)
            }
        }
    }
}


@Composable
fun Screen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    navController = navController,
                    scope = scope,
                    drawerState = drawerState
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    onOpenDrawer = {
                        scope.launch {
                            if (drawerState.isClosed) drawerState.open() else drawerState.close()
                        }
                    },
                    appBarTitle = viewModel.title // Observe the title in the top bar
                )
            }
        ) { padding ->
            SharedViewModel(
                navController = navController,
                mainViewModel = viewModel
            ) // This updates the title
            NavHost(
                navController = navController,
                startDestination = MainScreen.Start.name,
                modifier = Modifier.padding(padding)
            ) {
                composable(route = MainScreen.Start.name) {
                    Text("Welcome to the Fitness App!")
                }

                composable(route = MainScreen.Macro.name) {
                    MacronutrientScreen()
                }

                composable(route = MainScreen.Workouts.name) {
                    WorkoutScreen()
                }
            }
            ScreenContent(modifier = Modifier.padding(padding))
        }
    }
}


@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    scope: CoroutineScope,
    drawerState: DrawerState,
) {
    // Track the current destination
    val currentDestination by navController.currentBackStackEntryAsState()

    Text(
        text = "Menu",
        fontSize = 24.sp,
        modifier = Modifier.padding(16.dp)
    )
    Divider()
    Spacer(modifier = Modifier.height(8.dp))

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = if (
                    currentDestination?.destination?.route == MainScreen.Macro.name
                ) {
                    Icons.Default.Favorite
                } else {
                    Icons.Outlined.FavoriteBorder
                },
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
        selected = currentDestination?.destination?.route == MainScreen.Macro.name,
        onClick = {
            scope.launch { drawerState.close() }
            navController.navigate(MainScreen.Macro.name)
        }
    )

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = if (
                    currentDestination?.destination?.route == MainScreen.Workouts.name
                ) {
                    Icons.Default.Build
                } else {
                    Icons.Outlined.Build
                },
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
        selected = currentDestination?.destination?.route == MainScreen.Workouts.name, // Dynamically set
        onClick = {
            scope.launch { drawerState.close() }
            navController.navigate(MainScreen.Workouts.name)
        }
    )
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onOpenDrawer: () -> Unit, appBarTitle: String) {

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
            Text(
                text = appBarTitle
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FitnessApplicationTheme {

    }
}