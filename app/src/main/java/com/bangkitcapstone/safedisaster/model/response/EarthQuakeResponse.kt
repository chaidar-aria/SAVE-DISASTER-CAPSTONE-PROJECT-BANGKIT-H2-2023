package com.bangkitcapstone.safedisaster.model.response

import com.google.gson.annotations.SerializedName

data class EarthQuakeResponse(

    @field:SerializedName("data")
	val data: Data? = null,

    @field:SerializedName("success")
	val success: Boolean? = null,

    @field:SerializedName("message")
	val message: Any? = null
)

data class Data(

	@field:SerializedName("datetime")
	val datetime: String? = null,

	@field:SerializedName("potensi")
	val potensi: String? = null,

	@field:SerializedName("dirasakan")
	val dirasakan: String? = null,

	@field:SerializedName("lintang")
	val lintang: String? = null,

	@field:SerializedName("jam")
	val jam: String? = null,

	@field:SerializedName("coordinates")
	val coordinates: String? = null,

	@field:SerializedName("magnitude")
	val magnitude: String? = null,

	@field:SerializedName("kedalaman")
	val kedalaman: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("wilayah")
	val wilayah: String? = null,

	@field:SerializedName("shakemap")
	val shakemap: String? = null,

	@field:SerializedName("bujur")
	val bujur: String? = null
)
