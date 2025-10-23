package ma.beans.service;

import ma.beans.classes.Mariage;
import ma.beans.dao.IDao;
import ma.beans.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MariageService implements IDao<Mariage> {

    public MariageService() {}

    @Override
    public Mariage save(Mariage mariage) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(mariage);
            transaction.commit();
            return mariage;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Mariage> findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Mariage mariage = session.get(Mariage.class, id);
            return Optional.ofNullable(mariage);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Mariage> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Mariage> query = session.createQuery("FROM Mariage", Mariage.class);
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public Mariage update(Mariage mariage) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Mariage updated = (Mariage) session.merge(mariage);
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
            Mariage mariage = session.get(Mariage.class, id);
            if (mariage != null) {
                session.delete(mariage);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    // Méthode pour compter les hommes mariés à 4 femmes entre deux dates
    public Long compterHommesMariesQuatreFemmes(LocalDate dateDebut, LocalDate dateFin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery(
                    "SELECT COUNT(DISTINCT m.homme) FROM Mariage m " +
                            "WHERE m.dateDebut BETWEEN :dateDebut AND :dateFin " +
                            "GROUP BY m.homme " +
                            "HAVING COUNT(m) >= 4");
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            return (Long) query.uniqueResult();
        } finally {
            session.close();
        }
    }
}