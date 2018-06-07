package com.naturecurly.split.injection.modules

import com.naturecurly.split.SplitApplication
import dagger.Module

/**
 * @author Leon Wu
 */

@Module
class ApplicationModule(private val application: SplitApplication) {
}