package com.example.chapter4.viewModel.FragmentKeranjang

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chapter4.cart.AdapterCart
import com.example.chapter4.databaseRom.ModalDataDao

class ViewModelKeranjang:ViewModel() {
    private lateinit var modalDataDao: ModalDataDao
    private lateinit var adapterCart: AdapterCart
    private val totalHarga: MutableLiveData<Int> = MutableLiveData()

    // Inisialisasi ViewModel dengan data yang dibutuhkan
    fun initialize(modalDataDao: ModalDataDao, adapterCart: AdapterCart) {
        this.modalDataDao = modalDataDao
        this.adapterCart = adapterCart
    }

    // Fungsi untuk mendapatkan LiveData dari total harga
    fun getTotalHarga(): LiveData<Int> {
        return totalHarga
    }

    // Fungsi untuk memperbarui data dalam ViewModel
    fun updateData() {
        modalDataDao.getAllItem().observeForever { listCart ->
            adapterCart.setDataCartList(listCart)

            val total = adapterCart.getTotalHarga()
            totalHarga.value = total
        }
    }
}