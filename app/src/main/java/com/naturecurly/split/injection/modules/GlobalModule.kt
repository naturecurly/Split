package com.naturecurly.split.injection.modules

import android.app.Application
import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.naturecurly.split.data.managers.PeopleManager
import com.naturecurly.split.data.room.PersonDatabase
import com.naturecurly.split.domain.repositories.PeopleRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Leon Wu
 */

@Module
class GlobalModule {

    private companion object {
        const val DATABASE_NAME = "split_db"
    }

    @Singleton
    @Provides
    fun providePersonDataBase(application: Application): PersonDatabase = Room.databaseBuilder(application, PersonDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providePeopleRepository(peopleManager: PeopleManager): PeopleRepository = peopleManager
}