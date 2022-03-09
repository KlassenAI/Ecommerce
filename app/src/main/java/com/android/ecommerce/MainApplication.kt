package com.android.ecommerce

import android.app.Application
import com.android.ecommerce.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(applicationContext)
            modules(listOf(appModule, mainModule, detailsModule, cartModule))
        }
    }
}