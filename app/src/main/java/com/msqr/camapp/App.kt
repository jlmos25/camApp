package com.msqr.camapp

import android.app.Application
import com.msqr.server_list.di.serversModule
import org.koin.core.context.startKoin

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        initKoinApplication()
    }

    private fun initKoinApplication(){
        startKoin {
            modules(serversModule)
        }
    }


}