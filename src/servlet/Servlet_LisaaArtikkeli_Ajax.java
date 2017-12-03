package servlet;

import dao.Dao_Artikkeli;
import model.Alusta;
import model.Artikkeli;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Servlet_LisaaArtikkeli_Ajax")
public class Servlet_LisaaArtikkeli_Ajax extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Servlet_LisaaArtikkeli_Ajax() {
        super();
        System.out.println("Servlet_LisaaArtikkeli_Ajax.Servlet_LisaaArtikkeli_Ajax()");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet_LisaaArtikkeli_Ajax.doGet()");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet_LisaaArtikkeli_Ajax.doPost()");

        Artikkeli artikkeli = new Artikkeli();
        artikkeli.setNimi(request.getParameter("Nimi"));
        artikkeli.setLisatiedot(request.getParameter("Lisatiedot"));
        artikkeli.setPyyntihinta(request.getParameter("Pyyntihinta"));

        Alusta alusta = new Alusta();
        alusta.setAlusta_id(Integer.parseInt(request.getParameter("Alustat_id")));

        artikkeli.setAlusta(alusta);

        Dao_Artikkeli dao = new Dao_Artikkeli();
        int returnId = dao.lisaaArtikkeliAjax(artikkeli);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(returnId);
        out.flush();

    }
}