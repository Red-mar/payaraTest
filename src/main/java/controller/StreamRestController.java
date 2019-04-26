package controller;

import model.facade.MyFacade;
import model.facade.StreamFacade;
import model.logic.SongDO;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.*;
import java.util.List;
import java.util.logging.Logger;

import static security.Constants.ADMIN;
import static security.Constants.USER;

@Path("/stream")
public class StreamRestController {

    @Inject
    private StreamFacade facade;

    @Inject
    private SecurityContext securityContext;

    // The Java method will process HTTP GET requests
    @GET
    @RolesAllowed({ADMIN})
    @Path("/{id}")
    public SongDO GetStreamById(@PathParam("id") int id) {
        // Return some cliched textual content
        return facade.GetSongById(id);
    }

    @POST
    @RolesAllowed({ADMIN})
    @Path("/song")
    public boolean AddSongToDatabase(SongDO song) {
        return facade.AddSongToDatabase(song.getName(), song.getUrl());
    }

    @PUT
    @RolesAllowed({ADMIN})
    @Path("/song")
    public boolean EditSong(SongDO song) {
        return facade.EditSong(song);
    }

    @GET
    @PermitAll
    @Path("/song")
    public List<SongDO> GetAllSongs() {
        return facade.GetAllSongs();
    }

    @POST
    @Path("/queue")
    @RolesAllowed({ADMIN, USER})
    public void AddSongToQueue(SongDO song) {
        facade.AddSongToQueue(song);
    }

    @GET
    @PermitAll
    @Path("/queue")
    public List<SongDO> GetQueue() {
        return facade.GetQueue();
    }

    @POST
    @RolesAllowed({ADMIN})
    @Path("/start")
    public void StartStreaming() {
        facade.StartStreaming();
    }

    @POST
    @RolesAllowed({ADMIN})
    @Path("/stop")
    public void StopStreaming() {
        facade.StopStreaming();
    }





}
