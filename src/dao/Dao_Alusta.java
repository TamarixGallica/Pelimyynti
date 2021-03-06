package dao;

import com.google.gson.Gson;
import model.Alusta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao_Alusta extends Dao {

    public ArrayList<Alusta> haeAlustat() throws Exception {
        ArrayList<Alusta> alustat = new ArrayList<>();
        sql = "SELECT * FROM pm_alustat";
        con = yhdista();
        if(con!=null) {
            stmtPrep = con.prepareStatement(sql);
            rs = stmtPrep.executeQuery();
            if(rs!=null) {
                while(rs.next()) {
                    Alusta alusta = new Alusta();
                    alusta.setAlusta_id(rs.getInt("Alustat_id"));
                    alusta.setNimi(rs.getString("Nimi"));
                    alustat.add(alusta);
                }
            }
            con.close();
        }
        return alustat;
    }

    public boolean lisaaAlusta(Alusta alusta) {
        boolean paluuArvo = true;

        sql = "INSERT INTO pm_alustat(Nimi) VALUES(?)";

        try {
            con = yhdista();
            stmtPrep = con.prepareStatement(sql);
            stmtPrep.setString(1, alusta.getNimi());
            stmtPrep.executeQuery();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            paluuArvo = false;
        }

        return paluuArvo;
    }

    public int lisaaAlustaAjax(Alusta alusta) {
        int paluuArvo;

        sql = "INSERT INTO pm_alustat(Nimi) VALUES(?)";

        try {
            con = yhdista();
            stmtPrep = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmtPrep.setString(1, alusta.getNimi());
            rs = stmtPrep.executeQuery();
            try (ResultSet generatedKeys = stmtPrep.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    paluuArvo=generatedKeys.getInt("Alustat_id");
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            paluuArvo = -1;
        }

        return paluuArvo;
    }

    public String haeTiedotJSON(String ehtoSarake, String ehtoArvo) throws Exception {
        ArrayList<Alusta> lista = new ArrayList<>();

        String ehtolause="";
        if(ehtoSarake.length()>0){
            ehtolause = " WHERE "+ehtoSarake+"=?";
        }

        sql = "SELECT * FROM pm_alustat ORDER BY Nimi";


        System.out.println(sql);
        con = yhdista();
        if(con != null) {
            stmtPrep = con.prepareStatement(sql);
            if(ehtolause.length()>0){
                stmtPrep.setString(1, ehtoArvo);
            }
            System.out.println(stmtPrep);
            rs = stmtPrep.executeQuery();
            if(rs!=null) {
                while(rs.next())
                {
                    Alusta alusta = new Alusta();
                    alusta.setAlusta_id(rs.getInt("Alustat_Id"));
                    alusta.setNimi(rs.getString("Nimi"));
                    lista.add(alusta);
                }
            }

            con.close();


        }

        return new Gson().toJson(lista);
    }

}
