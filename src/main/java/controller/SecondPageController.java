package controller;

import model.facade.MyFacade;
import model.logic.PersonDO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/second")
public class SecondPageController extends HttpServlet {

    @EJB
    private MyFacade facade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("personList", facade.getAllPersons());

            req.getRequestDispatcher("/WEB-INF/second.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}