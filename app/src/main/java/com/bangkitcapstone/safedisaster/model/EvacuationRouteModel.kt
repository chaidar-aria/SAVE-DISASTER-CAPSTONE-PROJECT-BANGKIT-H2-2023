package com.bangkitcapstone.safedisaster.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.bangkitcapstone.safedisaster.R

data class EvacuationRouteModel(
    @DrawableRes val imageCategoryEvacuationRoute: Int,
    @StringRes val textCategoryEvacuationRoute: Int
)

val evacuationRouteList = listOf(
    R.drawable.garut to R.string.garut,
    R.drawable.sukabumi to R.string.sukabumi
).map {
    EvacuationRouteModel(it.first, it.second)
}