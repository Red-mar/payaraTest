package model.logic;

import model.facade.StreamFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Stateless
@Path("/song")
public class SongRest implements SongRestInterface {

    @Inject
    private StreamFacade facade;

    @GET
    @Path("/{id}")
    @Override
    public SongDO GetSongByID(@PathParam("id") int id) {
        return facade.GetSongById(id);
    }

    @GET
    @Override
    public List<SongDO> GetAllSongs() {
        return facade.GetAllSongs();
    }

    @POST
    @Override
    public boolean EditSong(@FormParam("name") String name,
                            @FormParam("url") String url,
                            @FormParam("deleted") boolean isDeleted) {
        return facade.EditSong(
                new SongDO(
                        name,
                        url,
                        isDeleted
                )
        );
    }

    @POST
    @Produces("text/plain")
    @Override
    public boolean AddSong(@FormParam("name") String name,
                           @FormParam("url") String url) {
        return facade.AddSongToDatabase(name, url);
    }
}