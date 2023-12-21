package com.bangkitcapstone.safedisaster

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bangkitcapstone.safedisaster.model.clasterizationList
import com.bangkitcapstone.safedisaster.model.evacuationRouteList
import com.bangkitcapstone.safedisaster.preferences.UserPreference
import com.bangkitcapstone.safedisaster.ui.navigation.Actions
import com.bangkitcapstone.safedisaster.ui.navigation.Destination
import com.bangkitcapstone.safedisaster.ui.screen.clasterization.ClasterizationScreenDetail
import com.bangkitcapstone.safedisaster.ui.screen.dashboard.DashboardScreen
import com.bangkitcapstone.safedisaster.ui.screen.evacuation.EvacuationRouteDetail
import com.bangkitcapstone.safedisaster.ui.screen.login.LoginScreen
import com.bangkitcapstone.safedisaster.ui.screen.profile.ProfileScreen
import com.bangkitcapstone.safedisaster.ui.screen.register.RegisterScreen
import com.bangkitcapstone.safedisaster.ui.screen.settings.SettingsScreen

@Composable
fun ComposeApp() {
    val navController = rememberNavController()

    val context = LocalContext.current
    val userPreference = remember {
        UserPreference(context)
    }
    val userEmail = userPreference.getUserEmail()

    val actions = remember(navController) { Actions(navController) }

    if (userEmail != null) {
        DashboardScreen(actions, navController)
    } else {
        LoginScreen(onLoginSuccess = actions.openDashboard, navController)
    }

    MaterialTheme {
        NavHost(navController = navController, startDestination = Destination.Dashboard) {

            composable(Destination.Register) {
                RegisterScreen(onRegisterSuccess = actions.openDashboard, navController)
            }

            composable(Destination.Login) {
                LoginScreen(onLoginSuccess = actions.openDashboard, navController)
            }

            composable(Destination.Dashboard) {
                DashboardScreen(actions, navController)
            }

            composable(Destination.Profile) {
                ProfileScreen(actions)
            }

            composable(Destination.Settings) {
                SettingsScreen()
            }

            composable(
                "${Destination.EvacuationRouteDetail}/{routeId}",
                arguments = listOf(navArgument("routeId") { type = NavType.IntType })
            ) { backStackEntry ->
                val routeId = backStackEntry.arguments?.getInt("routeId")
                val routeModel = evacuationRouteList.find { it.textCategoryEvacuationRoute == routeId }
                routeModel?.let {
                    EvacuationRouteDetail(model = it, textCategory = null)
                }
            }

            composable(
                "${Destination.ClasterizationScreenDetail}/{routeId}",
                arguments = listOf(navArgument("routeId") { type = NavType.IntType })
            ) { backStackEntry ->
                val routeId = backStackEntry.arguments?.getInt("routeId")
                val routeModel = clasterizationList.find { it.textCategoryClasterization == routeId }
                routeModel?.let {
                    ClasterizationScreenDetail(model = it, textCategory = null)

                }
            }
        }
    }
}
