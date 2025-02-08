package workshopjdbc;

import tn.edu.esprit.entities.ParticipantEvenement;
import tn.edu.esprit.services.ServiceParticipant;
import java.util.List;

public class WorkshopJDBC {
    public static void main(String[] args) {
        ServiceParticipant sp = new ServiceParticipant();

        ParticipantEvenement p1 = new ParticipantEvenement("souha", "jomaa", "souhajomaa@gmail.com", 25);
        ParticipantEvenement p2 = new ParticipantEvenement("seif", "jomaa", "seifjomaa@gmail.com", 30);
        ParticipantEvenement p3 = new ParticipantEvenement("ahmed", "belkacem", "ahmedbelkacem@gmail.com", 28);

        sp.ajouter(p1);
        sp.ajouter(p2);
        sp.ajouter(p3);

        p1.setId(1);
        p1.setNom("Mohamed");
        sp.modifier(p1);

        sp.supprimer(1);

        List<ParticipantEvenement> participants = sp.getAll();
        for (ParticipantEvenement p : participants) {
            System.out.println(p);
        }
    }
}
