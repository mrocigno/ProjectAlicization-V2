package br.com.mrocigno.alicization

import android.app.Application
import br.com.mrocigno.alicization.common.di.KoinModules.initialize
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AlicizationApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            initialize()
        }
    }
}