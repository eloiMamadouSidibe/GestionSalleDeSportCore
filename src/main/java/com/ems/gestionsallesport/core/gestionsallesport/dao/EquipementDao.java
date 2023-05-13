/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ems.gestionsallesport.core.gestionsallesport.dao;

import com.ems.gestionsallesport.core.gestionsallesport.HibernateUtil;
import com.ems.gestionsallesport.core.gestionsallesport.entity.Equipement;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class EquipementDao {
    public String insert(Equipement equipement) {
        Session session = null;
        Transaction tx = null;
        String message;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(equipement);
            tx.commit();
            message = "L'insertion de l'équipement réussi";

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

    public Equipement getById(int id) {
        Session session = null;
        Equipement equipement = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            equipement = session.get(Equipement.class, id);
        } catch (PersistenceException e) {
            System.out.println("erreur : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }

            return equipement;
        }

    }

    public String update(int id, Equipement updateEquipement) {
        Session session = null;
        Equipement equipement = null;
        String message = "Erreur lors de la modification de l'équipement, vérifez si l'identifiant de l'équipement existe";
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            equipement = session.get(Equipement.class, id);
            tx = session.beginTransaction();
            if (equipement != null) {
                equipement.setNom(updateEquipement.getNom());
                equipement.setDescription(updateEquipement.getDescription());
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
        Equipement equipement = null;
        String message = "Erreur lors de la suppresion de l'équipement, vérifez si l'identifiant de l'équipement existe";
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            equipement = session.get(Equipement.class, id);
            if (equipement != null) {
                session.delete(equipement);
                tx.commit();
                message = ("Equipement " + id + " supprimé avec succès");
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

    public List<Equipement> liste() {
        Session session = null;
        List<Equipement> equipements = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            equipements = session.createQuery("FROM Equipement").getResultList();
        } catch (PersistenceException e) {
            System.out.println("erreur : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return equipements;
    }
    
}
