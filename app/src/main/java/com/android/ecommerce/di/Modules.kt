package com.android.ecommerce.di

import com.android.ecommerce.data.network.RetrofitInstance
import com.android.ecommerce.data.network.RetrofitService
import com.android.ecommerce.presentation.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {

    single<OkHttpClient> { RetrofitInstance.getHttpClient() }

    single<Retrofit> { RetrofitInstance.getRetrofit(get()) }

    single<RetrofitService> { RetrofitInstance.getService(get()) }
}

val presentationModule = module {

    viewModel<MainViewModel> { MainViewModel(get()) }
}