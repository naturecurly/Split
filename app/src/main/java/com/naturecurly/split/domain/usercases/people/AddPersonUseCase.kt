package com.naturecurly.split.domain.usercases.people

import com.naturecurly.split.domain.repositories.PeopleRepository
import javax.inject.Inject

class AddPersonUseCase @Inject constructor(private val peopleRepository: PeopleRepository) {

    fun addPerson(name: String) = peopleRepository.insertPerson(name)
}