package controller;

import model.facade.MyFacade;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.logic.Constants.USER;

@WebServlet("/person")
public class CreatePersonController extends HttpServlet {

    @EJB
    private MyFacade facade;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String register_user = req.getParameter("register_user");
        String register_pw = req.getParameter("register_pw");

        try {
            facade.createPerson(name, register_user, register_pw, USER);

            req.getRequestDispatcher("/WEB-INF/person.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
