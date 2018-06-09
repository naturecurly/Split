package com.naturecurly.split.injection.components

import com.naturecurly.split.SplitApplication
import com.naturecurly.split.domain.repositories.PeopleRepository
import com.naturecurly.split.injection.modules.ApplicationModule
import com.naturecurly.split.injection.modules.GlobalModule
import dagger.Component
import javax.inject.Singleton

/**
 * @author Leon Wu
 */
@Singleton
@Component(modules = [ApplicationModule::class, GlobalModule::class])
interface ApplicationComponent {
    fun inject(application: SplitApplication)

    fun peopleRepository(): PeopleRepository
}