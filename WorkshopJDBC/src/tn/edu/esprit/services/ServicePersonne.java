/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.edu.esprit.entities.Personne;
import tn.edu.esprit.tools.DataSource;

/**
 *
 * @author abdelazizmezri
 */
public class ServicePersonne implements IService<Personne> {
Connection cnx ;

public ServicePersonne(){
    this.cnx= DataSource.getInstance().getConnection();
}

    @Override
    public void ajouter(Personne t) {
        try {
            String req = "INSERT INTO `personne`( `nom`, `prenom`) VALUES ('" + t.getNom() + "','" + t.getPrenom() + "')";
            
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Personne t) {
        try {
            String req = "UPDATE personne SET nom = ?, prenom = ? WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
            ps.setInt(3, t.getId());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Personne mise à jour avec succès !");
            } else {
                System.out.println("Aucune personne trouvée avec cet ID.");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour : " + ex.getMessage());
        }
    }



    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM personne WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Personne supprimée avec succès !");
            } else {
                System.out.println("Aucune personne trouvée avec cet ID.");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression : " + ex.getMessage());
        }
    }


    @Override
    public Personne getOne(Personne t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Personne> getAll(Personne t) {
      String req = "SELECT * FROM `personne`";
      ArrayList<Personne> personnes = new ArrayList<>();
    Statement stm;
    try {
        stm = this.cnx.createStatement();
    
    
        ResultSet rs=  stm.executeQuery(req);
    while (rs.next()){
        Personne p = new Personne();
        p.setId(rs.getInt(1));
        p.setNom(rs.getString("nom"));
        p.setPrenom(rs.getString(3));
        
        personnes.add(p);
    }
        
        
    } catch (SQLException ex) {
    
        System.out.println(ex.getMessage());
    
    }
    return personnes;
    }

}
