/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ems.gestionsallesport.core.gestionsallesport.dao;

import com.ems.gestionsallesport.core.gestionsallesport.HibernateUtil;
import com.ems.gestionsallesport.core.gestionsallesport.entity.Paiement;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class PaiementDao {
     public String insert(Paiement paiement) {
        Session session = null;
        Transaction tx = null;
        String message;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(paiement);
            tx.commit();
            message = "L'insertion du paiement réussi";

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

    public Paiement getById(int id) {
        Session session = null;
        Paiement paiement = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            paiement = session.get(Paiement.class, id);
        } catch (PersistenceException e) {
            System.out.println("erreur : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }

            return paiement;
        }

    }

    public String update(int id, Paiement updatePaiement) {
        Session session = null;
        Paiement paiement = null;
        String message = "Erreur lors de la modification du paiement, vérifez si l'identifiant existe";
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            paiement = session.get(Paiement.class, id);
            tx = session.beginTransaction();
            if (paiement != null) {
                paiement.setDate(updatePaiement.getDate());
                paiement.setMembre(updatePaiement.getMembre());
                paiement.setMethode(updatePaiement.getMethode());
                paiement.setMontant(updatePaiement.getMontant());
                paiement.setStatut(updatePaiement.getStatut());
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
        Paiement paiement = null;
        String message = "Erreur lors de la suppresion de l'équipement, vérifez si l'identifiant de l'équipement existe";
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            paiement = session.get(Paiement.class, id);
            if (paiement != null) {
                session.delete(paiement);
                tx.commit();
                message = ("Paiement " + id + " supprimé avec succès");
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

    public List<Paiement> liste() {
        Session session = null;
        List<Paiement> paiements = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            paiements = session.createQuery("FROM Paiement").getResultList();
        } catch (PersistenceException e) {
            System.out.println("erreur : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return paiements;
    }
}
