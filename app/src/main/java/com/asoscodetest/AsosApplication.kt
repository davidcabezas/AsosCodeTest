package com.asoscodetest

import android.app.Application
import com.asoscodetest.di.AppComponent
import com.asoscodetest.di.DaggerAppComponent
import com.asoscodetest.di.module.ContextModule

/**
 * Created by David C. on 18/11/2018.
 */
class AsosApplication : Application() {

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerAppComponent.builder().contextModule(ContextModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)

    }

}