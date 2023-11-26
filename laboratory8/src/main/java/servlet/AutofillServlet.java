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

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/autofill")
public class AutofillServlet extends HttpServlet {
    private final SessionFactory factory = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!req.getParameter("autofillArtists").isEmpty()) {
            ArtistService service = new ArtistService(new ArtistDaoImpl(factory));
            List<Artist> artists = service.findAllArtists();
            List<String> names = new ArrayList<>();
            for (Artist artist : artists) {
                names.add(artist.getName());
            }
            String json = new Gson().toJson(names);
            resp.getWriter().write(json);
        }
    }
}
