package com.naturecurly.split.domain.usercases.people

import com.naturecurly.split.domain.repositories.PeopleRepository
import javax.inject.Inject

class GetAllPeopleUseCase @Inject constructor(private val peopleRepository: PeopleRepository) {

    fun getAllPeople() = peopleRepository.getAllPeople()
}