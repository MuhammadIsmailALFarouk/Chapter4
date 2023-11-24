package com.example.chapter4.viewModel.FragmentDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    // Aturan untuk menjalankan kode secara synchronously menggunakan peraturan Android Architecture Components
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    @Test
    fun getCounter_initialValueIsZero() {
        assertEquals(0, viewModel.counter1.value)
    }

    @Test
    fun increment_counterIncrements() {
        viewModel.incremet()
        assertEquals(1, viewModel.counter1.value)
    }

    @Test
    fun decrement_whenGreaterThanZero_counterDecrements() {
        viewModel.incremet() // Increment first to have a value to decrement from
        viewModel.decrement()
        assertEquals(0, viewModel.counter1.value)
    }

    @Test
    fun decrement_whenZero_counterStaysZero() {
        viewModel.decrement()
        assertEquals(0, viewModel.counter1.value)
    }
}

