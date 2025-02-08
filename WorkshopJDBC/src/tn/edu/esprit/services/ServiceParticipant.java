package tn.edu.esprit.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import tn.edu.esprit.entities.ParticipantEvenement;
import tn.edu.esprit.tools.DataSource;

public class ServiceParticipant {
    Connection cnx;

    public ServiceParticipant() {
        this.cnx = DataSource.getInstance().getConnection();
    }

    public void ajouter(ParticipantEvenement p) {
        try {
            String req = "INSERT INTO participant_evenement (nom, prenom, email, age) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getEmail());
            ps.setInt(4, p.getAge());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout : " + ex.getMessage());
        }
    }

    public void modifier(ParticipantEvenement p) {
        try {
            String req = "UPDATE participant_evenement SET nom = ?, prenom = ?, email = ?, age = ? WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getEmail());
            ps.setInt(4, p.getAge());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour : " + ex.getMessage());
        }
    }

    public void supprimer(int id) {
        try {
            String req = "DELETE FROM participant_evenement WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression : " + ex.getMessage());
        }
    }

    public List<ParticipantEvenement> getAll() {
        String req = "SELECT * FROM participant_evenement";
        List<ParticipantEvenement> participants = new ArrayList<>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) {
                participants.add(new ParticipantEvenement(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getInt("age")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération : " + ex.getMessage());
        }
        return participants;
    }
}
