package com.bangkitcapstone.safedisaster.ui.navigation

import androidx.navigation.NavController

object Destination {
    const val Splash = "Splash"
    const val Login = "Login"
    const val Dashboard = "Dashboard"
    const val Maps = "Maps"
    const val Profile = "Profile"
    const val Settings = "Settings"

}

class Actions(navController: NavController){
    val openDashboard: () -> Unit = {
        navController.navigate(Destination.Dashboard)
    }

    var openSettings: () -> Unit = {
        navController.navigate(Destination.Settings)
    }
}