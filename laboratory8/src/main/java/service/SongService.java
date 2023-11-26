package service;

import dao.impl.SongDaoImpl;
import model.Song;

import java.util.List;

public class SongService {
    private final SongDaoImpl songDao;

    public SongService(SongDaoImpl songDao) {
        this.songDao = songDao;
    }

    public Song findSongById(int id) {
        return songDao.findById(id);
    }

    public void saveSong(Song song) {
        songDao.save(song);
    }

    public void updateSong(Song song) {
        songDao.update(song);
    }

    public void deleteSong(Song song) {
        songDao.delete(song);
    }

    public List<Song> findAllSongs() {
        return songDao.findAll();
    }

}
