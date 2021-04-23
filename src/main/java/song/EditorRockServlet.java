package song;

import company.RockSong;
import controllers.SongController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditRockServlet", value="/rock/edit")
public class EditorRockServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SongController<RockSong> controller;
        int id = Integer.parseInt(request.getParameter("id"));
        try{
            controller = new SongController<>(RockSong.class, "json");
            RockSong song = controller.GetSong(id);
            request.setAttribute("song", song);
        } catch (Exception e){
            request.setAttribute("message", e.getStackTrace());
            request.getRequestDispatcher("exception.jsp").forward(request, response);
        }

        request.setAttribute("id", id);
        request.getRequestDispatcher("rock_edit.jsp").forward(request, response);
    }
}
