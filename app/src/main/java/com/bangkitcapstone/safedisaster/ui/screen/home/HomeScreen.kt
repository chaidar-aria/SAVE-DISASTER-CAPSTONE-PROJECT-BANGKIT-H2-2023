package com.bangkitcapstone.safedisaster.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
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
import com.bangkitcapstone.safedisaster.R
import com.bangkitcapstone.safedisaster.model.emergencyCategoryList
import com.bangkitcapstone.safedisaster.ui.component.EmergencyNumberCard
import com.bangkitcapstone.safedisaster.ui.theme.BrownLight
import com.bangkitcapstone.safedisaster.ui.theme.LightRed
import com.bangkitcapstone.safedisaster.ui.theme.OldRed
import com.bangkitcapstone.safedisaster.ui.theme.PurpleMain

@Composable
fun HomeScreen() {
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
            val ( culm) = createRefs()
            Row {
                Column {
                    Text(
                        text = "Selamat Siang",
                        color = Color.White,
                        modifier = Modifier.padding(top = 32.dp, start = 32.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "Ini namamu",
                        color = Color.White,
                        modifier = Modifier.padding(top = 10.dp, start = 32.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "profile_screen_bg",
                    modifier = Modifier
                        .padding(top = 32.dp, start = 50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .requiredHeight(50.dp)
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
                        containerColor = BrownLight
                    ),
                    modifier = Modifier
                        .size(width = 300.dp, height = 150.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Row {
                        Image(
                            painterResource(id = R.drawable.cloud) ,
                            contentDescription = "Cuaca hari ini",
                            modifier = Modifier
                                .padding(top = 53.dp, start = 16.dp)
                                .requiredWidth(50.dp)

                        )
                        Column(
                            modifier = Modifier.padding
                                (start=23.dp)
                        ) {
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
                                    .padding(top= 5.dp),
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
                        .size(width = 345.dp, height = 150.dp)
                        .padding(top = 10.dp)
                ) {
                    Row {
                        Column(modifier = Modifier.padding(start = 14.dp)){
                            Text(
                                text = "Waspada Gempa Bumi",
                                color = OldRed,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 15.dp)
                            )
                            Text(
                                text = "Pusat Gempa: Barat Daya Malang",
                                color = OldRed,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                            Text(
                                text = "Magnitudo: 5.5",
                                color = OldRed,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                            Text(
                                text = "BERPOTENSI TSUNAMI!",
                                color = OldRed,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        }
                        Text(
                            text = "Ini nanti diisi icon ya chai",
                            color = PurpleMain
                        )
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