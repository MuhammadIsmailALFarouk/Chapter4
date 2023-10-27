package com.example.chapter4.listmenu


import androidx.annotation.Keep
import com.example.chapter4.listmenu.Data
import com.google.gson.annotations.SerializedName

@Keep
data class ListMenu(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data : List<Data>?,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)