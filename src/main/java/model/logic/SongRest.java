package model.logic;

import model.facade.StreamFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Stateless
@Path("/song")
public class SongRest {

    @EJB
    private StreamFacade facade;

    // The Java method will process HTTP GET requests
    @GET
    @Path("/{id}")
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getSongByID(@PathParam("id") int id) {
        // Return some cliched textual content
        return "";
    }
}