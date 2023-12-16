package com.bangkitcapstone.safedisaster.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardScreen() {
    val selectedIndex = remember {
        mutableIntStateOf(0)
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                CustomTopAppBar()
            },
            content = {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Box(modifier = Modifier.padding(bottom = 96.dp)) {
                            when (selectedIndex.value) {
                                0 -> {
                                    HomeScreen()
                                }
                                1 -> {
                                }
                                2 -> {
                                }
                                3 -> {
                                }
                            }
                        } //bodyContent()

                    }
                }
            },
            bottomBar = {
                BottomBar(selectedIndex = selectedIndex)
            }
        )

    }

}

@Composable
fun BottomBar(selectedIndex: MutableIntState) {
    TODO("Not yet implemented")
}

@Composable
fun CustomTopAppBar() {
    TODO("Not yet implemented")
}
