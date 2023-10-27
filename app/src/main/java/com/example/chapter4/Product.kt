package com.example.chapter4

import androidx.room.Embedded
import com.example.chapter4.cart.Cart

data class Product(
    @Embedded
    val cart: Cart

)
