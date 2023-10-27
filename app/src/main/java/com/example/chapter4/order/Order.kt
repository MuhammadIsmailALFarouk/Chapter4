package com.example.chapter4.order


import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)