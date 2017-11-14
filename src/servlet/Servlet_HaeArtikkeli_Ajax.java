package servlet;

import dao.Dao_Artikkeli;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet_HaeArtikkeli_Ajax")
public class Servlet_HaeArtikkeli_Ajax extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Servlet_HaeArtikkeli_Ajax() {
        super();
        System.out.println("Servlet_HaeArtikkeli_Ajax.Servlet_HaeArtikkeli_Ajax()");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Servlet_HaeArtikkeli_Ajax.doGet()");
        Dao_Artikkeli dao = new Dao_Artikkeli();


    }
}