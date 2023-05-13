/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ems.gestionsallesport.core.gestionsallesport.dao;

import com.ems.gestionsallesport.core.gestionsallesport.HibernateUtil;
import com.ems.gestionsallesport.core.gestionsallesport.entity.Cours;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author User
 */
public class CoursDao {
    public String insert(Cours cours) {
        Session session = null;
        Transaction tx = null;
        String message;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(cours);
            tx.commit();
            message = "L'insertion du cours réussi";

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
    public Cours getById(int id) {
        Session session = null;
        Cours cours = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            cours = session.get(Cours.class, id);
        } catch (PersistenceException e) {
            System.out.println("erreur : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }

            return cours;
        }

    }
    public String update(int id, Cours updateCours) {
        Session session = null;
        Cours cours = null;
        String message = "Erreur lors de la modification du cours, vérifez si l'identifiant du cours existe";
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            cours = session.get(Cours.class, id);
            tx = session.beginTransaction();
            if (cours != null) {
                cours.setNom(updateCours.getNom());
                cours.setDescription(updateCours.getDescription());
                cours.setDuree(updateCours.getDuree());
                cours.setNiveau(updateCours.getNiveau());
                cours.setEmploye(updateCours.getEmploye());
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
        Cours cours = null;
        String message = "Erreur lors de la suppresion du cours, vérifez si l'identifiant du cours existe";
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            cours = session.get(Cours.class, id);
            if (cours != null) {
                session.delete(cours);
                tx.commit();
                message = ("Cours " + id + " supprimé avec succès");
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
    public List<Cours> liste() {
        Session session = null;
        List<Cours> cours = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            cours = session.createQuery("FROM Cours").getResultList();
        } catch (PersistenceException e) {
            System.out.println("erreur : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return cours;
    }
    
}
