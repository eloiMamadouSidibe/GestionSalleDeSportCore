/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ems.gestionsallesport.core.gestionsallesport.dao;

import com.ems.gestionsallesport.core.gestionsallesport.HibernateUtil;
import com.ems.gestionsallesport.core.gestionsallesport.entity.HistoriqueAbonnement;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class HistoriqueAbonnementDao {
     public String insert(HistoriqueAbonnement historiqueAbonnement) {
        Session session = null;
        Transaction tx = null;
        String message;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(historiqueAbonnement);
            tx.commit();
            message = "L'insertion de l'historique d'abonnement réussi";

        } catch (PersistenceException e) {
            tx.rollback();
            message = "erreur : " + e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return message;
    }
    public HistoriqueAbonnement getById(int id) {
        Session session = null;
        HistoriqueAbonnement historiqueAbonnement = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            historiqueAbonnement = session.get(HistoriqueAbonnement.class, id);
        } catch (PersistenceException e) {
            System.out.println("erreur : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }

            return historiqueAbonnement;
        }

    }
    public String update(int id, HistoriqueAbonnement updateHistoriqueAbonnement) {
        Session session = null;
        HistoriqueAbonnement historiqueAbonnement = null;
        String message = "Erreur lors de la modification de l'historique d'abonnement, vérifez si l'identifiant de l'historique existe";
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            historiqueAbonnement = session.get(HistoriqueAbonnement.class, id);
            tx = session.beginTransaction();
            if (historiqueAbonnement != null) {
                historiqueAbonnement.setDate_debut(updateHistoriqueAbonnement.getDate_debut());
                historiqueAbonnement.setDate_fin(updateHistoriqueAbonnement.getDate_fin());
                historiqueAbonnement.setMembre(updateHistoriqueAbonnement.getMembre());
                historiqueAbonnement.setMontant(updateHistoriqueAbonnement.getMontant());
                historiqueAbonnement.setStatut(updateHistoriqueAbonnement.getStatut());
                historiqueAbonnement.setType(updateHistoriqueAbonnement.getType());
                tx.commit();
                message = "Modification effectuée avec succès";
            }

        } catch (PersistenceException e) {
            tx.rollback();
            message = "Erreur : " + e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }

            return message;
        }
    }
    public String delete(int id) {
        Session session = null;
        Transaction tx = null;
        HistoriqueAbonnement historiqueAbonnement = null;
        String message = "Erreur lors de la suppresion de l'historique d'abonnement, vérifez si l'identifiant de l'historique existe";
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            historiqueAbonnement = session.get(HistoriqueAbonnement.class, id);
            if (historiqueAbonnement != null) {
                session.delete(historiqueAbonnement);
                tx.commit();
                message = ("Historique " + id + " supprimé avec succès");
            }

        } catch (PersistenceException e) {
            tx.rollback();
            message = "Erreur : " + e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return message;
    }
    public List<HistoriqueAbonnement> liste() {
        Session session = null;
        List<HistoriqueAbonnement> historiqueAbonnement = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            historiqueAbonnement = session.createQuery("FROM HistoriqueAbonnement").getResultList();
        } catch (PersistenceException e) {
            System.out.println("erreur : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return historiqueAbonnement;
    }
    
}
