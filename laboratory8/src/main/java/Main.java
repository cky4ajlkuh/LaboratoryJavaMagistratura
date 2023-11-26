
import dao.impl.AlbumDaoImpl;
import dao.impl.ArtistDaoImpl;
import dao.impl.SongDaoImpl;
import model.Album;
import model.Artist;
import model.Song;
import org.hibernate.SessionFactory;
import service.AlbumService;
import service.ArtistService;
import service.SongService;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

        ArtistService artistService = new ArtistService(new ArtistDaoImpl(sessionFactory));
        AlbumService albumService = new AlbumService(new AlbumDaoImpl(sessionFactory));
        SongService songService = new SongService(new SongDaoImpl(sessionFactory));
        Artist artist = artistService.findArtistById(3);
        artistService.deleteArtist(artist);
        /*
        Artist firstArtist = new Artist("Noize MC");
        Artist secondArtist = new Artist("Anacondaz");

        Album firstAlbum = new Album("Выход в город", "rap");
        Album secondAlbum = new Album("Царь горы", "rap");
        Album thirdAlbum = new Album("Я тебя никогда", "rap");

        Song firstSong = new Song("Букет крапивы", 180);
        Song secondSong = new Song("+-0", 200);
        Song thirdSong = new Song("Гой еси", 210);
        Song fourthSong = new Song("Поезда", 300);

        artistService.saveArtist(firstArtist);
        artistService.saveArtist(secondArtist);

        firstAlbum.setArtist(firstArtist);
        secondAlbum.setArtist(firstArtist);
        thirdAlbum.setArtist(secondArtist);

        firstArtist.addAlbum(firstAlbum);
        firstArtist.addAlbum(secondAlbum);
        secondArtist.addAlbum(thirdAlbum);

        albumService.saveAlbum(firstAlbum);
        albumService.saveAlbum(secondAlbum);
        albumService.saveAlbum(thirdAlbum);

        firstSong.setAlbum(firstAlbum);
        secondSong.setAlbum(secondAlbum);
        thirdSong.setAlbum(secondAlbum);
        fourthSong.setAlbum(thirdAlbum);

        firstAlbum.addSong(firstSong);
        secondAlbum.addSong(secondSong);
        secondAlbum.addSong(thirdSong);
        thirdAlbum.addSong(fourthSong);

        songService.saveSong(firstSong);
        songService.saveSong(secondSong);
        songService.saveSong(thirdSong);
        songService.saveSong(fourthSong);

        List<Song> songList = songService.findAllSongs();
        for (Song song : songList) {
            System.out.println(song.toString());
        }

        firstSong.setDuration(13);

        songService.updateSong(firstSong);
        songList = songService.findAllSongs();
        for (Song song : songList) {
            System.out.println(song.toString());
        }

        List<Artist> artistList = artistService.findAllArtists();
        for (Artist artist : artistList) {
            System.out.println(artist.toString());
        }

        List<Album> albumList = albumService.findAllAlbums();
        for (Album album : albumList) {
            System.out.println(album.toString());
        }

        List<Song> songList = songService.findAllSongs();
        for (Song song : songList) {
            System.out.println(song.toString());
        }
        songService.deleteSong(firstSong);

        songList = songService.findAllSongs();
        for (Song song : songList) {
            System.out.println(song.toString());
        }
 */
    }
}
