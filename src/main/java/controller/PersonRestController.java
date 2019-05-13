package controller;

import model.facade.MyFacade;
import model.logic.PersonDO;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static security.Constants.ADMIN;

@Path("/person")
public class PersonRestController {

    @Inject
    private MyFacade facade;

    @Inject
    private SecurityContext securityContext;

    private static final Logger LOGGER = Logger.getLogger(PersonRestController.class.getName());

    // The Java method will process HTTP GET requests
    @GET
    //@RolesAllowed(ADMIN)
    @Path("/{id}")
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("application/json")
    public String getPersonById(@PathParam("id") int id) {
        // Return some cliched textual content
        return facade.getPersonById(id).getName();
    }

    @POST
    @Path("/register")
    @Produces("application/json")
    @PermitAll
    public Response createPerson(PersonDO person, @Context UriInfo uriInfo) {
        PersonDO personResult = facade.createPerson(person);

        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder())
                .rel("self").build();

        JsonObject result = Json.createObjectBuilder()
                .add("message", person.getName() + " created!")
                .add("Links", uriInfo.getBaseUri().toString() + "/person/" + personResult.getId())
                .build();

        return Response.ok(result)
                .links(self).build();
    }

    @POST
    @Produces("application/json")
    @RolesAllowed({ADMIN})
    public Response deletePersonById(@FormParam("id") int id) {
        String response = "undefined";
        if (facade.deletePersonById(id)) {
            response = "Successfully deleted " + id + ".";
        } else {
            response = "Deletion of " + id + " failed.";
        }
        JsonObject result = Json.createObjectBuilder()
                .add("user", securityContext.getCallerPrincipal().getName())
                .add("delete", response)
                .build();
        return Response.ok(result).build();
    }

    @GET
    @Produces("application/json")
    @RolesAllowed({ADMIN})
    public List<PersonDO> getPersons() {
        return facade.getAllPersons();
    }
}