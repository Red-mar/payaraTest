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
import javax.ws.rs.core.Response;
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
    @Path("/create")
    @Produces("application/json")
    @PermitAll
    public Response createPerson(@FormParam("name") String name,
                                 @FormParam("username") String username,
                                 @FormParam("password") String password,
                                 @FormParam("role") String role) {
        facade.createPerson(name, username, password, role);

        // Cannot create admin if you are not logged in as admin.
        if( ! securityContext.isCallerInRole(ADMIN) && role.equals(ADMIN) ) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        JsonObject result = Json.createObjectBuilder()
                .add("name", name)
                .add("username", username)
                .add("role", role)
                .build();
        return Response.ok(result).build();
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
    public List<PersonDO> getPersons() {
        return facade.getAllPersons();
    }

}