package com.android.ecommerce

import android.app.Application
import com.android.ecommerce.di.*
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    companion object {
        private const val MAPKIT_API_KEY = "224ebf75-f7c6-4f13-98f0-0d29593625f4"
    }

    override fun onCreate() {
        super.onCreate()

        MapKitFactory.setApiKey(MAPKIT_API_KEY)
        MapKitFactory.initialize(this)

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(applicationContext)
            modules(listOf(appModule, homeModule, detailsModule, cartModule))
        }
    }
}