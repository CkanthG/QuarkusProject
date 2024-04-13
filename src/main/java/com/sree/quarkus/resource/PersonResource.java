package com.sree.quarkus.resource;

import com.sree.quarkus.entities.Person;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/person")
public class PersonResource {

    Logger logger = LoggerFactory.getLogger(PersonResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPersons() {
        List<Person> panacheEntityBases = Person.listAll();
        panacheEntityBases.forEach(
                person -> logger.info(person.getName())
        );
        return panacheEntityBases;
    }

}
