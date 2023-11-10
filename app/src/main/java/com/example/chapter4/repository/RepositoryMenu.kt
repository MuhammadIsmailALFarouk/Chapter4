package com.example.chapter4.repository

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chapter4.api.ApiClient
import com.example.chapter4.listmenu.ListMenu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryMenu {
    var _listMenu: MutableLiveData<List<com.example.chapter4.listmenu.Data>> = MutableLiveData()
    val listMenu: LiveData<List<com.example.chapter4.listmenu.Data>> get() = _listMenu

    fun fetchListMenu(query: String? = null) {
        ApiClient.instance.getAllListMenu(query).enqueue(object : Callback<ListMenu> {
            override fun onResponse(call: Call<ListMenu>, response: Response<ListMenu>) {
                if (response.isSuccessful) {
                    _listMenu.postValue(response.body()?.data!!)
                } else {
                    // Tindakan jika terjadi kegagalan
                    Log.i("failListMenu", response.message())
                }
            }

            override fun onFailure(call: Call<ListMenu>, t: Throwable) {
                // Tindakan jika request gagal
            }
        })
    }
}
