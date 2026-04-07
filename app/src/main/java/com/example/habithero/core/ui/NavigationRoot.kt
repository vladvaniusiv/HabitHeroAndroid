package com.example.habithero.core.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habithero.feature_auth.presentation.login.LoginRoute
import com.example.habithero.feature_auth.presentation.signup.RegisterRoute
import com.example.habithero.feature_home.presentation.home.HomeRoute
import com.example.habithero.feature_home.presentation.screens.HomeScreen
import com.example.habithero.feature_settings.presentation.screens.SettingsScreen
import com.example.habithero.feature_settings.presentation.settings.SettingsRoute
import com.example.habithero.feature_stats.presentation.screens.StatsScreen
import com.example.habithero.feature_stats.presentation.stats.StatsRoute

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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationRoot(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginRoute(
                onNavigateToHome = { navController.navigate(Routes.HOME) },
                onNavigateToSignUp = { navController.navigate(Routes.SIGNUP) }
            )
        }
        composable(Routes.SIGNUP) {
            RegisterRoute(
                onNavigateToHome = { navController.navigate(Routes.HOME) },
                onNavigateBackToLogin = { navController.popBackStack(Routes.LOGIN, inclusive = false) }
            )
        }
        composable(Routes.HOME) {
            HomeRoute(
                onNavigateToStats = { navController.navigate(Routes.STATS) },
                onNavigateToSettings = { navController.navigate(Routes.SETTINGS) }
            )
        }
        composable(Routes.STATS) {
            StatsRoute(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToHome = { navController.navigate(Routes.HOME) },
                onNavigateToSettings = { navController.navigate(Routes.SETTINGS) },

            )
        }
        composable(Routes.SETTINGS) {
            SettingsRoute(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToHome = { navController.navigate(Routes.HOME) },
                onNavigateToStats = { navController.navigate(Routes.STATS) }
            )
        }
    }
}
