package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;

public class TacheService implements IDao<Tache> {

    @Override
    public boolean create(Tache o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Tache o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Tache o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Tache findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Tache tache = session.get(Tache.class, id);
        session.close();
        return tache;
    }

    @Override
    public List<Tache> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Tache> query = session.createQuery("FROM Tache", Tache.class);
        List<Tache> taches = query.list();
        session.close();
        return taches;
    }

    // Méthode pour afficher les tâches dont le prix est supérieur à 1000 DH (requête nommée)
    public List<Tache> getTachesPrixSuperieurA1000() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Tache> query = session.createNamedQuery("Tache.findByPrixGreaterThan", Tache.class);
        query.setParameter("prix", 1000.0);
        List<Tache> taches = query.list();
        session.close();
        return taches;
    }

    // Méthode pour afficher les tâches réalisées entre deux dates
    public List<Tache> getTachesRealiseesEntreDates(Date dateDebut, Date dateFin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Tache> query = session.createQuery(
                "SELECT t FROM Tache t WHERE t.dateDebutReelle BETWEEN :dateDebut AND :dateFin",
                Tache.class
        );
        query.setParameter("dateDebut", dateDebut);
        query.setParameter("dateFin", dateFin);
        List<Tache> taches = query.list();
        session.close();
        return taches;
    }
}