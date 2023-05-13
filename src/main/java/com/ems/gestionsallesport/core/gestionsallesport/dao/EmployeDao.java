/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ems.gestionsallesport.core.gestionsallesport.dao;

import com.ems.gestionsallesport.core.gestionsallesport.HibernateUtil;
import com.ems.gestionsallesport.core.gestionsallesport.entity.Employe;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class EmployeDao {
     public String insert(Employe employe) {
        Session session = null;
        Transaction tx = null;
        String message;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(employe);
            tx.commit();
            message = "L'insertion de l'employe réussi";

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
      public Employe getById(int id) {
        Session session = null;
        Employe employe = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            employe = session.get(Employe.class, id);
        } catch (PersistenceException e) {
            System.out.println("erreur : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }

            return employe;
        }

    }
       public String update(int id, Employe updateEmploye) {
        Session session = null;
        Employe employe = null;
        String message = "Erreur lors de la modification de l'employe, vérifez si l'identifiant de l'employe existe";
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            employe = session.get(Employe.class, id);
            tx = session.beginTransaction();
            if (employe != null) {
                employe.setNom(updateEmploye.getNom());
                employe.setPrenom(updateEmploye.getPrenom());
                employe.setAdresse(updateEmploye.getAdresse());
                employe.setDate_embauche(updateEmploye.getDate_embauche());
                employe.setDate_naissance(updateEmploye.getDate_naissance());
                employe.setEmail(updateEmploye.getEmail());
                employe.setPays(updateEmploye.getPays());
                employe.setPoste(updateEmploye.getPoste());
                employe.setSalaire(updateEmploye.getSalaire());
                employe.setSexe(updateEmploye.isSexe());
                employe.setTel(updateEmploye.getTel());
                employe.setVille(updateEmploye.getVille());
                
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
        Employe employe = null;
        String message = "Erreur lors de la suppresion de l'employe, vérifez si l'identifiant de l'employe existe";
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            employe = session.get(Employe.class, id);
            if (employe != null) {
                session.delete(employe);
                tx.commit();
                message = ("Employe " + id + " supprimé avec succès");
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
    public List<Employe> liste() {
        Session session = null;
        List<Employe> employes = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            employes = session.createQuery("FROM Employe").getResultList();
        } catch (PersistenceException e) {
            System.out.println("erreur : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employes;
    }
}
