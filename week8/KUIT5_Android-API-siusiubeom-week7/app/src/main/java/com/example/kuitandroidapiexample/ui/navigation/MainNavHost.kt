package com.example.kuitandroidapiexample.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.compose.ui.platform.LocalContext
import com.example.kuitandroidapiexample.App
import com.example.kuitandroidapiexample.ui.detail.screen.DetailScreen
import com.example.kuitandroidapiexample.ui.detail.viewmodel.AnimalDetailViewModel
import com.example.kuitandroidapiexample.ui.home.screen.HomeScreen
import com.example.kuitandroidapiexample.ui.home.screen.PreferencesScreen
import com.example.kuitandroidapiexample.ui.home.viewmodel.AnimalViewModel
import com.example.kuitandroidapiexample.ui.register.screen.RegisterScreen
import com.example.kuitandroidapiexample.ui.register.viewmodel.AnimalRegisterViewModel

@Composable
fun MainNavHost(
    padding: PaddingValues
) {
    val navController = rememberNavController()

    val context = LocalContext.current.applicationContext as App
    val viewModel: AnimalViewModel = viewModel(
        factory = AnimalViewModel.AnimalViewModelFactory(context.appContainer.provideRepository())
    )
    val detailViewModel: AnimalDetailViewModel = viewModel(
        factory = AnimalDetailViewModel.AnimalDetailViewModelFactory(context.appContainer.provideRepository())
    )

    val registerViewModel: AnimalRegisterViewModel = viewModel(
        factory = AnimalRegisterViewModel.AnimalRegisterViewModelFactory(context.appContainer.provideRepository())
    )

    NavHost(
        navController = navController,
        startDestination = Route.Home
    ) {
        composable<Route.Home> {
            HomeScreen(
                padding = padding,
                navigateToRegister = { navController.navigate(Route.Register) },
                navigateToDetail = { index ->
                    navController.navigate(Route.Detail(index))
                },
                navigateToPref = {navController.navigate(Route.Preferences)},
                viewModel = viewModel
            )
        }
        composable<Route.Register> {
            RegisterScreen(
                padding = padding,
                navigateToBack = { navController.navigateUp() },
                viewModel = registerViewModel
            )
        }
        composable<Route.Detail> { navBackStackEntry ->
            val args = navBackStackEntry.toRoute<Route.Detail>()

            DetailScreen(
                padding = padding,
                index = args.index,
                navigateToBack = { navController.navigateUp() },
                viewModel = detailViewModel
            )
        }
        composable<Route.Preferences> {
            PreferencesScreen(

            )
        }
    }

}