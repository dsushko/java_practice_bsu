package song;

import company.Opera;
import company.RapSong;
import controllers.SongController;
import services.ServiceLayerException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RapDeleteServlet" , value ="/rap/delete")
public class RapDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        SongController<RapSong> controller;
        try{
            controller = new SongController<>(RapSong.class, "json");
            controller.DeleteSong(id);
            response.sendRedirect("../rap");
        } catch (ServiceLayerException e) {
            //e.printStackTrace();
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/exception.jsp").forward(request, response);
        }
    }
}
