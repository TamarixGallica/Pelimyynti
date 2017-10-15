package dao;

import model.Artikkeli;

import java.util.ArrayList;

public class Dao_Artikkeli extends Dao {

    public ArrayList<Artikkeli> haeArtikkelit() throws Exception {
        ArrayList<Artikkeli> artikkelit= new ArrayList<Artikkeli>();
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

    public boolean lisaaArtikkelit(Artikkeli artikkeli) {
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

}
