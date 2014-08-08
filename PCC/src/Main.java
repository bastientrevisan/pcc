
import java.sql.SQLException;

public class Main {
    public static int nb_participants = 80;
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnexionBDD c = new ConnexionBDD();
        c.ConnexionSQLite("pcc.db");
        //c.InitDatabase(nb_participants);
        Participants p = new Participants(c);
        //p.NouveauParticipant();
        Votes v = new Votes(c);
        v.NouveauVote();
        
    }
}