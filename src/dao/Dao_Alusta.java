package dao;

import model.Alusta;

import java.util.ArrayList;

public class Dao_Alusta extends Dao {

    public ArrayList<Alusta> haeAlustat() throws Exception {
        ArrayList<Alusta> alustat = new ArrayList<Alusta>();
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

}
