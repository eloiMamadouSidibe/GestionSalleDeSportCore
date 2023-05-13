/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ems.gestionsallesport.core.gestionsallesport.dao;

import com.ems.gestionsallesport.core.gestionsallesport.HibernateUtil;
import com.ems.gestionsallesport.core.gestionsallesport.entity.HistoriqueEmploi;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class HistoriqueEmploiDao {
    public String insert(HistoriqueEmploi historiqueEmploi) {
        Session session = null;
        Transaction tx = null;
        String message;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(historiqueEmploi);
            tx.commit();
            message = "L'insertion de l'historique de l'employe réussi";

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
    public HistoriqueEmploi getById(int id) {
        Session session = null;
        HistoriqueEmploi historiqueEmploi = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            historiqueEmploi = session.get(HistoriqueEmploi.class, id);
        } catch (PersistenceException e) {
            System.out.println("erreur : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }

            return historiqueEmploi;
        }

    }
    public String update(int id, HistoriqueEmploi updateHistoriqueEmploi) {
        Session session = null;
        HistoriqueEmploi historiqueEmploi = null;
        String message = "Erreur lors de la modification de l'historique de l'employe, vérifez si l'identifiant de l'historique existe";
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            historiqueEmploi = session.get(HistoriqueEmploi.class, id);
            tx = session.beginTransaction();
            if (historiqueEmploi != null) {
                historiqueEmploi.setDate_debut(updateHistoriqueEmploi.getDate_debut());
                historiqueEmploi.setDate_fin(updateHistoriqueEmploi.getDate_fin());
                historiqueEmploi.setPoste(updateHistoriqueEmploi.getPoste());
                historiqueEmploi.setSalaire(updateHistoriqueEmploi.getSalaire());
                historiqueEmploi.setEmploye(updateHistoriqueEmploi.getEmploye());
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
        HistoriqueEmploi historiqueEmploi = null;
        String message = "Erreur lors de la suppresion de l'historique de l'employe, vérifez si l'identifiant de l'historique existe";
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            historiqueEmploi = session.get(HistoriqueEmploi.class, id);
            if (historiqueEmploi != null) {
                session.delete(historiqueEmploi);
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
    public List<HistoriqueEmploi> liste() {
        Session session = null;
        List<HistoriqueEmploi> historiqueEmploi = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            historiqueEmploi = session.createQuery("FROM HistoriqueEmploi").getResultList();
        } catch (PersistenceException e) {
            System.out.println("erreur : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return historiqueEmploi;
    }
}
