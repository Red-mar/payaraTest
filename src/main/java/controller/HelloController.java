package controller;

import model.facade.myFacade;
import model.logic.PersonDO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class HelloController extends HttpServlet {

    @EJB
    private myFacade facade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PersonDO person = facade.getPersonById(1);

        req.setAttribute("helloMessage", "Hello " + person.getName() + ". What can I do for you?");

        req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
    }
}

