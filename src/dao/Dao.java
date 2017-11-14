package dao;

import java.sql.*;

public class Dao {
    public Connection con = null;
    public ResultSet rs = null;
    public PreparedStatement stmtPrep = null;
    public String sql;

    public Connection yhdista() throws Exception {
        Connection con = null;
        String JDBCAjuri = "org.mariadb.jdbc.Driver";
        String url = "jdbc:mariadb://localhost:3306/database";
        Class.forName(JDBCAjuri);
        con = DriverManager.getConnection(url, "user", "password");
        return con;
    }

    public void sulje() throws Exception {
        if(con!=null) {
            con.close();
        }
    }


    public String haeTiedotJSON(String[] sarakkeet, String taulu) throws Exception {
        return haeTiedotJSON(sarakkeet, taulu, "", "");
    }

    public String haeTiedotJSON(String[] sarakkeet, String taulu, String ehtoSarake, String ehtoArvo) throws Exception {
        String palautusJSON = "";
        String sarakeStr = "";

        // Ketjutetaan kaikki annetut sarakkeet pilkulla erotettuna paitsi viimeinen
        for(int i=0; i < (sarakkeet.length-1); i++) {
            sarakeStr += sarakkeet[i] + ",";
        }
        // Lisätään viimeinen sarake ilman pilkkua
        sarakeStr += sarakkeet[sarakkeet.length-1];

        sql = "SELECT " + sarakeStr + " FROM " + taulu;

        if(ehtoSarake.length()>0){
            sql += " WHERE "+ehtoSarake+"=?";
        }

        System.out.println(sql);
        con = yhdista();
        if(con != null) {
            stmtPrep = con.prepareStatement(sql);
            if(ehtoSarake.length()>0){
                stmtPrep.setString(1, ehtoArvo);
            }
            rs = stmtPrep.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            if(rs!=null) {
                int sarLkm = rsmd.getColumnCount();
                palautusJSON += "[";
                while (rs.next()) {
                    palautusJSON += "{";
                    for (int i = 1; i <= sarLkm; i++) {
                        palautusJSON += "\"";
                        palautusJSON += rsmd.getColumnName(i);
                        palautusJSON += "\":";
                        palautusJSON += "\"";
                        try {
                            palautusJSON += rs.getString(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        palautusJSON += "\"";
                        if (i < sarLkm) {
                            palautusJSON += ',';
                        }
                    }
                    palautusJSON += "}";
                    palautusJSON += ",";
                }
                palautusJSON += "]";
            }

            con.close();


        }
        //Siivotaan viimeinen pilkku pois
        palautusJSON = palautusJSON.substring(0, palautusJSON.length()-2) + "]";

        if(palautusJSON.length()==1){
            palautusJSON="{}";
        }

        return palautusJSON;
    }

    public String poistaTiedotJSON(String taulu, String ehtoSarake, String ehtoArvo) throws Exception {
        String palautusJSON = "";

        sql = "DELETE FROM " + taulu + " WHERE "+ ehtoSarake + "=?";
        System.out.println(sql);

        con = yhdista();
        if(con != null) {
            stmtPrep = con.prepareStatement(sql);
            stmtPrep.setString(1, ehtoArvo);
            rs = stmtPrep.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            if(rs!=null) {
                int sarLkm = rsmd.getColumnCount();
                palautusJSON += "[";
                while (rs.next()) {
                    palautusJSON += "{";
                    for (int i = 1; i <= sarLkm; i++) {
                        palautusJSON += "\"";
                        palautusJSON += rsmd.getColumnName(i);
                        palautusJSON += "\":";
                        palautusJSON += "\"";
                        try {
                            palautusJSON += rs.getString(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        palautusJSON += "\"";
                        if (i < sarLkm) {
                            palautusJSON += ',';
                        }
                    }
                    palautusJSON += "}";
                    palautusJSON += ",";
                }
                palautusJSON += "]";
            }

            con.close();


        }
        //Siivotaan viimeinen pilkku pois
        palautusJSON = palautusJSON.substring(0, palautusJSON.length()-2) + "]";

        if(palautusJSON.length()==1){
            palautusJSON="{}";
        }

        return palautusJSON;
    }
}
