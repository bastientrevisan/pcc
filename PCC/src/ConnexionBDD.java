
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author b_tre
 */
public class ConnexionBDD {
    private Connection c;
    
    public Connection ConnexionSQLite(String database) throws ClassNotFoundException, SQLException
    {
        Class.forName("org.sqlite.JDBC");
        System.out.println("Driver SQLite trouvé");
        c = DriverManager.getConnection("jdbc:sqlite:" + database);
        System.out.println("Connexion a PCC avec succès");
        
        return c;
    }
    
    public void InitDatabase(int nb_part) throws SQLException
    {
        Statement s = c.createStatement();
        int i = 0;
        /* table Participants */
        s.execute("DROP TABLE IF EXISTS PARTICIPANTS");
        s.execute("CREATE TABLE PARTICIPANTS (NUM INTEGER PRIMARY KEY,"
                    +  " NOM VARCHAR(20),  "
                    +  " PRENOM VARCHAR(20))");
        for (i = 0; i < nb_part; i++)
        {
            s.executeUpdate("INSERT INTO PARTICIPANTS (NOM, PRENOM) VALUES('', '')");
        }
        
        /* table Photos*/
        s.execute("DROP TABLE IF EXISTS PHOTOS");
        s.execute("CREATE TABLE PHOTOS (NUM VARCHAR(4) PRIMARY KEY, "
                                    +" THEME INT, "
                                    +" PART INT,  "
                                    +" FOREIGN KEY(PART) REFERENCES PARTICIPANTS(NUM))");
        for (i = 1; i <= nb_part; i++)
        {
            if (i < 10 )
            {
                s.execute("INSERT INTO PHOTOS VALUES('0"+i+"11', 1, "+i+")");
                s.execute("INSERT INTO PHOTOS VALUES('0"+i+"12', 1, "+i+")");
                s.execute("INSERT INTO PHOTOS VALUES('0"+i+"21', 2, "+i+")");
                s.execute("INSERT INTO PHOTOS VALUES('0"+i+"22', 2, "+i+")");
                s.execute("INSERT INTO PHOTOS VALUES('0"+i+"31', 3, "+i+")");
                s.execute("INSERT INTO PHOTOS VALUES('0"+i+"32', 3, "+i+")");
            } else {
                s.execute("INSERT INTO PHOTOS VALUES('"+i+"11', 1, "+i+")");
                s.execute("INSERT INTO PHOTOS VALUES('"+i+"12', 1, "+i+")");
                s.execute("INSERT INTO PHOTOS VALUES('"+i+"21', 2, "+i+")");
                s.execute("INSERT INTO PHOTOS VALUES('"+i+"22', 2, "+i+")");
                s.execute("INSERT INTO PHOTOS VALUES('"+i+"31', 3, "+i+")");
                s.execute("INSERT INTO PHOTOS VALUES('"+i+"32', 3, "+i+")");
            }
        }
        
        /* table votes*/
        s.execute("DROP TABLE IF EXISTS VOTES");
        s.execute("CREATE TABLE VOTES (NUM INTEGER PRIMARY KEY,"
                                    +"TH1 VARCHAR(4),"
                                    +"TH2 VARCHAR(4),"
                                    +"TH3 VARCHAR(4))");
        /* Pour tests */
        s.execute("INSERT INTO VOTES(TH1, TH2, TH3) VALUES('1211', '1222', '1131')");
        s.execute("INSERT INTO VOTES(TH1, TH2, TH3) VALUES('1012', '1121', '1231')");
        s.execute("INSERT INTO VOTES(TH1, TH2, TH3) VALUES('1212', '1021', '0531')");
        s.execute("INSERT INTO VOTES(TH1, TH2, TH3) VALUES('0911', '1222', '1232')");
        s.execute("INSERT INTO VOTES(TH1, TH2, TH3) VALUES('1012', '1021', '1131')");
        s.execute("INSERT INTO VOTES(TH1, TH2, TH3) VALUES('1112', '0922', '1031')");
        s.execute("INSERT INTO VOTES(TH1, TH2, TH3) VALUES('1011', '1021', '1131')");
        s.execute("INSERT INTO VOTES(TH1, TH2, TH3) VALUES('1012', '1121', '1031')");
        s.execute("INSERT INTO VOTES(TH1, TH2, TH3) VALUES('8912', '1222', '1131')");
    }
    public Connection GetConnection() {
        return c;
    }
    
    public void EnregistrerParticipant(Participants.Participant p) throws SQLException
    {
        Statement s = c.createStatement();
        s.executeUpdate("UPDATE PARTICIPANTS "
                + "SET NOM = '" + p.nom + "', "
                + "    PRENOM = '" + p.prenom + "'"
                + "WHERE NUM = " + p.num);
    }
    
    public void EnregistrerVote(Votes.Vote v) throws SQLException
    {
        Statement s = c.createStatement();
        s.executeUpdate("INSERT INTO VOTES(TH1, TH2, TH3) VALUES('"+v.vTh1+"', "
                + "'"+v.vTh2+"', "
                + "'"+v.vTh3+"')");
    }
    public void Deconnexion() throws SQLException
    {
        c.close();
    }
}
