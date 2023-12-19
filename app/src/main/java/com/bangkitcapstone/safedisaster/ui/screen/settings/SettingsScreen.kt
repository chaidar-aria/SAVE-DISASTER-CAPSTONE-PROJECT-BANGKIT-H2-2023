package com.bangkitcapstone.safedisaster.ui.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.safedisaster.ui.theme.BrownDark
import com.bangkitcapstone.safedisaster.ui.theme.BrownVeryLight

@Composable
fun SettingsScreen() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = BrownVeryLight // Use the Color class to represent the color
    ) {

        Column(
            modifier = Modifier
                .padding(top = 50.dp, start = 16.dp, end = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .fillMaxWidth()
                    .background(
                        color = BrownDark,
                        shape = RoundedCornerShape(
                            50.dp
                        )
                    )
            )
            NotificationItem(onClick = {})
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .fillMaxWidth()
                    .background(
                        color = BrownDark,
                        shape = RoundedCornerShape(
                            50.dp
                        )
                    )
            )
        }

    }

}

@Composable
fun NotificationItem(onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = "Settings",
            tint = Color.Gray
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = "Pengaturan",
            color = Color.Gray
        )
        Spacer(Modifier.weight(1f))
        Icon(
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = "Go to settings",
            tint = Color.Gray
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SettingsScreen()
}