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
    R.drawable.sukabumi to R.string.sukabumi,
    R.drawable.cianjur to R.string.cianjur,
    R.drawable.cilacap to R.string.cilacap,
    R.drawable.gunung_kidul to R.string.gunung_kidul,
    R.drawable.pacitan to R.string.pacitan,
    R.drawable.pangandaran to R.string.pangandaran,
    R.drawable.pelabuhan_ratu to R.string.pelabuhan_ratu,
    R.drawable.tulungagung to R.string.tulungagung,
).map {
    EvacuationRouteModel(it.first, it.second)
}