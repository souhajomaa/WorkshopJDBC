package workshopjdbc;

import tn.edu.esprit.entities.Personne;
import tn.edu.esprit.services.ServicePersonne;

import java.util.List;

public class WorkshopJDBC {

    public static void main(String[] args) {
        ServicePersonne sp = new ServicePersonne();

        // Ajouter une personne
        Personne p1 = new Personne("souha", "jomaa");
        Personne p2 = new Personne("seif", "jomaa");
        Personne p3 = new Personne("ahmed", "belkacem");


        sp.ajouter(p1);
        sp.ajouter(p2);
        sp.ajouter(p3);



        // Modifier une personne (remplace ID par un ID existant)
        p1.setId(1);
        p1.setNom("Mohamed");
        sp.modifier(p1);

        // Supprimer une personne (remplace ID par un ID existant)
        sp.supprimer(1);

        // Afficher toutes les personnes
        List<Personne> personnes = sp.getAll(new Personne());
        for (Personne p : personnes) {
            System.out.println(p);
        }
    }
}
