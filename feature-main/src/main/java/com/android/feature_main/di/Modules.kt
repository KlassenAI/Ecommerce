package com.android.feature_main.di

import com.android.feature_main.data.HomeRepository
import com.android.feature_main.data.db.MainDao
import com.android.feature_main.data.db.MainDatabase
import com.android.feature_main.data.network.RetrofitInstance
import com.android.feature_main.data.network.RetrofitService
import com.android.feature_main.presentation.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val mainDataModule = module {
    // Network
    single<HttpLoggingInterceptor>  { RetrofitInstance.getHttpLogger() }
    single<OkHttpClient> { RetrofitInstance.getHttpClient(get()) }
    single<Retrofit> { RetrofitInstance.getRetrofit(get()) }
    single<RetrofitService> { RetrofitInstance.getService(get()) }
    // Database
    single<MainDatabase> { MainDatabase.getDatabase(get()) }
    single<MainDao> { MainDatabase.getDao(get()) }
    // Repository
    single<HomeRepository> { HomeRepository(get(), get(), get()) }
}

val mainPresentationModule = module {
    viewModel<MainViewModel> { MainViewModel(get()) }
}