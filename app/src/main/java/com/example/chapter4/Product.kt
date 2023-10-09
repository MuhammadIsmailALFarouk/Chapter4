package com.example.chapter4

import androidx.room.Embedded

data class Product(
    @Embedded
    val cart:Cart

)
