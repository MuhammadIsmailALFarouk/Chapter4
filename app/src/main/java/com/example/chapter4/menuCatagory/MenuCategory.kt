package com.example.chapter4.menuCatagory


import com.example.chapter4.menuCatagory.DataX
import com.google.gson.annotations.SerializedName

data class MenuCategory(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<DataX>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)