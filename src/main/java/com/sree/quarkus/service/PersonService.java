package com.sree.quarkus.service;


import com.sree.quarkus.dto.PersonDto;
import com.sree.quarkus.entities.Person;

import java.util.List;

/**
 * This interface is used to implement multiple times according to our needs.
 */
public interface PersonService {

    List<Person> findAll();

    Long createPerson(PersonDto personDto);

    Long updatePerson(Long personId, PersonDto personDto);

    void deletePerson(Long id);

}
