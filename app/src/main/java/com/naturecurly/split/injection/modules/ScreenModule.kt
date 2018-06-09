package com.naturecurly.split.injection.modules

import android.app.Activity
import androidx.recyclerview.widget.GridLayoutManager
import com.naturecurly.split.injection.scopes.PerScreen
import dagger.Module
import dagger.Provides

/**
 * @author Leon Wu
 */

@Module
class ScreenModule(private val activity: Activity) {

    private companion object {
        const val SPAN_NUMBER = 2
    }

    @Provides
    @PerScreen
    fun provideActivity(): Activity = activity

    @Provides
    @PerScreen
    fun provideGridLayoutManager(activity: Activity): GridLayoutManager = GridLayoutManager(activity, SPAN_NUMBER)
}