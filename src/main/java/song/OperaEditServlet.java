package song;

import company.ListeningStats;
import company.Opera;
import controllers.SongController;
import services.ServiceLayerException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OperaEditServlet", value = "/opera/post")
public class OperaEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String duration = request.getParameter("duration");
        String albumName = request.getParameter("albumName");
        String key = request.getParameter("key");
        int releaseCentury = Integer.parseInt(request.getParameter("releaseCentury"));
        int orchestraCount = Integer.parseInt(request.getParameter("orchestraCount"));
        String vocalVoiceTypeRequired = request.getParameter("vocalVoiceTypeRequired");


        int under10 = Integer.parseInt(request.getParameter("under10"));
        int _10to18 = Integer.parseInt(request.getParameter("10to18"));
        int _18to35 = Integer.parseInt(request.getParameter("18to35"));
        int _35to60 = Integer.parseInt(request.getParameter("35to60"));
        int over60 = Integer.parseInt(request.getParameter("over60"));
        int men = Integer.parseInt(request.getParameter("men"));
        int women = Integer.parseInt(request.getParameter("women"));

        SongController<Opera> controller;
        try {
            controller = new SongController<>(Opera.class, "json");
            ListeningStats newStats = new ListeningStats(under10,_10to18, _18to35, _35to60, over60, men, women);
            int thisId = Integer.parseInt(request.getParameter("id"));

            Opera model = new Opera(author, name, duration, albumName, newStats, key, releaseCentury, orchestraCount, vocalVoiceTypeRequired);
            controller.EditSong(thisId, model);
            response.sendRedirect("../opera");
        } catch (ServiceLayerException e) {
            //e.printStackTrace();
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/exception.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/opera/opera_create.jsp").forward(request, response);
    }
}
