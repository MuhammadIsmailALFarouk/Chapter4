package com.example.chapter4.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListMenu(
    @SerializedName("data")
    val data: List<DataX>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
):Parcelable