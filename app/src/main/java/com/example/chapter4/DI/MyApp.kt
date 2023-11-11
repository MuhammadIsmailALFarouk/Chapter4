package com.example.chapter4.DI

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.chapter4.repository.RepositoryMenu
import com.example.chapter4.repository.ViewModelAdapterFactory
import com.example.chapter4.viewModel.FragmentHome.ViewModelAdapter

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

// MyApp.kt (atau kelas Application Anda)
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}

// Modul Koin
val appModule = module {
    single { RepositoryMenu() } // Mendeklarasikan RepositoryMenu sebagai Singleton
    factory { ViewModelAdapterFactory(get())  } // Mendeklarasikan ViewModelAdapterFactory sebagai Factory
    viewModel { ViewModelAdapter(get()) } // Mendeklarasikan ViewModelAdapter sebagai ViewModel
//    viewModel { ViewModelAdapter(get()) } // Mendeklarasikan ViewModelAdapter sebagai ViewModel
}
