package servlet;

import dao.Dao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/Servlet_MuutaAlusta_Ajax")
public class Servlet_MuutaAlusta_Ajax extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Servlet_MuutaAlusta_Ajax() {
        super();
        System.out.println("Servlet_MuutaAlusta_Ajax.Servlet_MuutaAlusta_Ajax()");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Servlet_MuutaAlusta_Ajax.DoGet()");
        Dao dao = new Dao();
        try {
            String strJSON = dao.muutaTiedotJSON("pm_alustat", "Alustat_id", request.getParameter("id"), "Nimi", request.getParameter("Nimi"));
            PrintWriter out = response.getWriter();
            //            response.setContentType("text/html");
            out.println(strJSON);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
