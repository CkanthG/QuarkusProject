package com.sree.quarkus.service.impl;

import com.sree.quarkus.dto.PersonDto;
import com.sree.quarkus.entities.Person;
import com.sree.quarkus.repository.PersonRepository;
import com.sree.quarkus.service.PersonService;
import com.sree.quarkus.service.Translating;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * This implementation class is used Repository to do CRUD operations on Persons.
 */
@Transactional
@ApplicationScoped
@Translating
public class PersonServiceImpl2 implements PersonService {
    @Inject
    PersonRepository personRepository;

    Logger logger = LoggerFactory.getLogger(PersonServiceImpl2.class);

    /**
     * This method is to get all person's data from DB.
     * @return list of person's.
     */
    @Override
    public List<Person> findAll() {
        logger.info("PersonServiceImpl2:findAll method called");
        return personRepository.findAll().stream().toList();
    }

    /**
     * This method is used to save person data.
     * @param personDto
     * @return gives person id
     */
    @Override
    public Long createPerson(PersonDto personDto) {
        logger.info("PersonServiceImpl2:createPerson method called");
        personRepository.persist(new Person(
                null,
                personDto.getName(),
                personDto.getEmail(),
                personDto.getPhoneNumber(),
                personDto.getCity()));
        return personRepository.findAll(Sort.descending("id")).stream().findFirst().get().getId();
    }

    /**
     * This method is used to update the person.
     * @param personId
     * @param updatedPerson
     * @return updated person id.
     */
    @Override
    public Long updatePerson(Long personId, PersonDto updatedPerson) {
        logger.info("PersonServiceImpl2:updatePerson method called");
        Person person = personRepository.findById(personId);
        if (person == null) {
            throw new RuntimeException("Person not found with id: " + personId);
        }
        person.setName(updatedPerson.getName());
        person.setEmail(updatedPerson.getEmail());
        person.setPhoneNumber(updatedPerson.getPhoneNumber());
        person.setCity(updatedPerson.getCity());
        return personId;
    }

    /**
     * This method is used to remove person info from DB.
     * @param id
     */
    @Override
    public void deletePerson(Long id) {
        logger.info("PersonServiceImpl2:deletePerson method called");
        personRepository.delete(personRepository.findById(id));
    }
}
