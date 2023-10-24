package com.example.chapter4.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

// category Menu
@Parcelize
data class Data(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("updatedAt")
    val updatedAt: String
):Parcelable