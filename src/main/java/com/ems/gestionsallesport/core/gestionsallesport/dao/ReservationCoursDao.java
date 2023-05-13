/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ems.gestionsallesport.core.gestionsallesport.dao;

import com.ems.gestionsallesport.core.gestionsallesport.HibernateUtil;
import com.ems.gestionsallesport.core.gestionsallesport.entity.ReservationCours;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class ReservationCoursDao {


        public String insert(ReservationCours reservationCours) {
            Session session = null;
            Transaction tx = null;
            String message;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                tx = session.beginTransaction();
                session.persist(reservationCours);
                tx.commit();
                message = "Réservation du cours ajouté";

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

        public ReservationCours getById(int id) {
            Session session = null;
            ReservationCours reservationCours = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                reservationCours = session.get(ReservationCours.class, id);
            } catch (PersistenceException e) {
                System.out.println("erreur : " + e.getMessage());
            } finally {
                if (session != null) {
                    session.close();
                }

                return reservationCours;
            }

        }

        public String update(int id, ReservationCours updateReservationCours) {
            Session session = null;
            ReservationCours reservationCours = null;
            String message = "Erreur lors de la modification de la réservation, vérifez si l'identifiant de la réservation existe";
            Transaction tx = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                reservationCours = session.get(ReservationCours.class, id);
                tx = session.beginTransaction();
                if (reservationCours != null) {
                    reservationCours.setCours(updateReservationCours.getCours());
                    reservationCours.setDate_reservation(updateReservationCours.getDate_reservation());
                    reservationCours.setHeure_debut(updateReservationCours.getHeure_debut());
                    reservationCours.setHeure_fin(updateReservationCours.getHeure_fin());
                    reservationCours.setMembre(updateReservationCours.getMembre());
                    reservationCours.setStatut(updateReservationCours.getStatut());
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
            ReservationCours reservationCours = null;
            String message = "Erreur lors de la suppresion de la réservation, vérifez si l'identifiant de la réservation existe";
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                tx = session.beginTransaction();
                reservationCours = session.get(ReservationCours.class, id);
                if (reservationCours != null) {
                    session.delete(reservationCours);
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

        public List<ReservationCours> liste() {
            Session session = null;
            List<ReservationCours> reservationCours = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                reservationCours = session.createQuery("FROM ReservationCours").getResultList();
            } catch (PersistenceException e) {
                System.out.println("erreur : " + e.getMessage());
            } finally {
                if (session != null) {
                    session.close();
                }
            }
            return reservationCours;
        }
    }


