package servlet;

import dao.Dao;
import dao.Dao_Alusta;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/Servlet_HaeAlustaArtikkelille_Ajax")
public class Servlet_HaeAlustaArtikkelille_Ajax extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public Servlet_HaeAlustaArtikkelille_Ajax() {
        super();
        System.out.println("Servlet_HaeAlustaArtikkelille_Ajax.Servlet_HaeAlustaArtikkelille_Ajax()");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Servlet_HaeAlustaArtikkelille_Ajax.doGet()");
        Dao dao = new Dao_Alusta();
        try {
            String[] sarakkeet = {"Alustat_id"};
            String strJSON = dao.haeTiedotJSON(sarakkeet, "pm_artikkelit_alustat", "Artikkelit_id", request.getParameter("id"));
            PrintWriter out = response.getWriter();
            out.println(strJSON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
