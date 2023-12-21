package com.bangkitcapstone.safedisaster.model.response

import com.google.gson.annotations.SerializedName

data class BmkgEarthQuakeResponse(

	@field:SerializedName("Infogempa")
	val infogempa: Infogempa? = null
)

data class Gempa(

	@field:SerializedName("Dirasakan")
	val dirasakan: String? = null,

	@field:SerializedName("Wilayah")
	val wilayah: String? = null,

	@field:SerializedName("Shakemap")
	val shakemap: String? = null,

	@field:SerializedName("Kedalaman")
	val kedalaman: String? = null,

	@field:SerializedName("Jam")
	val jam: String? = null,

	@field:SerializedName("Coordinates")
	val coordinates: String? = null,

	@field:SerializedName("Potensi")
	val potensi: String? = null,

	@field:SerializedName("Tanggal")
	val tanggal: String? = null,

	@field:SerializedName("Bujur")
	val bujur: String? = null,

	@field:SerializedName("Magnitude")
	val magnitude: String? = null,

	@field:SerializedName("Lintang")
	val lintang: String? = null,

	@field:SerializedName("DateTime")
	val dateTime: String? = null
)

data class Infogempa(

	@field:SerializedName("gempa")
	val gempa: Gempa? = null
)
