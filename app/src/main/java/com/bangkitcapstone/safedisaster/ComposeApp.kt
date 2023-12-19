package com.bangkitcapstone.safedisaster

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bangkitcapstone.safedisaster.ui.navigation.Actions
import com.bangkitcapstone.safedisaster.ui.navigation.Destination
import com.bangkitcapstone.safedisaster.ui.screen.dashboard.DashboardScreen
import com.bangkitcapstone.safedisaster.ui.screen.login.LoginScreen
import com.bangkitcapstone.safedisaster.ui.screen.profile.ProfileScreen
import com.bangkitcapstone.safedisaster.ui.screen.settings.SettingsScreen

@Composable
fun ComposeApp() {
    val navController = rememberNavController()

    val actions = remember(navController) { Actions(navController) }
    MaterialTheme {
        NavHost(navController = navController, startDestination = Destination.Login) {

            composable(Destination.Login) {
                LoginScreen(actions.openDashboard)
            }

            composable(Destination.Dashboard) {
                DashboardScreen(actions)
            }

            composable(Destination.Profile) {
                ProfileScreen(actions)
            }

            composable(Destination.Settings) {
                SettingsScreen()
            }
        }
    }
}