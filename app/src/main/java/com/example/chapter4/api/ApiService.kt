package com.example.chapter4.api

import com.example.chapter4.model.ListMenu
import com.example.chapter4.model.MenuCatagory
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET ("listmenu")
    fun getAllListMenu(): Call<List<ListMenu>>

    @GET ("catagory-menu")
    fun getAllCatagoryMenu():Call<List<MenuCatagory>>
}