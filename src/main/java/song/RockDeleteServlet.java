package song;

import company.RockSong;
import controllers.SongController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RockDeleteServlet", value ="/rock/delete")
public class RockDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        SongController<RockSong> controller;
        try {
            controller = new SongController<>(RockSong.class, "json");
            controller.DeleteSong(id);
            response.sendRedirect("../rock");
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("exception.jsp").forward(request, response);
        }
    }
}
