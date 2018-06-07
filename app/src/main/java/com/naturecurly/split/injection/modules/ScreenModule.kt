package com.naturecurly.split.injection.modules

import android.app.Activity
import com.naturecurly.split.injection.scopes.PerScreen
import dagger.Module
import dagger.Provides

/**
 * @author Leon Wu
 */

@Module
class ScreenModule(private val activity: Activity) {

    @Provides
    @PerScreen
    fun provideActivity(): Activity = activity
}