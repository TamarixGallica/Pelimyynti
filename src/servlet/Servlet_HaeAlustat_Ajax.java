package servlet;

import dao.Dao_Alusta;
import dao.Dao_Artikkeli;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/Servlet_HaeAlustat_Ajax")
public class Servlet_HaeAlustat_Ajax extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public Servlet_HaeAlustat_Ajax() {
        super();
        System.out.println("Servlet_HaeArtikkelit_Ajax.Servlet_HaeArtikkelit_Ajax()");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Servlet_HaeArtikkelit_Ajax.doGet()");
        Dao_Alusta dao = new Dao_Alusta();
        try {
            String[] sarakkeet = {"Alustat_id", "Nimi"};
            String strJSON = dao.haeTiedotJSON(sarakkeet, "pm_alustat");
            PrintWriter out = response.getWriter();
//            response.setContentType("text/html");
            out.println(strJSON);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
