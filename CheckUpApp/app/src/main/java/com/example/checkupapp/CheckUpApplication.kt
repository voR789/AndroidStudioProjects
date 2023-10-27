package com.example.checkupapp

import android.app.Application
import com.example.checkupapp.data.AppContainer
import com.example.checkupapp.data.AppDataContainer

class CheckUpApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */


    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }

}