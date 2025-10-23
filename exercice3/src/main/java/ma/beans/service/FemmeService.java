package ma.beans.service;

import ma.beans.classes.Femme;
import ma.beans.dao.IDao;
import ma.beans.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class FemmeService implements IDao<Femme> {

    public FemmeService() {}

    @Override
    public Femme save(Femme femme) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(femme);
            transaction.commit();
            return femme;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Femme> findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Femme femme = session.get(Femme.class, id);
            return Optional.ofNullable(femme);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Femme> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Femme> query = session.createQuery("FROM Femme", Femme.class);
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public Femme update(Femme femme) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Femme updated = (Femme) session.merge(femme);
            transaction.commit();
            return updated;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Femme femme = session.get(Femme.class, id);
            if (femme != null) {
                session.delete(femme);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    // Méthode HQL pour compter les enfants d'une femme entre deux dates
    public Integer compterEnfantsEntreDatesHQL(Long femmeId, LocalDate dateDebut, LocalDate dateFin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery(
                    "SELECT COALESCE(SUM(m.nombreEnfants), 0) " +
                            "FROM Mariage m " +
                            "WHERE m.femme.id = :femmeId " +
                            "AND m.dateDebut BETWEEN :dateDebut AND :dateFin");

            query.setParameter("femmeId", femmeId);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);

            return ((Number) query.uniqueResult()).intValue();
        } finally {
            session.close();
        }
    }

    // Méthode avec requête nommée pour les femmes mariées au moins deux fois
    public List<Femme> getFemmesMarieesAuMoinsDeuxFois() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Femme> query = session.createQuery(
                    "SELECT f FROM Femme f WHERE SIZE(f.mariagesFemme) >= 2", Femme.class);
            return query.list();
        } finally {
            session.close();
        }
    }

    // Méthode pour trouver la femme la plus âgée
    public Femme getFemmeLaPlusAgee() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Femme> query = session.createQuery(
                    "SELECT f FROM Femme f ORDER BY f.dateNaissance ASC", Femme.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } finally {
            session.close();
        }
    }

    // Méthode HQL pour compter les enfants d'une femme entre deux dates
    public Integer compterEnfantsEntreDatesNative(Long femmeId, LocalDate dateDebut, LocalDate dateFin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery(
                    "SELECT COALESCE(SUM(m.nombreEnfants), 0) " +
                            "FROM Mariage m " +
                            "WHERE m.femme.id = :femmeId " +
                            "AND m.dateDebut BETWEEN :dateDebut AND :dateFin");

            query.setParameter("femmeId", femmeId);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);

            return ((Number) query.uniqueResult()).intValue();
        } finally {
            session.close();
        }
    }
}