package com.sree.quarkus.service.impl;

import com.sree.quarkus.dto.PersonDto;
import com.sree.quarkus.entities.Person;
import com.sree.quarkus.service.PersonService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * This implementation class is used entity manager to do CRUD operations on Persons.
 */
@Transactional
@ApplicationScoped
public class PersonServiceImpl implements PersonService {
    @Inject
    EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    /**
     * This method is to get all person's data from DB.
     * @return list of person's.
     */
    public List<Person> findAll() {
        logger.info("PersonServiceImpl:findAll method called");
        return entityManager.createQuery("SELECT p from Person p", Person.class).getResultList();
    }

    /**
     * This method is used to save person data.
     * @param personDto
     * @return gives person id
     */
    public Long createPerson(PersonDto personDto) {
        logger.info("PersonServiceImpl:createPerson method called");
        Person person = new Person(
                personDto.getId(),
                personDto.getName(),
                personDto.getEmail(),
                personDto.getPhoneNumber(),
                personDto.getCity());
        entityManager.persist(person);
        return person.getId();
    }

    /**
     * This method is used to update the person.
     * @param personId
     * @param personDto
     * @return updated person id.
     */
    public Long updatePerson(Long personId, PersonDto personDto) {
        logger.info("PersonServiceImpl:updatePerson method called");
        entityManager.createQuery("UPDATE Person set name='" + personDto.getName() + "'," +
                "email='" + personDto.getEmail() + "', phoneNumber='" + personDto.getPhoneNumber() + "'," +
                "city='" + personDto.getCity() + "' where id =" + personId).executeUpdate();
        return personId;
    }

    /**
     * This method is used to remove person info from DB.
     * @param id
     */
    public void deletePerson(Long id) {
        logger.info("PersonServiceImpl:deletePerson method called");
        entityManager.createQuery("DELETE from Person where id = " + id).executeUpdate();
    }
}
