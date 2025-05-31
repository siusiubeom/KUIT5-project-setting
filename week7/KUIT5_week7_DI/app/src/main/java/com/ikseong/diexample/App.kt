package com.ikseong.diexample

import android.app.Application

class App: Application() {
    /*lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer()
    }*/
    val appContainer by lazy { AppContainer() }
}