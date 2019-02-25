package model.logic;

import model.facade.MyFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;

@Stateless
@Path("/person")
public class PersonRest {

    @EJB
    private MyFacade facade;

    // The Java method will process HTTP GET requests
    @GET
    @Path("/{id}")
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getPersonById(@PathParam("id") int id) {
        // Return some cliched textual content
        return facade.getPersonById(id).getName();
    }
}
