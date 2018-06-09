package com.naturecurly.split

import android.app.Application
import com.naturecurly.split.injection.components.ApplicationComponent
import com.naturecurly.split.injection.components.DaggerApplicationComponent
import com.naturecurly.split.injection.modules.ApplicationModule

class SplitApplication : Application() {

    companion object {
        @JvmStatic
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    // region Private Functions
    private fun initDagger() {
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
        appComponent.inject(this)
    }
    // endregion
}