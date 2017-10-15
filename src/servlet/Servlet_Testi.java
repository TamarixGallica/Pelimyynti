package servlet;

import dao.Dao_Alusta;
import dao.Dao_Artikkeli;
import model.Alusta;
import model.Artikkeli;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/*
Testi-servlet, joka tulostaa kaikki artikkelit ja alustat konsoliin.
 */

@WebServlet("/Servlet_Testi")
public class Servlet_Testi extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Servlet_Testi() {
        super();
        System.out.println("Servlet_Testi");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Servlet_Testi.doGet()");

        Dao_Alusta dao = new Dao_Alusta();
        try {
            ArrayList<Alusta> alustat = dao.haeAlustat();
            for (Alusta alusta : alustat) {
                System.out.println(alusta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Dao_Artikkeli dao2 = new Dao_Artikkeli();
        try {
            ArrayList<Artikkeli> artikkelit = dao2.haeArtikkelit();
            for (Artikkeli artikkeli : artikkelit) {
                System.out.println(artikkeli);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
