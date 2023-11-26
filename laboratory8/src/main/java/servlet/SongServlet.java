package servlet;

import dao.impl.AlbumDaoImpl;
import dao.impl.SongDaoImpl;
import model.Album;
import model.Song;
import org.hibernate.SessionFactory;
import service.AlbumService;
import service.SongService;
import utils.HibernateSessionFactoryUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/song")
public class SongServlet extends HttpServlet {
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    private List<Song> songs;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            SongService service = new SongService(new SongDaoImpl(sessionFactory));
            songs = service.findAllSongs();
            req.setAttribute("songs", songs);
            getServletContext().getRequestDispatcher("/change-song.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SongService service = new SongService(new SongDaoImpl(sessionFactory));
            AlbumService albumService = new AlbumService(new AlbumDaoImpl(sessionFactory));
            if (!req.getParameterMap().isEmpty()) {
                if (!req.getParameter("deleteName").isEmpty()) {
                    String deleteName = req.getParameter("deleteName");
                    Song song = checkSong(deleteName);
                    if (song != null) {
                        song.setAlbum(null);
                        service.deleteSong(song);
                    } else {
                        throw new RuntimeException("Неверные данные в поле для удаления песни!");
                    }
                }
                if (!req.getParameter("updateName").isEmpty()) {
                    String updateName = req.getParameter("updateName");
                    Song song = checkSong(updateName);
                    if (song != null) {
                        String newName = req.getParameter("newName");
                        int newDuration = 0;
                        if (!req.getParameter("updateDuration").isEmpty()) {
                            newDuration = Integer.parseInt(req.getParameter("updateDuration"));
                        }
                        String otherAlbum = req.getParameter("updateAlbum");
                        if (checkAlbum(song.getId(), newName, newDuration, otherAlbum)) {
                            setNewParams(song, newName, newDuration, otherAlbum, albumService.findAllAlbums());
                            service.updateSong(song);
                        }
                    } else {
                        throw new RuntimeException("Неверные данные в поле для изменения песни!");
                    }
                }
                if (!req.getParameter("insertName").isEmpty()) {
                    String name = req.getParameter("insertName");
                    String duration = req.getParameter("insertDuration");
                    String album = req.getParameter("insertAlbum");
                    if (checkParams(name, duration, album) && checkSong(name) == null) {
                        List<Album> albums = albumService.findAllAlbums();
                        int count = 0;
                        for (Album a : albums) {
                            if (a.getName().equals(album)) {
                                Song song = new Song(name, Integer.parseInt(duration));
                                song.setAlbum(a);
                                a.addSong(song);
                                service.saveSong(song);
                                break;
                            }
                            count++;
                        }
                        if (count == albums.size()) {
                            throw new RuntimeException("Данный альбом отсутствует!");
                        }
                    } else {
                        throw new RuntimeException("Неверные данные в поле для создания песни!");
                    }
                }
                resp.sendRedirect("/song");
            }
        } catch (Exception exception) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            throw new RuntimeException(exception.getMessage());
        }

    }

    private void setNewParams(Song song, String newName, int newDuration, String otherAlbum, List<Album> albums) {
        for (Album album : albums) {
            if (album.getName().equals(otherAlbum)) {
                if (song.getAlbum() != album) {
                    song.setAlbum(album);
                    album.addSong(song);
                    break;
                }
            }
        }
        if (!song.getName().equals(newName) && !newName.isEmpty()) {
            song.setName(newName);
        }
        if (song.getDuration() != newDuration && newDuration > 0) {
            song.setDuration(newDuration);
        }
    }

    private Song checkSong(String name) {
        for (Song song : songs) {
            if (song.getName().equals(name)) {
                return song;
            }
        }
        return null;
    }

    private boolean checkAlbum(int id, String newName, int newDuration, String otherAlbum) {
        int count = 0;
        for (Song song : songs) {
            if (song.getId() == id) {
                if (song.getName().equals(newName)) {
                    count++;
                }
                if (song.getDuration() == newDuration) {
                    count++;
                }
                if (song.getAlbum().getName().equals(otherAlbum)) {
                    count++;
                }
            }
        }
        return count != 3;
    }

    private boolean checkParams(String name, String duration, String album) {
        return !name.isEmpty() && !duration.isEmpty() && !album.isEmpty() && Integer.parseInt(duration) > 0;
    }
}
