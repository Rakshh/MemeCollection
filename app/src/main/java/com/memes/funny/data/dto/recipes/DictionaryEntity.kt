package com.task.data.dto.recipes


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class DictionaryEntity(
    @Json(name = "eword")
    val eword: String = "",
    @Json(name = "hword")
    val hword: String = "",
    @Json(name = "egrammar")
    val egrammar: String = ""
) : Parcelable