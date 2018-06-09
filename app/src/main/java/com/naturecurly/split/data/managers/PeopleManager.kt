package com.naturecurly.split.data.managers

import android.content.Context
import com.naturecurly.split.R
import com.naturecurly.split.data.room.PersonDatabase
import com.naturecurly.split.domain.data.Person
import com.naturecurly.split.domain.repositories.PeopleRepository
import com.naturecurly.split.data.room.Person as PersonData
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class PeopleManager @Inject constructor(private val context: Context,
                                        private val personDatabase: PersonDatabase) : PeopleRepository {

    override fun getAllPeople(): Maybe<List<Person>> =
            personDatabase.personDao().getAllPeople()
                    .map { it.map { com.naturecurly.split.domain.data.Person(it.id!!, it.name, it.backgroundColor) } }
                    .subscribeOn(Schedulers.io())

    override fun insertPerson(personName: String) =
            Single.fromCallable<Unit> {
                personDatabase.personDao().insert(PersonData(name = personName, backgroundColor = randamColor()))
            }.subscribeOn(Schedulers.io())

    private fun randamColor(): Int {
        val colorArray = context.resources.getIntArray(R.array.avatar_background_list)
        val random = Random()
        return colorArray.get(random.nextInt(colorArray.size))
    }
}