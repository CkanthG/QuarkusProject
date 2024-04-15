package com.sree.quarkus.repository;

import com.sree.quarkus.entities.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * This repository class is used for CRUD operations on top of Person.
 */
@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {
}
