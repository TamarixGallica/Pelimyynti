package servlet;

import dao.Dao_Alusta;
import model.Alusta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Servlet_LisaaAlusta")
public class Servlet_LisaaAlusta extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Servlet_LisaaAlusta() {
        super();
        System.out.println("Servlet_LisaaAlusta.Servlet_LisaaAlusta()");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet_LisaaAlusta.doGet()");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet_LisaaAlusta.doPost()");

        Alusta alusta = new Alusta();
        alusta.setNimi(request.getParameter("Nimi"));

        Dao_Alusta dao = new Dao_Alusta();
        dao.lisaaAlusta(alusta);

        response.sendRedirect("alustahaku.jsp");
    }
}
