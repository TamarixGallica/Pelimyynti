package servlet;

import dao.Dao_Artikkeli;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Servlet_MuutaArtikkeli_Ajax")
public class Servlet_MuutaArtikkeli_Ajax extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Servlet_MuutaArtikkeli_Ajax() {
        super();
        System.out.println("Servlet_MuutaArtikkeli_Ajax.Servlet_MuutaArtikkeli_Ajax()");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet_MuutaArtikkeli_Ajax.doGet()");

        Dao_Artikkeli dao = new Dao_Artikkeli();
        try {
            //dao.muutaTiedotJSON("pm_artikkelit_alustat", "Alustat_id", request.getParameter("Alusta_id"), "");
            dao.muutaTiedotJSON("pm_artikkelit_alustat", "Artikkelit_id", request.getParameter("Artikkeli_id"), "Alustat_id", request.getParameter("Alusta_id"));
            dao.muutaTiedotJSON("pm_artikkelit", "Artikkelit_id", request.getParameter("Artikkeli_id"), "Lisatiedot", request.getParameter("Artikkeli_lisatiedot"));
            dao.muutaTiedotJSON("pm_artikkelit", "Artikkelit_id", request.getParameter("Artikkeli_id"), "Pyyntihinta", request.getParameter("Artikkeli_pyyntihinta"));
            String strJSON = dao.muutaTiedotJSON("pm_artikkelit", "Artikkelit_id", request.getParameter("Artikkeli_id"), "Nimi", request.getParameter("Artikkeli_nimi"));
            PrintWriter out = response.getWriter();
            //            response.setContentType("text/html");
            out.println(strJSON);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
