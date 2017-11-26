package dao;

import org.mariadb.jdbc.MariaDbDataSource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.Properties;

public class Dao {
    public Connection con = null;
    public ResultSet rs = null;
    public PreparedStatement stmtPrep = null;
    public String sql;

    public Connection yhdista() throws Exception {
        Connection con = null;
        Properties prop = new Properties();
        InputStream input = null;
        OutputStream output = null;
        int retryCount = 0;

        try {
            input = this.getClass().getResourceAsStream("/dao/database.properties");
            prop.load(input);
        } catch( Exception ex) {
            ex.printStackTrace();
        }
        Class.forName(prop.getProperty("driver"));
        while(con==null && retryCount<5) {
            try {
                con = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
            } catch (Exception e) {
                Thread.sleep(100);
                retryCount++;
            }
        }
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
            System.out.println(stmtPrep);
            try {
                rs = stmtPrep.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                if(rs!=null) {
                    palautusJSON+= "[{\"status\": \"OK\"},]";
                }
            } catch(java.sql.SQLIntegrityConstraintViolationException ex)
            {
                palautusJSON += "[{\"status\": \"ERROR\", \"message\": \""+ex.getErrorCode()+"\"},]";
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

    public String muutaTiedotJSON(String taulu, String ehtoSarake, String ehtoArvo, String muutettavaSarake, String uusiArvo) throws Exception {
        String palautusJSON = "";

        sql = "UPDATE " + taulu + " SET "+ muutettavaSarake + "=? WHERE "+ ehtoSarake + "=" + ehtoArvo;
        System.out.println(sql);

        con = yhdista();
        if(con != null) {
            stmtPrep = con.prepareStatement(sql);
            stmtPrep.setString(1, uusiArvo);
            try {
                rs = stmtPrep.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                if(rs!=null) {
                    palautusJSON+= "[{\"status\": \"OK\"},]";
                }
            } catch(java.sql.SQLIntegrityConstraintViolationException ex)
            {
                palautusJSON += "[{\"status\": \"ERROR\", \"message\": \""+ex.getErrorCode()+"\"},]";
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
