package controller;

import model.facade.MyFacade;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
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
    @Produces("text/plain")
    public String getPersonById(@PathParam("id") int id) {
        // Return some cliched textual content
        return facade.getPersonById(id).getName();
    }

    @POST
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

}