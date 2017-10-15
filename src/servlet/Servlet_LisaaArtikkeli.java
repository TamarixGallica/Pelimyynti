package servlet;

import dao.Dao_Artikkeli;
import helper.Helper;
import model.Artikkeli;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Servlet_LisaaArtikkeli")
public class Servlet_LisaaArtikkeli extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Servlet_LisaaArtikkeli() {
        super();
        System.out.println("Servlet_LisaaArtikkeli.Servlet_LisaaArtikkeli()");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet_LisaaArtikkeli.doGet()");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet_LisaaArtikkeli.doPost()");

        Helper helper = new Helper();
        String tiedot = helper.naytaMuuttujaArvoparit(request, response);
        System.out.println(tiedot);

        Artikkeli artikkeli = new Artikkeli();
        artikkeli.setNimi(request.getParameter("Nimi"));
        artikkeli.setLisatiedot(request.getParameter("Lisatiedot"));
        artikkeli.setPyyntihinta(request.getParameter("Pyyntihinta"));

        Dao_Artikkeli dao = new Dao_Artikkeli();
        dao.lisaaArtikkelit(artikkeli);

        response.sendRedirect("artikkelihaku.jsp");
    }
}
