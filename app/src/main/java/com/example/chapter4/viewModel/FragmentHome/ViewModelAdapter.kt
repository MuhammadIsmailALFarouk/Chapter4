package com.example.chapter4.viewModel.FragmentHome

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chapter4.api.ApiClient
import com.example.chapter4.listmenu.Data
import com.example.chapter4.listmenu.ListMenu
import com.example.chapter4.repository.RepositoryMenu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelAdapter(private val repositoryMenu: RepositoryMenu) : ViewModel(){
//    var _listMenu: MutableLiveData<List<Data>> = MutableLiveData()
//    val listMenu: LiveData<List<Data>> get() = _listMenu
    val listMenu:LiveData<List<Data>> = repositoryMenu.listMenu
    fun getListMenu(query: String? = null) {
        repositoryMenu.fetchListMenu(query)
    }
//
//    fun getListMenu(query: String? = null){
//        ApiClient.instance.getAllListMenu(query).enqueue(object : Callback<ListMenu> {
//            override fun onResponse(
//                call: Call<ListMenu>,
//                response: Response<ListMenu>
//            ) {
//                if (response.isSuccessful){
//                    _listMenu.postValue(response.body()?.data!!)
//
//                }else{
//                    Log.i("failListMenu", response.message())
//                }
//            }
//            override fun onFailure(call: Call<ListMenu>, t: Throwable) {
//            }
//
//        })
//    }



}