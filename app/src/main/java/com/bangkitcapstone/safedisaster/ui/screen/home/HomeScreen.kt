package com.bangkitcapstone.safedisaster.ui.screen.home

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bangkitcapstone.safedisaster.R
import com.bangkitcapstone.safedisaster.model.emergencyCategoryList
import com.bangkitcapstone.safedisaster.ui.component.EmergencyNumberCard
import com.bangkitcapstone.safedisaster.ui.theme.BrownLight
import com.bangkitcapstone.safedisaster.ui.theme.LightRed
import com.bangkitcapstone.safedisaster.ui.theme.OldRed
import com.bangkitcapstone.safedisaster.ui.theme.PurpleMain
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val dataGempa = viewModel.dataGempa.collectAsState().value

    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    LaunchedEffect(true) {
        permissionState.launchPermissionRequest()
    }

    if (permissionState.hasPermission) {
        viewModel.getLocationNewest(context)
        val location = viewModel.locationState.observeAsState().value
        val locationName = location?.let {
            viewModel.getLocationName(context, it.latitude, it.longitude)
        } ?: "Mencari Lokasi..."

        // Tampilkan lokasi
        Log.d("HomeScreen", "location: ${location?.longitude}, ${location?.latitude}")
        Log.d("HomeScreen", "locationName: $locationName")
    } else {
        Text("Izin lokasi diperlukan")
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = PurpleMain
    ) {
        ConstraintLayout(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            val (culm) = createRefs()
            Row {
                Column {
                    Text(
                        text = getGreetingMessage(),
                        color = Color.White,
                        modifier = Modifier.padding(top = 32.dp, start = 32.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "SAFE DISASTER",
                        color = Color.White,
                        modifier = Modifier.padding(top = 10.dp, start = 32.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.application_logo),
                    contentDescription = "profile_screen_bg",
                    modifier = Modifier
                        .padding(start = 100.dp)
                        .size(100.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 100.dp)
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
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = BrownLight
                    ),
                    modifier = Modifier
                        .size(width = 300.dp, height = 150.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Row {
                        Image(
                            painterResource(id = R.drawable.cloud),
                            contentDescription = "Cuaca hari ini",
                            modifier = Modifier
                                .padding(top = 53.dp, start = 16.dp)
                                .requiredWidth(50.dp)

                        )
                        Column(
                            modifier = Modifier.padding
                                (start = 23.dp)
                        ) {
//                            val lokasiSekarang = location?.toString() ?: "Locating..."
//                            Log.d("HomeScreen", "lokasiSekarang: $lokasiSekarang")
                            Text(
                                text = "Cuaca di Surabaya",
                                modifier = Modifier
                                    .padding(top = 16.dp),
                                textAlign = TextAlign.Center,
                                color = PurpleMain
                            )

                            Text(
                                text = "Mendung",
                                modifier = Modifier
                                    .padding(top = 5.dp),
                                textAlign = TextAlign.Center,
                                color = PurpleMain
                            )
                            Text(
                                text = "28/32°C Terasa seperti 28°C",
                                modifier = Modifier.padding(top = 5.dp),
                                color = PurpleMain
                            )
                        }
                    }

                }
                Text(
                    text = "Bencana di sekitar anda",
                    color = PurpleMain,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = "Waspada bencana disekitar anda",
                    color = PurpleMain,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = 5.dp)
                )
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = LightRed
                    ),
                    modifier = Modifier
                        .size(width = 345.dp, height = 200.dp)
                        .padding(top = 10.dp)
                ) {
                    Row {
                        Column(modifier = Modifier.padding(start = 14.dp)) {
                            Text(
                                text = "Waspada Gempa Bumi",
                                color = OldRed,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 15.dp)
                            )
                            val latestGempa = dataGempa.firstOrNull()
                            if (latestGempa != null) {
                                Log.d("HomeScreen", "latestGempa: ${latestGempa.infogempa?.gempa}")
                                Text(
                                    text = "Pusat Gempa: ${latestGempa.infogempa?.gempa?.wilayah}",
                                    color = OldRed,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                                Text(
                                    text = "Magnitudo: ${latestGempa.infogempa?.gempa?.magnitude}",
                                    color = OldRed,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                                Text(
                                    text = "${latestGempa.infogempa?.gempa?.potensi}",
                                    color = OldRed,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            } else {
                                // Tampilkan UI alternatif jika data tidak tersedia atau null
                                Text(
                                    text = "Data gempa tidak tersedia\n (Sistem Dalam Perbaikan) \n Namun tetap waspada terhadap Bencana Gempa Bumi",
                                    color = PurpleMain,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(top = 10.dp)
                                )
                            }
                        }
                    }
                }
                Text(
                    text = "Nomor darurat yang bisa diakses",
                    color = PurpleMain,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp)
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp),
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    items(emergencyCategoryList, key = { it.textCategory }) { emergencyCategory ->
                        EmergencyNumberCard(emergencyCategory, onClick = {
                            coroutineScope.launch {
                                dialNumber(context, emergencyCategory.emergencyNumber)
                            }
                        })
                    }
                }
            }
        }
    }
}

private fun dialNumber(context: Context, emergencyNumber: Int) {
    val emergencyNumberStr = context.getString(emergencyNumber)
    Log.d("HomeScreen", "dialNumber: $emergencyNumberStr")

    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$emergencyNumberStr")
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}


fun getGreetingMessage(): String {

    return when (java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)) {
        in 0..11 -> "Selamat Pagi"
        in 12..15 -> "Selamat Siang"
        in 16..18 -> "Selamat Sore"
        else -> "Selamat Malam"
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}