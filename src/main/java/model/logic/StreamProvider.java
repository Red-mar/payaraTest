package model.logic;

import com.gmail.kunicins.olegs.libshout.Libshout;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Stateless
public class StreamProvider {

    @PersistenceContext
    private EntityManager em;

    private Libshout iceCast;
    private Thread streamingThread = new Thread();
    private ArrayList<SongDO> songQueue = new ArrayList<>();


    public void Initialize(String host, int port) {
        try {
            iceCast = new Libshout();
            iceCast.setHost(host);
            iceCast.setPort(port);
            iceCast.setProtocol(Libshout.PROTOCOL_HTTP);
            iceCast.setPassword("hackme");
            iceCast.setMount("/test");
            iceCast.setFormat(Libshout.FORMAT_MP3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean StopStreaming() {
        if (streamingThread.isAlive()) {
            streamingThread.interrupt();
            return true;
        }
        return false;
    }

    public boolean StartStreaming() {
        if (streamingThread.isAlive()) {
            System.out.println("Already streaming");
            return false;
        }

        // Start a thread in the background that streams audio to iceCast.
        // The queue can still be controlled.
        streamingThread = new Thread() {
            public void run() {
                try {
                    iceCast.open();
                    while (songQueue.size() > 0) {

                        System.out.println("Starting stream of song\n" + songQueue.get(0).getUrl() + "\n" + songQueue.size());

                        byte[] buffer = new byte[1024];
                        InputStream mp3 = new BufferedInputStream(new FileInputStream(
                                new File(songQueue.get(0).getUrl())));
                        int read = mp3.read(buffer);
                        while (read > 0) {
                            iceCast.send(buffer, read);
                            read = mp3.read(buffer);
                        }
                        // Send empty buffer so the stream does not stop when switching songs.
                        iceCast.send(new byte[1024], 1024);
                        // Remove the first song from the queue, the list will automatically shift the items
                        songQueue.remove(0);
                    }
                    iceCast.close();
                    System.out.println("No more songs in the queue");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        streamingThread.start();
        return true;
    }

    public SongDO GetSongById(int id) {
        return em.createNamedQuery("song.findOne", SongDO.class).setParameter("id", id).getSingleResult();
    }

    public List<SongDO> GetAllSongs() {
        return em.createNamedQuery("song.getAll", SongDO.class).getResultList();
    }

    public List<SongDO> GetQueue() {
        return songQueue;
    }

    public boolean AddSongToDatabase(SongDO song) {
        try {
            em.persist(song);
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public void AddSongToQueue(SongDO song) {
        songQueue.add(song);
    }
}
