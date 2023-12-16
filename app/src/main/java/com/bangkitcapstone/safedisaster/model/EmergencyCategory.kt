package com.bangkitcapstone.safedisaster.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.bangkitcapstone.safedisaster.R

data class EmergencyCategory (
    @DrawableRes val imageCategory: Int,
    @StringRes val textCategory: Int
)

val emergencyCategoryList = listOf(
    R.drawable.logo_bnpb to R.string.call_bnpb,
    R.drawable.basarnas_logo to R.string.call_basarnas,
    R.drawable.logo_tribrata to R.string.call_police
).map { EmergencyCategory(it.first, it.second) }