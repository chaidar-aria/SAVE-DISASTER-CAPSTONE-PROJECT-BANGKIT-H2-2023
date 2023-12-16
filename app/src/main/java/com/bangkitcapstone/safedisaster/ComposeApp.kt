//package com.bangkitcapstone.safedisaster
//
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//
//@Composable
//fun ComposeApp() {
//    val navController = rememberNavController()
//
//    val actions = remember(navController) { Actions(navController) }
//    MaterialTheme {
//        NavHost(navController = navController, startDestination = Destination.Dashboard) {
//
////            composable(Destination.Login) {
////                LoginScreen(actions.openDashboard)
////            }
//
//            composable(Destination.DashBoard) {
//                DashboardScreen()
//            }
//        }
//    }
//}