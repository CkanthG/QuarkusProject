package com.sree.quarkus.controller;

import com.sree.quarkus.dto.PersonDto;
import com.sree.quarkus.entities.Person;
import com.sree.quarkus.service.PersonService;
import com.sree.quarkus.service.Translating;
import jakarta.inject.Inject;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This controller class is used for Person CRUD operations.
 */
@Path("/person")
public class PersonResourceController {

    Logger logger = LoggerFactory.getLogger(PersonResourceController.class);
    private final String ERROR_MSG_KEY = "Error";
    private final String ID_KEY = "id";

    @Inject
    @Translating
    PersonService personService;

    /**
     * This controller method delegate request to service layer to get all person's info.
     * @return list of persons.
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPersons() {
        return personService.findAll();
    }

    /**
     * This controller method delegate request to service layer to save person's info.
     * @param personDto
     * @return created person info.
     */
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPerson(PersonDto personDto) {
        Map<String, Object> source = new HashMap<>();
        try {
            logger.info("createPerson with name : {}", personDto.getName());
            Long id = personService.createPerson(personDto);
            return Response.ok(source.put(ID_KEY, id)).build();
        } catch (Exception ex) {
            logger.error("Error: {}", ex.getMessage());
            return Response.status(400).entity(source.put(ERROR_MSG_KEY, "Failed to Insert")).build();
        }
    }

    /**
     * This controller method delegate request to service layer to update person's info.
     * @param personId
     * @param personDto
     * @return updated person info.
     */
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("id") Long personId, PersonDto personDto) {
        Map<String, Object> source = new HashMap<>();
        try {
            logger.info("updatePerson with Person ID : {}", personId);
            Long id = personService.updatePerson(personId, personDto);
            return Response.ok(source.put(ID_KEY, id)).build();
        } catch (Exception ex) {
            logger.error("Error: {}", ex.getMessage());
            return Response.status(400).entity(source.put(ERROR_MSG_KEY, "Failed to Update")).build();
        }
    }

    /**
     * This controller method delegate request to service layer to remove person's info.
     * @param id
     * @return status of deletion
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id") Long id) {
        Map<String, String> source = new HashMap<>();
        try {
            personService.deletePerson(id);
            return Response.ok(source.put("Success", "Successfully Deleted")).build();
        } catch (Exception exe) {
            return Response.ok(source.put(ERROR_MSG_KEY, "Error While Deleting Entry")).build();
        }
    }
}
