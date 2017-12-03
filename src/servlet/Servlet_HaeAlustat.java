package servlet;

import dao.Dao_Alusta;
import model.Alusta;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Servlet_HaeAlustat")
public class Servlet_HaeAlustat extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public Servlet_HaeAlustat() {
        super();
        System.out.println("Servlet_HaeAlustat.Servlet_HaeAlustat()");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet_HaeAlustat.doGet()");

        Dao_Alusta dao = new Dao_Alusta();
        try {
            ArrayList<Alusta> alustat = dao.haeAlustat();
            request.setAttribute("alustat", alustat);
            String jsp = "/alustahaku.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
