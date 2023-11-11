package com.example.chapter4.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chapter4.viewModel.FragmentHome.ViewModelAdapter

class ViewModelAdapterFactory(private val menuRepository: RepositoryMenu) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelAdapter::class.java)) {
            return ViewModelAdapter(menuRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
