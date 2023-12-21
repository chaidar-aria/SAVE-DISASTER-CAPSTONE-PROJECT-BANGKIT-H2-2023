package com.bangkitcapstone.safedisaster.ui.screen.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bangkitcapstone.safedisaster.R
import com.bangkitcapstone.safedisaster.ui.navigation.Actions
import com.bangkitcapstone.safedisaster.ui.screen.evacuation.EvacuationRoute
import com.bangkitcapstone.safedisaster.ui.screen.home.HomeScreen
import com.bangkitcapstone.safedisaster.ui.screen.maps.MapsScreen
import com.bangkitcapstone.safedisaster.ui.screen.profile.ProfileScreen
import com.bangkitcapstone.safedisaster.ui.theme.BrownDark

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardScreen(actions: Actions, navController: NavController) {
    val selectedIndex = remember {
        mutableIntStateOf(0)
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            content = {
                when (selectedIndex.intValue) {
                    0 -> HomeScreen()
                    1 -> EvacuationRoute(actions, navController)
                }
            },
            bottomBar = {
                CustomBottomBar(selectedIndex = selectedIndex)
            },
        )

    }

}

@Composable
fun CustomBottomBar(selectedIndex: MutableState<Int>) {

    val listItems = listOf("Home", "EvacuationRoute")

    androidx.compose.material.Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(64.dp)
    ) {
        BottomNavigation(backgroundColor = Color.White) {
            listItems.forEachIndexed { index, label ->
                val isSelected = selectedIndex.value == index
                BottomNavigationItem(
                    icon = {
                        when (index) {
                            0 -> TabIcons(R.drawable.home, isSelected)
                            1 -> TabIcons(R.drawable.map, isSelected)
                        }
                    },
                    selected = isSelected,
                    onClick = { selectedIndex.value = index },
                    alwaysShowLabel = false
                )
            }
        }
    }
}

@Composable
fun TabIcons(drawableId: Int, isTintColor: Boolean) {
    val iconPainter = painterResource(id = drawableId)
    if (isTintColor) {
        Image(
            modifier = Modifier.wrapContentSize(),
            painter = iconPainter,
            colorFilter = ColorFilter.tint(BrownDark),
            contentScale = ContentScale.Fit,
            contentDescription = "tb_icon_if"
        )
    } else {
        Image(
            modifier = Modifier.wrapContentSize(),
            painter = iconPainter,
            contentScale = ContentScale.Fit,
            contentDescription = "tb_icon_else"
        )
    }
}