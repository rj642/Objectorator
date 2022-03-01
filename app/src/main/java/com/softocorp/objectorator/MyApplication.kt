package com.softocorp.objectorator

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}