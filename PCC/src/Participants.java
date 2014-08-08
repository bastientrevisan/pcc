
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class Participants {
    public class Participant {
        public String num;
        public String nom;
        public String prenom;

        public Participant(String pNum, String pNom, String pPrenom)
        {
            num = pNum;
            nom = pNom;
            prenom = pPrenom;
        }
    }

    public ArrayList<Participant> listePart = new ArrayList<>();
    public ConnexionBDD c;

    public Participants (ConnexionBDD cBDD)
    {
        c = cBDD;
    }
    public void NouveauParticipant() throws SQLException
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Num ?");
        String num = sc.nextLine();
        System.out.println("Nom ?");
        String nom = sc.nextLine();
        System.out.println("Prénom ?");
        String prenom = sc.nextLine();

        Participant p = new Participant(num, nom, prenom);

        listePart.add(p);

        c.EnregistrerParticipant(p);
    }
}
