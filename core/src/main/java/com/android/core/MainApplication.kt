package com.android.core

import android.app.Application
import com.android.feature_main.di.dataModule
import com.android.feature_main.di.presentationModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            //androidContext(applicationContext)
            modules(
                listOf(
                    presentationModule,
                    dataModule
                )
            )
        }
    }
}