package com.naturecurly.split.injection.modules

import android.app.Application
import android.content.Context
import com.naturecurly.split.SplitApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Leon Wu
 */

@Module
class ApplicationModule(private val application: SplitApplication) {

    @Singleton
    @Provides
    fun provideApplication(): Application = application

    @Singleton
    @Provides
    fun provideApplicationContext(): Context = application
}