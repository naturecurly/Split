package com.naturecurly.split.domain.repositories

import com.naturecurly.split.domain.data.Person
import io.reactivex.Maybe
import io.reactivex.Single

interface PeopleRepository {
    fun getAllPeople(): Maybe<List<Person>>
    fun insertPerson(personName: String): Single<Unit>
}