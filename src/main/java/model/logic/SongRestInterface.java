package model.logic;

import java.util.List;

public interface SongRestInterface {

    SongDO GetSongByID(int id);
    List<SongDO> GetAllSongs();
    void DeleteSongById(int id);
    void AddSong(SongDO songDO);
}
