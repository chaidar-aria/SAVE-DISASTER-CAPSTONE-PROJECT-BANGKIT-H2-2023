package com.bangkitcapstone.safedisaster.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bangkitcapstone.safedisaster.model.emergencyCategoryList
import com.bangkitcapstone.safedisaster.ui.component.EmergencyNumberCard
import com.bangkitcapstone.safedisaster.ui.theme.SafeDisasterTheme

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = Color(0xFF31304D) // Use the Color class to represent the color
    ) {
        ConstraintLayout(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            val (topText, culm) = createRefs()
            Row {
                Column {
                    Text(
                        text = "Selamat Siang",
                        color = Color.White,
                        modifier = Modifier.padding(top = 32.dp, start = 32.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "Ini namamu",
                        color = Color.White,
                        modifier = Modifier.padding(top = 10.dp, start = 32.dp),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Image(
                    painter = painterResource(id = com.bangkitcapstone.safedisaster.R.drawable.ic_launcher_background),
                    contentDescription = "profile_screen_bg",
                    modifier = Modifier
                        .padding(top = 32.dp, start = 32.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .requiredHeight(100.dp)
                        .fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(675.dp)
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
                        containerColor = Color(0xFFEBE3D5)
                    ),
                    modifier = Modifier
                        .size(width = 300.dp, height = 150.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Row {
                        Text(
                            text = "Ini nanti icon buat\n cuaca ya chai",
                            modifier = Modifier
                                .padding(16.dp),
                            textAlign = TextAlign.Center,
                        )
                        Column {
                            Text(
                                text = "Cuaca di Surabaya",
                                modifier = Modifier
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                            )
                            Text(
                                text = "Mendung",
                                modifier = Modifier
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                            )
                            Text(
                                text = "28/32°C Terasa seperti 28°C",
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }

                }
                Text(
                    text = "Bencana di sekitar anda",
                    color = Color(0xFF31304D),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = "Waspada bencana disekitar anda",
                    color = Color(0xFF31304D),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = 5.dp)
                )
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFCFCF)
                    ),
                    modifier = Modifier
                        .size(width = 345.dp, height = 190.dp)
                        .padding(top = 10.dp)
                ) {
                    Row {
                        Column(modifier = Modifier.padding(start = 14.dp)){
                            Text(
                                text = "Waspada Gempa Bumi",
                                color = Color(0xFF990000),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 40.dp)
                            )
                            Text(
                                text = "Pusat Gempa: Barat Daya Malang",
                                color = Color(0xFF900000),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                            Text(
                                text = "Magnitudo: 5.5",
                                color = Color(0xFF900000),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                            Text(
                                text = "BERPOTENSI TSUNAMI!",
                                color = Color(0xFF990000),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        }
                        Text(
                            text = "Ini nanti diisi icon ya chai"
                        )
                    }
                }
                Text(
                    text = "Nomor darurat yang bisa diakses",
                    color = Color(0xFF31304D),
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
                        EmergencyNumberCard(emergencyCategory)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}