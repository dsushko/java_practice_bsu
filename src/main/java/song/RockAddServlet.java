package song;

import company.ListeningStats;
import company.RockSong;
import controllers.SongController;
import services.ServiceLayerException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RockAddServlet", value="/rock/add")
public class RockAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String duration = request.getParameter("duration");
        String albumName = request.getParameter("albumName");
        String key = request.getParameter("key");
        String subGenre = request.getParameter("subgenre");
        String guitarEffects = request.getParameter("guitarEffects");
        boolean hasExtremeVocals = !(request.getParameter("hasExtremeVocals") == null);

        int under10 = Integer.parseInt(request.getParameter("under10"));
        int _10to18 = Integer.parseInt(request.getParameter("10to18"));
        int _18to35 = Integer.parseInt(request.getParameter("18to35"));
        int _35to60 = Integer.parseInt(request.getParameter("35to60"));
        int over60 = Integer.parseInt(request.getParameter("over60"));
        int men = Integer.parseInt(request.getParameter("men"));
        int women = Integer.parseInt(request.getParameter("women"));

        SongController<RockSong> controller;
        try {
            controller = new SongController<>(RockSong.class, "json");
            ListeningStats newStats = new ListeningStats(under10,_10to18, _18to35, _35to60, over60, men, women);

            RockSong model = new RockSong(author, name, duration, albumName, newStats, key,
                    subGenre, hasExtremeVocals, guitarEffects);
            controller.AddSong(model);

            response.sendRedirect("../rock");
        } catch (ServiceLayerException e) {
            //e.printStackTrace();
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/exception.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/rock/rock_create.jsp").forward(request, response);
    }
}
