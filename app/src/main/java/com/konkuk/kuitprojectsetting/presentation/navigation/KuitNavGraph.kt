package com.konkuk.kuitprojectsetting.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.konkuk.kuitprojectsetting.presentation.diary.DiaryScreen
import com.konkuk.kuitprojectsetting.presentation.home.HomeScreen
import com.konkuk.kuitprojectsetting.presentation.my.MyScreen

@Composable
fun KuitNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.route
    ) {
        composable(route = Route.Home.route) {
            HomeScreen(modifier = modifier)
        }
        composable(route = Route.Diary.route) {
            DiaryScreen(modifier = modifier)
        }
        composable(route = Route.My.route) {
            MyScreen(modifier = modifier)
        }
    }
}