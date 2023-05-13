/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ems.gestionsallesport.core.gestionsallesport.dao;

import com.ems.gestionsallesport.core.gestionsallesport.HibernateUtil;
import com.ems.gestionsallesport.core.gestionsallesport.entity.Membre;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class MembreDao {
    public String insert(Membre membre) {
        Session session = null;
        Transaction tx = null;
        String message;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(membre);
            tx.commit();
            message = "L'insertion du membre réussi";

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
      public Membre getById(int id) {
        Session session = null;
        Membre membre = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            membre = session.get(Membre.class, id);
        } catch (PersistenceException e) {
            System.out.println("erreur : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }

            return membre;
        }

    }
       public String update(int id, Membre updateMembre) {
        Session session = null;
        Membre membre = null;
        String message = "Erreur lors de la modification du membre, vérifez si l'identifiant du membre existe";
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            membre = session.get(Membre.class, id);
            tx = session.beginTransaction();
            if (membre != null) {
                membre.setNom(updateMembre.getNom());
                membre.setPrenom(updateMembre.getPrenom());
                membre.setAdresse(updateMembre.getAdresse());
                membre.setAbonnement(updateMembre.getAbonnement());
                membre.setDate_naissance(updateMembre.getDate_naissance());
                membre.setEmail(updateMembre.getEmail());
                membre.setPays(updateMembre.getPays());
                membre.setSexe(updateMembre.isSexe());
                membre.setTel(updateMembre.getTel());
                membre.setVille(updateMembre.getVille());
                
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
        Membre membre = null;
        String message = "Erreur lors de la suppresion du membre, vérifez si l'identifiant du membre existe";
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            membre = session.get(Membre.class, id);
            if (membre != null) {
                session.delete(membre);
                tx.commit();
                message = ("Membre " + id + " supprimé avec succès");
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
    public List<Membre> liste() {
        Session session = null;
        List<Membre> membres = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            membres = session.createQuery("FROM Membre").getResultList();
        } catch (PersistenceException e) {
            System.out.println("erreur : " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return membres;
    }
    public Membre getByEmail(String email){
        Session session = null;
        Membre membre = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            membre = (Membre) session.createQuery("From Membre WHERE email = :email").setParameter("email", email).uniqueResult();
            
        } catch (PersistenceException e) {
            System.out.println("erreur "+e.getMessage());
        }
        finally{
            if(session!=null){
                session.close();
            }
        }
        return membre;
    }
    
}
