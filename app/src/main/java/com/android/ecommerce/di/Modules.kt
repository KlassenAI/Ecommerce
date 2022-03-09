package com.android.ecommerce.di

import com.android.ecommerce.data.db.AppDatabase
import com.android.ecommerce.data.network.RetrofitInstance
import com.android.ecommerce.presentation.viewmodel.ExceptionHandler
import com.android.feature_cart.data.CartRepository
import com.android.feature_cart.data.db.CartDao
import com.android.feature_cart.data.network.CartService
import com.android.feature_cart.presentation.viewmodel.CartViewModel
import com.android.feature_details.data.DetailsRepository
import com.android.feature_details.data.db.DetailsDao
import com.android.feature_details.data.network.DetailsService
import com.android.feature_details.presentation.viewmodel.DetailsViewModel
import com.android.feature_main.data.HomeRepository
import com.android.feature_main.data.db.MainDao
import com.android.feature_main.data.network.MainService
import com.android.feature_main.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {

    // Network
    single<HttpLoggingInterceptor>  { RetrofitInstance.getHttpLogger() }
    single<OkHttpClient> { RetrofitInstance.getHttpClient(get()) }
    single<Retrofit> { RetrofitInstance.getRetrofit(get()) }

    // Database
    single<AppDatabase> { AppDatabase.getDatabase(get()) }

    // For ViewModel
    single<CoroutineExceptionHandler> { ExceptionHandler.get() }
}

val mainModule = module {
    single<MainService> { RetrofitInstance.getMainService(get()) }
    single<MainDao> { AppDatabase.getMainDao(get()) }
    single<HomeRepository> { HomeRepository(get(), get(), get()) }
    viewModel<MainViewModel> { MainViewModel(get(), get()) }
}

val detailsModule = module {
    single<DetailsService> { RetrofitInstance.getDetailsService(get()) }
    single<DetailsDao> { AppDatabase.getDetailsDao(get()) }
    single<DetailsRepository> { DetailsRepository(get(), get()) }
    viewModel<DetailsViewModel> { DetailsViewModel(get(), get()) }
}

val cartModule = module {
    single<CartService> { RetrofitInstance.getCartService(get()) }
    single<CartDao> { AppDatabase.getCartDao(get()) }
    single<CartRepository> { CartRepository(get(), get()) }
    viewModel<CartViewModel> { CartViewModel(get(), get()) }
}