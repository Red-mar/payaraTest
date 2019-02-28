package model.facade;

import model.logic.SongDO;
import model.logic.StreamProvider;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.List;

@Singleton
public class StreamFacade {

    @EJB
    private StreamProvider streamProvider;

    @PostConstruct
    public void init() {
        // IceCast host and port
        streamProvider.Initialize("localhost", 8000);
    }

    public SongDO GetSongById(int id) {
        return streamProvider.GetSongById(id);
    }

    public List<SongDO> GetAllSongs() {
        return streamProvider.GetAllSongs();
    }

    public List<SongDO> GetQueue() {
        return streamProvider.GetQueue();
    }

    public boolean AddSongToDatabase(String name, String url) {
        return streamProvider.AddSongToDatabase(new SongDO(name, url));
    }

    public void AddSongToQueue(SongDO song) {
        streamProvider.AddSongToQueue(song);
    }

    public boolean StartStreaming() {
        return streamProvider.StartStreaming();
    }

    public boolean StopStreaming() {
        return streamProvider.StopStreaming();
    }
}
