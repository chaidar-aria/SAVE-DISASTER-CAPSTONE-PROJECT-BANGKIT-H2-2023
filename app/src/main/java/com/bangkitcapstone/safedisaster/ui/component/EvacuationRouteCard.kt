package com.bangkitcapstone.safedisaster.ui.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.safedisaster.model.EvacuationRouteModel
import com.bangkitcapstone.safedisaster.ui.navigation.Destination
import com.bangkitcapstone.safedisaster.ui.theme.BrownLight
import com.bangkitcapstone.safedisaster.ui.theme.PurpleMain


@Composable
fun EvacuationRouteCard(

    evacuationRouteModel: EvacuationRouteModel,
    onClick: (Int) -> Unit
) {
    val textCategory = stringResource(evacuationRouteModel.textCategoryEvacuationRoute)
    Log.d("EvacuationRouteCard", "textCategoryCard: $textCategory")

    Card(
        colors = CardDefaults.cardColors(
            containerColor = BrownLight
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp) // Penambahan padding untuk Card
            .clickable { onClick(evacuationRouteModel.textCategoryEvacuationRoute) }

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) { // Menengahkan konten secara vertikal
            Image(
                painter = painterResource(evacuationRouteModel.imageCategoryEvacuationRoute),
                contentDescription = "Jalur Evakuasi",
                modifier = Modifier
                    .padding(16.dp) // Pengaturan padding yang lebih seimbang
                    .requiredWidth(100.dp)
                    .height(100.dp) // Menyesuaikan ukuran gambar
            )
            Column(
                modifier = Modifier
                    .padding(16.dp) // Penyesuaian padding
            ) {
                Text(
                    text = stringResource(evacuationRouteModel.textCategoryEvacuationRoute),
                    textAlign = TextAlign.Start, // Mengubah TextAlign
                    color = PurpleMain
                )
                Spacer(modifier = Modifier.height(8.dp)) // Menambahkan spacer
                Text(
                    text = "Lihat lebih detail",
                    textAlign = TextAlign.Start, // Mengubah TextAlign
                    color = PurpleMain
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EvacuationRouteCardPreview() {
    EvacuationRouteCard(
        evacuationRouteModel = EvacuationRouteModel(
            imageCategoryEvacuationRoute = 0,
            textCategoryEvacuationRoute = 0
        ),
        onClick = {}
    )
}
