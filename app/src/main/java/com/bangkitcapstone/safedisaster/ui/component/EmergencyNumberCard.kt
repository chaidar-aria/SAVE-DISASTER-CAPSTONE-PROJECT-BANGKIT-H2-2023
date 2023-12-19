package com.bangkitcapstone.safedisaster.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.safedisaster.R
import com.bangkitcapstone.safedisaster.model.EmergencyCategory
import com.bangkitcapstone.safedisaster.ui.theme.BrownLight
import com.bangkitcapstone.safedisaster.ui.theme.PurpleMain
import com.bangkitcapstone.safedisaster.ui.theme.SafeDisasterTheme

@Composable
fun EmergencyNumberCard(
    emergencyCategory: EmergencyCategory,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = BrownLight
            ),
            modifier = Modifier
                .size(width = 71.dp, height = 75.dp)
                .padding(8.dp), // Adjust padding as needed
        ) {
            Image(
                painter = painterResource(emergencyCategory.imageCategory),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
            )
        }
        Text(
            text = stringResource(emergencyCategory.textCategory),
            modifier = Modifier.padding(top = 8.dp),
            color = PurpleMain,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmergencyNumberCardPreview() {
    SafeDisasterTheme {
        EmergencyNumberCard(
            emergencyCategory = EmergencyCategory(
                R.mipmap.icon_bnpb_foreground,
                R.string.call_bnpb,
            ),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}