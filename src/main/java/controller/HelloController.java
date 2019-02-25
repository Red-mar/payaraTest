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
import java.util.Random;

@WebServlet("")
public class HelloController extends HttpServlet {

    @EJB
    private MyFacade facade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("helloMessage", "Hello from helloController.");

        req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
    }

}

