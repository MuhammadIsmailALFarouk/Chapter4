package com.example.chapter4.viewModel.FragmentDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val counter : MutableLiveData<Int> = MutableLiveData(0)
    val counter1 :LiveData<Int> get() = counter


    fun incremet(){
        counter.postValue(counter.value?.plus(1))

    }
    fun decrement(){
        counter.value?.let {
            if (it > 0) {
                counter.postValue(counter.value?.minus(1))
            }
        }
    }
}