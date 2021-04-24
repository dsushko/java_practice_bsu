package song;

import company.RockSong;
import controllers.SongController;
import services.ServiceLayerException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RockServlet", value="/rock")
public class RockServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SongController<RockSong> controller;
        try {
            controller = new SongController<>(RockSong.class, "json");
            String ageGroup = request.getParameter("age-group");
            List<RockSong> songs = controller.GetSongs();
            if(ageGroup != null){
                songs = controller.SortByListenersAgeGroup(songs, ageGroup);
            }
            request.setAttribute("songs", songs);
            request.getRequestDispatcher("rock.jsp").forward(request, response);
        } catch (ServiceLayerException | ServletException e) {
            //e.printStackTrace();
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/exception.jsp").forward(request, response);
        }
    }
}
