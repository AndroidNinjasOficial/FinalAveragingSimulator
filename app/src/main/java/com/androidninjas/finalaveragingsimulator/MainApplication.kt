package com.androidninjas.finalaveragingsimulator

import android.app.Application
import com.androidninjas.finalaveragingsimulator.util.ModelPreferencesManager
import java.lang.IllegalStateException

class MainApplication : Application() {

    companion object {
        private var appInstance: MainApplication? = null

        fun getInstance(): MainApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configure a classe de Application no AndroidManifest.xml")
            } else {
                return appInstance!!
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        ModelPreferencesManager.with(this)
    }
}