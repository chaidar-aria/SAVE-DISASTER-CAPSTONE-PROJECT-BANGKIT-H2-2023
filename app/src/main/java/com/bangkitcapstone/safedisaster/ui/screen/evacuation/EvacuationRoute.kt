package com.bangkitcapstone.safedisaster.ui.screen.evacuation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bangkitcapstone.safedisaster.model.evacuationRouteList
import com.bangkitcapstone.safedisaster.ui.component.EvacuationRouteCard
import com.bangkitcapstone.safedisaster.ui.navigation.Actions
import com.bangkitcapstone.safedisaster.ui.theme.BrownVeryLight

@Composable
fun EvacuationRoute(actions: Actions, navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = BrownVeryLight
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = 64.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(evacuationRouteList, key = { it.textCategoryEvacuationRoute }) { evacuationRoute ->
                EvacuationRouteCard(evacuationRoute, onClick = { routeId ->
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

