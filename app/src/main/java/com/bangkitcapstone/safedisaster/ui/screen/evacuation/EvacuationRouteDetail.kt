package com.bangkitcapstone.safedisaster.ui.screen.evacuation

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material.Surface
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import com.bangkitcapstone.safedisaster.R
import com.bangkitcapstone.safedisaster.model.EvacuationRouteModel
import com.bangkitcapstone.safedisaster.ui.theme.BrownDark
import com.bangkitcapstone.safedisaster.ui.theme.BrownSemiLight
import com.bangkitcapstone.safedisaster.ui.theme.BrownVeryLight
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun EvacuationRouteDetail(model: EvacuationRouteModel, textCategory: String?) {
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(BrownVeryLight),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = model.imageCategoryEvacuationRoute),
                contentDescription = stringResource(id = model.textCategoryEvacuationRoute),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .clickable { showDialog = true }  // Event klik untuk menampilkan dialog
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(id = model.textCategoryEvacuationRoute),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Legenda / Keterangan Gambar",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentHeight(align = Alignment.Top)
            ) {
                Text(
                    text = "Garis Merah: Jalur yang harus dilewati saat evakuasi.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Text(
                    text = "Garis Tebal: Jalan utama yang bisa dilewati banyak orang.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Text(
                    text = "Warna Biru: Area yang ada air, tidak bisa dilewati.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Text(
                    text = "Warna Terang: Daratan yang bisa digunakan untuk evakuasi.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Text(
                    text = "Nama Tempat: Daerah yang ditunjukkan dalam peta.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }
    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Image(
                painter = painterResource(id = model.imageCategoryEvacuationRoute),
                contentDescription = stringResource(id = model.textCategoryEvacuationRoute),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun EvacuationRouteDetailPreview() {
//    EvacuationRouteDetail(
//        model = EvacuationRouteModel(
//            imageCategoryEvacuationRoute = R.drawable.garut,
//            textCategoryEvacuationRoute = R.string.garut
//        ),
//        textCategory = "1"
//    )
//}
