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

    @GET
    @Path("/{id}")
    @Produces("text/plain")
    @Override
    public SongDO GetSongByID(@PathParam("id") int id) {
        return facade.GetSongById(id);
    }

    @GET
    @Produces("text/plain")
    @Override
    public List<SongDO> GetAllSongs() {
        return facade.GetAllSongs();
    }

    @DELETE
    @Path("/{id}")
    @Produces("text/plain")
    @Override
    public void DeleteSongById(@PathParam("id") int id) {
        //
    }

    @PUT
    @Produces("text/plain")
    @Override
    public void AddSong(SongDO song) {

    }
}