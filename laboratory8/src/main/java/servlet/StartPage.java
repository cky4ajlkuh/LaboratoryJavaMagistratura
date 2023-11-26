package servlet;

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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("")
public class StartPage extends HttpServlet {

    SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Artist> artists = new ArtistService(new ArtistDaoImpl(sessionFactory)).findAllArtists();
        List<Album> albums = new AlbumService(new AlbumDaoImpl(sessionFactory)).findAllAlbums();
        List<Song> songs = new SongService(new SongDaoImpl(sessionFactory)).findAllSongs();
        req.setAttribute("albums", albums);
        req.setAttribute("artists", artists);
        req.setAttribute("songs", songs);
        getServletContext().getRequestDispatcher("/start-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
