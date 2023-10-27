package com.example.chapter4.api

import com.example.chapter4.listmenu.ListMenu
import com.example.chapter4.menuCatagory.MenuCategory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET ("listmenu")
    fun getAllListMenu(@Query("c") c : String? = null): Call<ListMenu>

    @GET ("catagory")
    fun getAllCatagoryMenu(): MenuCategory
}