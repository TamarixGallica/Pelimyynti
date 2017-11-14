package servlet;

import dao.Dao_Alusta;
import model.Alusta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Servlet_LisaaAlusta_Ajax")
public class Servlet_LisaaAlusta_Ajax extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Servlet_LisaaAlusta_Ajax() {
        super();
        System.out.println("Servlet_LisaaAlusta.Servlet_LisaaAlusta()");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet_LisaaAlusta.doGet()");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet_LisaaAlusta.doPost()");

        Alusta alusta = new Alusta();
        alusta.setNimi(request.getParameter("Nimi"));

        Dao_Alusta dao = new Dao_Alusta();
        int returnId = dao.lisaaAlustaAjax(alusta);

//        response.sendRedirect("alustahaku.jsp");
        response.setContentType("application/json");
        // Get the printwriter object from response to write the required json object to the output stream
        PrintWriter out = response.getWriter();
// Assuming your json object is **jsonObject**, perform the following, it will return your json object
        out.print(returnId);
        out.flush();

    }
}
