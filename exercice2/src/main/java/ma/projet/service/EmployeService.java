package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Employe;
import ma.projet.classes.Tache;
import ma.projet.classes.Projet;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class EmployeService implements IDao<Employe> {

    @Override
    public boolean create(Employe o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Employe o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Employe o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Employe findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Employe employe = session.get(Employe.class, id);
        session.close();
        return employe;
    }

    @Override
    public List<Employe> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Employe> query = session.createQuery("FROM Employe", Employe.class);
        List<Employe> employes = query.list();
        session.close();
        return employes;
    }

    // Afficher la liste des tâches réalisées par un employé
    public List<Tache> getTachesRealiseesParEmploye(int employeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Tache> query = session.createQuery(
                "SELECT et.tache FROM EmployeTache et WHERE et.employe.id = :employeId",
                Tache.class
        );
        query.setParameter("employeId", employeId);
        List<Tache> taches = query.list();
        session.close();
        return taches;
    }

    // Afficher la liste des projets gérés par un employé
    public List<Projet> getProjetsGeresParEmploye(int employeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Projet> query = session.createQuery(
                "SELECT p FROM Projet p WHERE p.chefProjet.id = :employeId",
                Projet.class
        );
        query.setParameter("employeId", employeId);
        List<Projet> projets = query.list();
        session.close();
        return projets;
    }
}