package com.bangkitcapstone.safedisaster.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.bangkitcapstone.safedisaster.R

data class ClasterizationModel(
    @DrawableRes val imageCategoryClasterization: Int,
    @StringRes val textCategoryClasterization: Int
)

val clasterizationList = listOf(
    R.drawable.peta_klasterisasi_2022 to R.string.peta_klasterisasi_2022,
    R.drawable.peta_klasterisasi_2023 to R.string.peta_klasterisasi_2023,
).map {
    ClasterizationModel(it.first, it.second)
}