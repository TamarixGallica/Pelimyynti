package servlet;

import dao.Dao_Alusta;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/Servlet_PoistaAlusta_Ajax")
public class Servlet_PoistaAlusta_Ajax extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Servlet_PoistaAlusta_Ajax() {
        super();
        System.out.println("Servlet_PoistaAlusta_Ajax.Servlet_PoistaAlusta_Ajax()");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Servlet_PoistaAlusta_Ajax.doGet()");
        Dao_Alusta dao = new Dao_Alusta();
        try {
            String strJSON = dao.poistaTiedotJSON("pm_alustat", "Alustat_id", request.getParameter("id"));
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            out.println(strJSON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
