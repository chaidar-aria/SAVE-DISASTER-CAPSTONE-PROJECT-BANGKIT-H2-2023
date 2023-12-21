package com.bangkitcapstone.safedisaster.ui.screen.evacuation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bangkitcapstone.safedisaster.model.emergencyCategoryList
import com.bangkitcapstone.safedisaster.model.evacuationRouteList
import com.bangkitcapstone.safedisaster.ui.component.EmergencyNumberCard
import com.bangkitcapstone.safedisaster.ui.component.EvacuationRouteCard
import com.bangkitcapstone.safedisaster.ui.navigation.Actions
import com.bangkitcapstone.safedisaster.ui.theme.BrownVeryLight
import com.bangkitcapstone.safedisaster.ui.theme.PurpleMain

@Composable
fun EvacuationRoute(actions: Actions, navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = BrownVeryLight // Use the Color class to represent the color
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(evacuationRouteList, key = { it.textCategoryEvacuationRoute }) { evacuationRoute ->
                EvacuationRouteCard(evacuationRoute, onClick = { routeId ->
                    // Gunakan routeId untuk membuka detail rute evakuasi
                    actions.openEvacuationRouteDetail(navController, routeId)
                    Log.d("EvacuationRoute", "EvacuationRoute: $routeId")
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EvacuationRoutePreview() {
    EvacuationRoute(
        actions = Actions(navController = NavController(LocalContext.current)),
        navController = NavController(LocalContext.current)
    )
}

