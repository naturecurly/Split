package com.naturecurly.split.domain.usercases.people

import com.naturecurly.split.domain.repositories.PeopleRepository
import javax.inject.Inject

/**
 * @author Leon Wu
 */
class DeletePersonUseCase @Inject constructor(private val peopleRepository: PeopleRepository) {

    fun deletePerson(id: Long) = peopleRepository.deletePerson(id)
}