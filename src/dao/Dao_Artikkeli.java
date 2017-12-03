package dao;

import com.google.gson.Gson;
import model.Alusta;
import model.Artikkeli;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao_Artikkeli extends Dao {

    public ArrayList<Artikkeli> haeArtikkelit() throws Exception {
        ArrayList<Artikkeli> artikkelit= new ArrayList<>();
        sql = "SELECT * FROM pm_artikkelit";
//        sql = "SELECT alu.Nimi AS Alusta,art.Nimi,art.Lisatiedot,art.Nimi from pm_artikkelit_alustat pmaa join pm_artikkelit art on pmaa.Artikkelit_id = art.Artikkelit_id join pm_alustat alu on pmaa.Alustat_id = alu.Alustat_id ORDER BY alu.Nimi,art.Nimi"
        con = yhdista();
        if(con!=null) {
            stmtPrep = con.prepareStatement(sql);
            rs = stmtPrep.executeQuery();
            if(rs!=null) {
                while(rs.next()) {
                    Artikkeli artikkeli = new Artikkeli();
                    artikkeli.setArtikkeli_id(rs.getInt("Artikkelit_id"));
                    artikkeli.setNimi(rs.getString("Nimi"));
                    artikkeli.setLisatiedot(rs.getString("Lisatiedot"));
                    artikkeli.setPyyntihinta(rs.getFloat("Pyyntihinta"));
                    artikkelit.add(artikkeli);
                }
            }
            con.close();
        }
        return artikkelit;
    }

    public boolean lisaaArtikkeli(Artikkeli artikkeli) {
        boolean paluuArvo = true;

        sql = "INSERT INTO pm_artikkelit(Nimi, Lisatiedot, Pyyntihinta) VALUES(?, ?, ?)";

        try {
            con = yhdista();
            stmtPrep = con.prepareStatement(sql);
            stmtPrep.setString(1, artikkeli.getNimi());
            stmtPrep.setString(2, artikkeli.getLisatiedot());
            stmtPrep.setFloat(3, artikkeli.getPyyntihinta());
            stmtPrep.executeQuery();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            paluuArvo = false;
        }

        return paluuArvo;
    }

    public int lisaaArtikkeliAjax(Artikkeli artikkeli) {
        int paluuArvo;

        sql = "INSERT INTO pm_artikkelit(Nimi, Lisatiedot, Pyyntihinta) VALUES(?,?,?)";

        try {
            con = yhdista();
            stmtPrep = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmtPrep.setString(1, artikkeli.getNimi());
            stmtPrep.setString(2, artikkeli.getLisatiedot());
            stmtPrep.setFloat(3, artikkeli.getPyyntihinta());
            rs = stmtPrep.executeQuery();
            try (ResultSet generatedKeys = stmtPrep.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    paluuArvo=generatedKeys.getInt("Artikkelit_id");
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            sql = "INSERT INTO pm_artikkelit_alustat(Artikkelit_id, Alustat_id) VALUES (?, ?)";
            stmtPrep = con.prepareStatement(sql);
            stmtPrep.setInt(1, paluuArvo);
            stmtPrep.setInt(2, artikkeli.getAlusta().getAlusta_id());
            rs = stmtPrep.executeQuery();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            paluuArvo = -1;
        }

        return paluuArvo;
    }

    public String haeTiedotJSON() throws Exception {
        return this.haeTiedotJSON("", "");
    }

    public String haeTiedotJSON(String ehtoSarake, String ehtoArvo) throws Exception {
        String[] sarakkeet = {"Alusta_id", "Alusta_Nimi", "Artikkeli_Nimi", "Lisatiedot", "Pyyntihinta"};
        ArrayList<Artikkeli> lista = new ArrayList<>();

        String ehtolause="";
        if(ehtoSarake.length()>0){
            ehtolause = " WHERE "+ehtoSarake+"=?";
        }

        sql = "SELECT alu.Alustat_id as Alusta_Id, art.Artikkelit_id as Artikkeli_Id, alu.Nimi as Alusta_Nimi, art.Nimi as Artikkeli_Nimi, art.Lisatiedot, art.Pyyntihinta from pm_artikkelit_alustat pmaa join pm_artikkelit art on pmaa.Artikkelit_id = art.Artikkelit_id join pm_alustat alu on pmaa.Alustat_id = alu.Alustat_id "+ehtolause+"ORDER BY alu.Nimi,art.Nimi";


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
                    Artikkeli artikkeli = new Artikkeli();
                    artikkeli.setNimi(rs.getString("Artikkeli_Nimi"));
                    artikkeli.setArtikkeli_id(rs.getInt("Artikkeli_Id"));
                    artikkeli.setPyyntihinta(rs.getFloat("Pyyntihinta"));
                    artikkeli.setLisatiedot(rs.getString("Lisatiedot"));

                    Alusta alusta = new Alusta();
                    alusta.setAlusta_id(rs.getInt("Alusta_Id"));
                    alusta.setNimi(rs.getString("Alusta_Nimi"));
                    artikkeli.setAlusta(alusta);

                    lista.add(artikkeli);
                }
            }

            con.close();


        }

        return new Gson().toJson(lista);
    }
}
