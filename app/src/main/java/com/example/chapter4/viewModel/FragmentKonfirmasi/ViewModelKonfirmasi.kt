package com.example.chapter4.viewModel.FragmentKonfirmasi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chapter4.databaseRom.ModalDataDao

class ViewModelKonfirmasi: ViewModel()  {
    private val totalHarga = MutableLiveData<Int>()


    fun setTotalHarga(harga: Int) {
        totalHarga.value = harga
    }

    fun getTotalHarga(): LiveData<Int> {
        return totalHarga
    }


    // menghapus semua item di keranjang
    fun deleteAllItemsFromCart(dataDao: ModalDataDao) {
        dataDao.deleteAllItemCart()
    }



}