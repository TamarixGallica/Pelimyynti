package servlet;

import dao.Dao_Artikkeli;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/Servlet_PoistaArtikkeli_Ajax")
public class Servlet_PoistaArtikkeli_Ajax extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Servlet_PoistaArtikkeli_Ajax() {
        super();
        System.out.println("Servlet_PoistaArtikkeli_Ajax.Servlet_PoistaArtikkeli_Ajax()");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Servlet_PoistaArtikkeli_Ajax.doGet()");
        Dao_Artikkeli dao = new Dao_Artikkeli();
        try {
            String strJSON = dao.poistaTiedotJSON("pm_artikkelit", "Artikkelit_id", request.getParameter("id"));
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            out.println(strJSON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
