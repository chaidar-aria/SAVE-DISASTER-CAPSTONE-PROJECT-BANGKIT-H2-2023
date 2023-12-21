package com.bangkitcapstone.safedisaster.ui.navigation

import androidx.navigation.NavController

object Destination {
    const val Splash = "Splash"
    const val Register = "Register"
    const val Login = "Login"
    const val Dashboard = "Dashboard"
    const val Maps = "Maps"
    const val Profile = "Profile"
    const val Settings = "Settings"
    const val EvacuationRoute = "EvacuationRoute"
    const val EvacuationRouteDetail = "EvacuationRouteDetail"
    const val ClasterizationScreenDetail = "ClasterizationScreenDetail"

}

class Actions(navController: NavController) {
    val openDashboard: () -> Unit = {
        navController.navigate(Destination.Dashboard)
    }

    var openSettings: () -> Unit = {
        navController.navigate(Destination.Settings)
    }

    val openEvacuationRouteDetail: (NavController, Int) -> Unit = { navController, routeId ->
        navController.navigate("${Destination.EvacuationRouteDetail}/$routeId")
    }

    val openClasterizationScreenDetail: (NavController, Int) -> Unit = { navController, routeId ->
        navController.navigate("${Destination.ClasterizationScreenDetail}/$routeId")
    }
}