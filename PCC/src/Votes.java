
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author b_tre
 */
public class Votes {
    public class Vote {
        public String vTh1;
        public String vTh2;
        public String vTh3;

        public Vote(String pVote)
        {
            vTh1 = pVote.substring(0, 4);
            vTh2 = pVote.substring(4, 8);
            vTh3 = pVote.substring(8, 12);
        }
    }
    public ArrayList<Vote> listeVotes = new ArrayList<>();
    public ConnexionBDD c;

    public Votes (ConnexionBDD cBDD)
    {
        c = cBDD;
    }
    public void NouveauVote() throws SQLException
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Vote ?");
        String serializedVote = sc.nextLine();

        Vote v = new Vote(serializedVote);

        listeVotes.add(v);

        c.EnregistrerVote(v);
    }
}
