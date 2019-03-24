package model.logic;

import model.facade.MyFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

@Stateless
@Path("/personr")
public class PersonRest {

    @EJB
    private MyFacade facade;

    @Inject
    private SecurityContext securityContext;

    // The Java method will process HTTP GET requests
    @GET
    @Path("/{id}")
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getPersonById(@PathParam("id") int id) {
        // Return some cliched textual content
        return facade.getPersonById(id).getName();
    }

    @GET
    @Path("login")
    public Response login() {
        if (securityContext.getCallerPrincipal() != null) {
            JsonObject result = Json.createObjectBuilder()
                    .add("user", securityContext.getCallerPrincipal().getName())
                    .build();
            return Response.ok(result).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
