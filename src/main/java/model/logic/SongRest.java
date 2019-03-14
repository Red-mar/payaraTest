package model.logic;

import model.facade.StreamFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.List;

@Stateless
@Path("/song")
public class SongRest implements SongRestInterface {

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

    @GET
    @Path("/{id}")
    @Produces("text/plain")
    @Override
    public SongDO GetSongByID(@PathParam("id") int id) {
        return null;
    }

    @GET
    @Produces("text/plain")
    @Override
    public List<SongDO> GetAllSongs() {
        return null;
    }

    @DELETE
    @Produces("text/plain")
    @Override
    public void DeleteSongById(int id) {

    }

    @PUT
    @Produces("text/plain")
    @Override
    public void AddSong(SongDO song) {

    }
sr
    @GET
    @Produces("text/plain")
    @Override
    public SongDO GetSongByName(String name) {
        return null;
    }
}