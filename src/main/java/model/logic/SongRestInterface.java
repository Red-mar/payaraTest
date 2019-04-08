package model.logic;

import java.util.List;

public interface SongRestInterface {

    SongDO GetSongByID(int id);
    List<SongDO> GetAllSongs();
    boolean EditSong(String name, String url, boolean isDeleted);
    boolean AddSong(String name, String url);
}
