package song;

import company.RapSong;
import company.RockSong;
import controllers.SongController;
import services.ServiceLayerException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RapServlet", value="/rap")
public class RapServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SongController<RapSong> controller;
        try {
            controller = new SongController<>(RapSong.class, "json");
            request.setAttribute("songs", controller.GetSongs());
            request.getRequestDispatcher("rap.jsp").forward(request, response);
        } catch (ServiceLayerException | ServletException e) {
            //e.printStackTrace();
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/exception.jsp").forward(request, response);
        }

    }
}
