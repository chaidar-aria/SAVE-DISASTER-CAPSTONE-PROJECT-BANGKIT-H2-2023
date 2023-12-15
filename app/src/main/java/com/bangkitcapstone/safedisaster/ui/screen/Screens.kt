package com.bangkitcapstone.safedisaster.ui.screen

sealed class Screens(val route : String) {
    object Home : Screens("home_route")
    object Maps : Screens("maps_route")
    object Profile : Screens("profile_route")
}