package controller;

import model.facade.StreamFacade;
import model.logic.SongDO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/stream")
public class StreamController extends HttpServlet {

    @EJB
    private StreamFacade facade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Refresh(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("addSongToDatabase") != null) {
            try {
                String name = req.getParameter("songName");
                String url = req.getParameter("url");
                facade.AddSongToDatabase(name, url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (req.getParameter("addSongToQueue") != null) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                facade.AddSongToQueue(
                        facade.GetSongById(id)
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (req.getParameter("start") != null) {
            facade.StartStreaming();
        }

        Refresh(req, resp);
    }

    private void Refresh(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<SongDO> songList = facade.GetAllSongs();
            List<SongDO> queueList = facade.GetQueue();

            req.setAttribute("songList", songList);
            req.setAttribute("queueList", queueList);

            req.getRequestDispatcher("/WEB-INF/stream.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
