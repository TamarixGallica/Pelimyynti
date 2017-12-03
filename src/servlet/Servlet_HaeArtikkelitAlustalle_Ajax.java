package servlet;

import dao.Dao_Artikkeli;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/Servlet_HaeArtikkelitAlustalle_Ajax")
public class Servlet_HaeArtikkelitAlustalle_Ajax extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public Servlet_HaeArtikkelitAlustalle_Ajax() {
        super();
        System.out.println("Servlet_HaeArtikkelitAlustalle_Ajax.Servlet_HaeArtikkelit_Ajax()");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Servlet_HaeArtikkelitAlustalle_Ajax.doGet()");
        Dao_Artikkeli dao = new Dao_Artikkeli();
        try {
//            String[] sarakkeet = {"Artikkelit_id", "Nimi", "Lisatiedot", "Pyyntihinta"};
            String strJSON = dao.haeTiedotJSON("alu.Alustat_id", request.getParameter("Alusta_id"));
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            out.println(strJSON);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
