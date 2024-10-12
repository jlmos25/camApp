package com.msqr.camapp

import android.app.Application
import com.msqr.data.di.dataBaseModule
import com.msqr.data.di.networkModule
import com.msqr.data.di.remoteRepositoryModule
import com.msqr.server_list.di.serversModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        initKoinApplication()
    }

    private fun initKoinApplication(){
        startKoin {
            androidContext(this@App)
            modules(serversModule)
            modules(dataBaseModule)
            modules(networkModule)
            modules(remoteRepositoryModule)
        }
    }


}