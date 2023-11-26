package servlet;

import com.google.gson.Gson;
import dao.impl.AlbumDaoImpl;
import dao.impl.ArtistDaoImpl;
import model.Album;
import model.Artist;
import org.hibernate.SessionFactory;
import service.AlbumService;
import service.ArtistService;
import utils.HibernateSessionFactoryUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/album")
public class AlbumServlet extends HttpServlet {
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    private List<Album> albums;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            AlbumService service = new AlbumService(new AlbumDaoImpl(sessionFactory));
            albums = service.findAllAlbums();
            req.setAttribute("albums", albums);
            getServletContext().getRequestDispatcher("/change-album.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AlbumService service = new AlbumService(new AlbumDaoImpl(sessionFactory));
            ArtistService artistService = new ArtistService(new ArtistDaoImpl(sessionFactory));
            if (!req.getParameterMap().isEmpty()) {
                if (!req.getParameter("deleteName").isEmpty()) {
                    String deleteName = req.getParameter("deleteName");
                    Album album = checkAlbum(deleteName);
                    if (album != null) {
                        album.setSongs(null);
                        service.deleteAlbum(album);
                    } else {
                        throw new RuntimeException("Неверные данные в поле для удаления альбома!");
                    }
                }
                if (!req.getParameter("updateName").isEmpty()) {
                    String updateName = req.getParameter("updateName");
                    Album album = checkAlbum(updateName);
                    if (album != null) {
                        String newName = req.getParameter("newName");
                        String newGenre = req.getParameter("updateGenre");
                        String otherArtist = req.getParameter("updateArtist");
                        if (checkAlbum(album.getId(), newName, newGenre, otherArtist)) {
                            album = service.findAlbumById(album.getId());
                            setNewParams(album, newName, newGenre, otherArtist, artistService.findAllArtists());
                            service.updateAlbum(album);
                        } else {
                            throw new RuntimeException("Неверные данные в поле для изменения альбома!");
                        }
                    }
                }
                if (!req.getParameter("insertName").isEmpty()) {
                    String name = req.getParameter("insertName");
                    String genre = req.getParameter("insertGenre");
                    String artist = req.getParameter("insertArtist");
                    if (checkParams(name, genre, artist) && checkAlbum(name) == null) {
                        List<Artist> artists = artistService.findAllArtists();
                        int count = 0;
                        for (Artist a : artists) {
                            if (a.getName().equals(artist)) {
                                Album album = new Album(name, genre);
                                album.setArtist(a);
                                a.addAlbum(album);
                                service.saveAlbum(album);
                                break;
                            }
                            count++;
                        }
                        if (count == artists.size()) {
                            throw new RuntimeException("Данный артист отсутствует!");
                        }
                    } else {
                        throw new RuntimeException("Неверные данные в поле для создания альбома!");
                    }
                }
                resp.sendRedirect("/album");
            }
        } catch (Exception exception) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            throw new RuntimeException(exception.getMessage());
        }

    }

    private void setNewParams(Album album, String newName, String newGenre, String otherArtist, List<Artist> artists) {
        for (Artist artist : artists) {
            if (artist.getName().equals(otherArtist)) {
                if (album.getArtist() != artist) {
                    album.setArtist(artist);
                    artist.addAlbum(album);
                    break;
                }
            }
        }
        if (!album.getName().equals(newName) && !newName.isEmpty()) {
            album.setName(newName);
        }
        if (!album.getGenre().equals(newGenre) && !newGenre.isEmpty()) {
            album.setGenre(newGenre);
        }
    }

    private boolean checkAlbum(int id, String newName, String newGenre, String otherArtist) {
        int count = 0;
        for (Album album : albums) {
            if (album.getId() == id) {
                if (album.getName().equals(newName)) {
                    count++;
                }
                if (album.getGenre().equals(newGenre)) {
                    count++;
                }
                if (album.getArtist().getName().equals(otherArtist)) {
                    count++;
                }
            }
        }
        return count != 3;
    }

    private Album checkAlbum(String name) {
        for (Album album : albums) {
            if (album.getName().equals(name)) {
                return album;
            }
        }
        return null;
    }

    private boolean checkParams(String name, String genre, String artist) {
        return !name.isEmpty() && !genre.isEmpty() && !artist.isEmpty();
    }
}
