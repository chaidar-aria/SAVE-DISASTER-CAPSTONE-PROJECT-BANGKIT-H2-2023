package com.bangkitcapstone.safedisaster.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.bangkitcapstone.safedisaster.R

data class EmergencyCategory (
    @DrawableRes val imageCategory: Int,
    @StringRes val textCategory: Int,
    val emergencyNumber: Int
)

val emergencyCategoryList = listOf(
    Triple(R.drawable.logo_bnpb, R.string.call_bnpb, R.string.number_bnpb),
    Triple(R.drawable.basarnas_logo, R.string.call_basarnas, R.string.number_basarnas),
    Triple(R.drawable.logo_tribrata, R.string.call_police, R.string.number_police)
).map { EmergencyCategory(it.first, it.second, it.third) }
