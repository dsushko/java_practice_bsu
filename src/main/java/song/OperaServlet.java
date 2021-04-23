package song;

import company.Opera;
import company.RockSong;
import controllers.SongController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OperaServlet", value="/opera")
public class OperaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SongController<Opera> controller;
        try {
            controller = new SongController<>(Opera.class, "json");
            request.setAttribute("songs", controller.GetSongs());
            request.getRequestDispatcher("opera.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
          request.setAttribute("message", e.getStackTrace());
          request.getRequestDispatcher("exception.jsp").forward(request, response);
        }

    }
}
