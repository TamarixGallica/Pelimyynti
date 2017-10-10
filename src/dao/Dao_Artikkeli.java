package dao;

import model.Artikkeli;

import java.util.ArrayList;

public class Dao_Artikkeli extends Dao {

    public ArrayList<Artikkeli> haeAlustat() throws Exception {
        ArrayList<Artikkeli> artikkelit= new ArrayList<Artikkeli>();
        sql = "SELECT * FROM pm_artikkelit";
        con = yhdista();
        if(con!=null) {
            stmtPrep = con.prepareStatement(sql);
            rs = stmtPrep.executeQuery();
            if(rs!=null) {
                while(rs.next()) {
                    Artikkeli artikkeli = new Artikkeli();
                    artikkeli.setArtikkeli_id(rs.getInt("Artikkelit_id"));
                    artikkeli.setNimi(rs.getString("Nimi"));
                    artikkelit.add(artikkeli);
                }
            }
            con.close();
        }
        return artikkelit;
    }

}
