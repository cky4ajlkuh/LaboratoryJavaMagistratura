package service;

import dao.impl.ArtistDaoImpl;
import model.Artist;

import java.util.List;

public class ArtistService {
    private final ArtistDaoImpl artistDao;

    public ArtistService(ArtistDaoImpl artistDao) {
        this.artistDao = artistDao;
    }

    public Artist findArtistById(int id) {
        return artistDao.findById(id);
    }

    public void saveArtist(Artist artist) {
        artistDao.save(artist);
    }

    public void updateArtist(Artist artist) {
        artistDao.update(artist);
    }

    public void deleteArtist(Artist artist) {
        artistDao.delete(artist);
    }

    public List<Artist> findAllArtists() {
        return artistDao.findAll();
    }

}
