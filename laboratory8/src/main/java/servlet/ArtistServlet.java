package servlet;

import dao.impl.ArtistDaoImpl;
import model.Artist;
import org.hibernate.SessionFactory;
import service.ArtistService;
import utils.HibernateSessionFactoryUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/artist")
public class ArtistServlet extends HttpServlet {

    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    private List<Artist> artists;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArtistService service = new ArtistService(new ArtistDaoImpl(sessionFactory));
            artists = service.findAllArtists();
            req.setAttribute("artists", artists);
            getServletContext().getRequestDispatcher("/change-artist.jsp").forward(req, resp);
        } catch (IOException | ServletException e) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArtistService service = new ArtistService(new ArtistDaoImpl(sessionFactory));
            if (!req.getParameterMap().isEmpty()) {
                if (!req.getParameter("deleteName").isEmpty()) {
                    String deleteName = req.getParameter("deleteName");
                    Artist artist = checkArtist(deleteName);
                    if (artist != null) {
                        artist.setAlbums(null);
                        service.deleteArtist(service.findArtistById(artist.getId()));
                    } else {
                        throw new RuntimeException("Неверные данные в поле для удаления артиста!");
                    }
                }
                if (!req.getParameter("updateName").isEmpty()) {
                    String updateName = req.getParameter("updateName");
                    String newName = req.getParameter("newName");
                    Artist artist = checkArtist(updateName);
                    if (artist != null && !newName.isEmpty()) {
                        artist = service.findArtistById(artist.getId());
                        artist.setName(newName);
                        service.updateArtist(artist);
                    } else {
                        throw new RuntimeException("Неверные данные в поле для изменения артиста!");
                    }
                }
                if (!req.getParameter("insertName").isEmpty()) {
                    String name = req.getParameter("insertName");
                    if (checkArtist(name) == null && !name.isEmpty()) {
                        service.saveArtist(new Artist(name));
                    } else {
                        throw new RuntimeException("Неверные данные в поле для создания артиста!");
                    }
                }
                resp.sendRedirect("/artist");
            }
        } catch (Exception exception) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            throw new RuntimeException(exception.getMessage());
        }
    }

    private Artist checkArtist(String name) {
        for (Artist artist : artists) {
            if (artist.getName().equals(name)) {
                return artist;
            }
        }
        return null;
    }
}
