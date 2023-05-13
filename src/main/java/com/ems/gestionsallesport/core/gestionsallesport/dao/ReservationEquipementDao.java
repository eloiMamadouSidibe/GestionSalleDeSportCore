/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ems.gestionsallesport.core.gestionsallesport.dao;

import com.ems.gestionsallesport.core.gestionsallesport.HibernateUtil;
import com.ems.gestionsallesport.core.gestionsallesport.entity.ReservationEquipement;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class ReservationEquipementDao {
        public String insert(ReservationEquipement reservationEquipement) {
            Session session = null;
            Transaction tx = null;
            String message;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                tx = session.beginTransaction();
                session.persist(reservationEquipement);
                tx.commit();
                message = "Réservation de l'équipement ajouté";

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

        public ReservationEquipement getById(int id) {
            Session session = null;
            ReservationEquipement reservationEquipement = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                reservationEquipement = session.get(ReservationEquipement.class, id);
            } catch (PersistenceException e) {
                System.out.println("erreur : " + e.getMessage());
            } finally {
                if (session != null) {
                    session.close();
                }

                return reservationEquipement;
            }

        }
        

        public String update(int id, ReservationEquipement updateReservationEquipement) {
            Session session = null;
            ReservationEquipement reservationEquipement = null;
            String message = "Erreur lors de la modification de la réservation, vérifez si l'identifiant de la réservation existe";
            Transaction tx = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                reservationEquipement = session.get(ReservationEquipement.class, id);
                tx = session.beginTransaction();
                if (reservationEquipement != null) {
                    reservationEquipement.setDate(updateReservationEquipement.getDate());
                    reservationEquipement.setEquipements(updateReservationEquipement.getEquipements());
                    reservationEquipement.setHeure_debut(updateReservationEquipement.getHeure_debut());
                    reservationEquipement.setHeure_fin(updateReservationEquipement.getHeure_fin());
                    reservationEquipement.setMembre(updateReservationEquipement.getMembre());
                    reservationEquipement.setStatut(updateReservationEquipement.getStatut());
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
            ReservationEquipement reservationEquipement = null;
            String message = "Erreur lors de la suppresion de la réservation, vérifez si l'identifiant de la réservation existe";
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                tx = session.beginTransaction();
                reservationEquipement = session.get(ReservationEquipement.class, id);
                if (reservationEquipement != null) {
                    session.delete(reservationEquipement);
                    tx.commit();
                    message = ("Réservation " + id + " supprimé avec succès");
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

        public List<ReservationEquipement> liste() {
            Session session = null;
            List<ReservationEquipement> reservationEquipement = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                reservationEquipement = session.createQuery("FROM ReservationEquipement").getResultList();
            } catch (PersistenceException e) {
                System.out.println("erreur : " + e.getMessage());
            } finally {
                if (session != null) {
                    session.close();
                }
            }
            return reservationEquipement;
        }
}
