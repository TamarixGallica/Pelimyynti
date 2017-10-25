package servlet;

import dao.Dao_Artikkeli;
import model.Artikkeli;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Servlet_HaeArtikkelit")
public class Servlet_HaeArtikkelit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Servlet_HaeArtikkelit () {
        super();
        System.out.println("Servlet_HaeArtikkelit.Servlet_HaeArtikkelit()");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet_HaeArtikkelit.doGet()");

        Dao_Artikkeli dao = new Dao_Artikkeli();
        try {
            ArrayList<Artikkeli> artikkelit = dao.haeArtikkelit();
            request.setAttribute("artikkelit", artikkelit);
            String jsp = "/artikkelihaku.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Koetulostus artikkelien hakemiselle JSON-muodossa
        try {
            String[] sarakkeet={"Nimi", "Lisatiedot"};
            System.out.println(dao.haeTiedotJSON(sarakkeet, "pm_artikkelit"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
