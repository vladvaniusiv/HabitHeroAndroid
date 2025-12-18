package com.example.habithero.core.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habithero.feature_auth.presentation.screens.LoginScreen
import com.example.habithero.feature_auth.presentation.screens.SignUpScreen
import com.example.habithero.feature_home.presentation.screens.HomeScreen
import com.example.habithero.feature_settings.presentation.screens.SettingsScreen
import com.example.habithero.feature_stats.presentation.screens.StatsScreen

/**
 * Rutas de navegación
 */
object Routes {
    const val LOGIN = "login"
    const val SIGNUP = "signup"
    const val HOME = "home"
    const val STATS = "stats"
    const val SETTINGS = "settings"
}

@Composable
fun NavigationRoot(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = { navController.navigate(Routes.HOME) },
                onSignUpClick = { navController.navigate(Routes.SIGNUP) }
            )
        }
        composable(Routes.SIGNUP) {
            SignUpScreen(
                onSignUpSuccess = { navController.navigate(Routes.HOME) },
                onBackToLogin = { navController.popBackStack(Routes.LOGIN, inclusive = false) }
            )
        }
        composable(Routes.HOME) {
            HomeScreen(
                onNavigateToStats = { navController.navigate(Routes.STATS) },
                onNavigateToSettings = { navController.navigate(Routes.SETTINGS) }
            )
        }
        composable(Routes.STATS) {
            StatsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToHome = { navController.navigate(Routes.HOME) },
                onNavigateToSettings = { navController.navigate(Routes.SETTINGS) }
            )
        }
        composable(Routes.SETTINGS) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToStats = { navController.navigate(Routes.STATS) },
                onNavigateToHome = { navController.navigate(Routes.HOME) }
            )
        }
    }
}
