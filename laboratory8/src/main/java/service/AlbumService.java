package service;

import dao.impl.AlbumDaoImpl;
import model.Album;

import java.util.List;

public class AlbumService {
    private final AlbumDaoImpl albumDao;

    public AlbumService(AlbumDaoImpl albumDao) {
        this.albumDao = albumDao;
    }

    public Album findAlbumById(int id) {
        return albumDao.findById(id);
    }

    public void saveAlbum(Album album) {
        albumDao.save(album);
    }

    public void updateAlbum(Album album) {
        albumDao.update(album);
    }

    public void deleteAlbum(Album album) {
        albumDao.delete(album);
    }

    public List<Album> findAllAlbums() {
        return albumDao.findAll();
    }

}
