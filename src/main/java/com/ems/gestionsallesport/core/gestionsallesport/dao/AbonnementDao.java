/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ems.gestionsallesport.core.gestionsallesport.dao;

import com.ems.gestionsallesport.core.gestionsallesport.HibernateUtil;
import com.ems.gestionsallesport.core.gestionsallesport.entity.Abonnement;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class AbonnementDao {

    public String insert(Abonnement abonnement) {
        Session session = null;
        Transaction tx = null;
        String message;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(abonnement);
            tx.commit();
            message = "L'insertion de l'abonnement réussi";

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

    public Abonnement getById(int id) {
        Session session = null;
        Abonnement abonnement = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            abonnement = session.get(Abonnement.class, id);
        } catch (PersistenceException e) {
            System.out.println("erreur : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }

            return abonnement;
        }

    }

    public String update(int id, Abonnement updateAbonnement) {
        Session session = null;
        Abonnement abonnement = null;
        String message = "Erreur lors de la modification de l'abonnement, vérifez si l'identifiant de l'abonnement existe";
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            abonnement = session.get(Abonnement.class, id);
            tx = session.beginTransaction();
            if (abonnement != null) {
                abonnement.setDescription(updateAbonnement.getDescription());
                abonnement.setDuree(updateAbonnement.getDuree());
                abonnement.setNom(updateAbonnement.getNom());
                abonnement.setPrix(updateAbonnement.getPrix());
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
        Abonnement abonnement = null;
        String message = "Erreur lors de la suppresion de l'abonnement, vérifez si l'identifiant de l'abonnement existe";
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            abonnement = session.get(Abonnement.class, id);
            if (abonnement != null) {
                session.delete(abonnement);
                tx.commit();
                message = ("Abonnement " + id + " supprimé avec succès");
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

    public List<Abonnement> liste() {
        Session session = null;
        List<Abonnement> abonnements = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            abonnements = session.createQuery("FROM Abonnement").getResultList();
        } catch (PersistenceException e) {
            System.out.println("erreur : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return abonnements;
    }

}
