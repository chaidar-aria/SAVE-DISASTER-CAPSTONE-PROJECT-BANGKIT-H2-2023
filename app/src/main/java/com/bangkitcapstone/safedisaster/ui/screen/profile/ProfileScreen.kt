package com.bangkitcapstone.safedisaster.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.rememberNavController
import com.bangkitcapstone.safedisaster.R
import com.bangkitcapstone.safedisaster.ui.navigation.Actions
import com.bangkitcapstone.safedisaster.ui.theme.BrownDark
import com.bangkitcapstone.safedisaster.ui.theme.PurpleMain

@Composable
fun ProfileScreen(actions: Actions) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = PurpleMain // Use the Color class to represent the color
    ) {
        ConstraintLayout(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            val (overlay, culm) = createRefs()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .constrainAs(overlay) {
                        top.linkTo(parent.top)
                    }
                    .padding(17.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Profil",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Data diri pengguna",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(750.dp)
                    .constrainAs(culm) {
                        bottom.linkTo(parent.bottom)
                    }
                    .background(
                        color = Color(android.graphics.Color.parseColor("#F3EEEA")),
                        shape = RoundedCornerShape(
                            topStart = 40.dp, topEnd = 40.dp
                        )
                    )
                    .padding(32.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "profile_screen_bg",
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .requiredHeight(100.dp)
                        .fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth() // Mengisi lebar maksimum
                        .padding(top = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally // Menengahkan secara horizontal
                ) {
                    Text(
                        text = "NAMA ANDA NANTI DISINI",
                        color = PurpleMain,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                UserProfileCard()

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
                SettingsItem(openSettings = actions.openSettings)
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
}

@Composable
fun SettingsItem(openSettings: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = openSettings)
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

@Composable
fun UserProfileCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        ProfileDetail(label = "Nomor Telepon", value = "+6281 234 567 890")
        ProfileDetail(label = "Email", value = "randy@mail.com")
        ProfileDetail(label = "Nama", value = "Randy Mango")
        ProfileDetail(label = "Tempat Lahir", value = "Surabaya")
        ProfileDetail(label = "Tanggal Lahir", value = "10 Januari 1998")
        ProfileDetail(label = "Alamat", value = "Jalan Ketintang No 11 Gayungan, Kota Surabaya")
        ProfileDetail(label = "Kode Pos", value = "60231")
    }
}

@Composable
fun ProfileDetail(label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "$label",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
    }
    Spacer(
        modifier = Modifier
            .height(8.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewDefault() {
    // Create a fake Actions object with no-op lambdas for the preview
    val fakeActions = Actions(navController = rememberNavController()).apply {
        // Override the actions to do nothing in preview
        openSettings = {}
    }

    // Now pass the fakeActions to the ProfileScreen
    ProfileScreen(actions = fakeActions)
}
