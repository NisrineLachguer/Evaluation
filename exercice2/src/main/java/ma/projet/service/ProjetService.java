package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class ProjetService implements IDao<Projet> {

    @Override
    public boolean create(Projet o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Projet o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Projet o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Projet findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Projet projet = session.get(Projet.class, id);
        session.close();
        return projet;
    }

    @Override
    public List<Projet> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Projet> query = session.createQuery("FROM Projet", Projet.class);
        List<Projet> projets = query.list();
        session.close();
        return projets;
    }

    // Afficher la liste des tâches planifiées pour un projet
    public List<Tache> getTachesPlanifieesPourProjet(int projetId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Tache> query = session.createQuery(
                "SELECT t FROM Tache t WHERE t.projet.id = :projetId",
                Tache.class
        );
        query.setParameter("projetId", projetId);
        List<Tache> taches = query.list();
        session.close();
        return taches;
    }

    // Afficher la liste des tâches réalisées avec les dates réelles
    public List<Tache> getTachesRealiseesAvecDatesReelles(int projetId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Tache> query = session.createQuery(
                "SELECT t FROM Tache t WHERE t.projet.id = :projetId AND t.dateDebutReelle IS NOT NULL",
                Tache.class
        );
        query.setParameter("projetId", projetId);
        List<Tache> taches = query.list();
        session.close();
        return taches;
    }

    // Méthode pour afficher selon le format demandé
    public void afficherTachesRealisees(int projetId) {
        Projet projet = findById(projetId);
        if (projet != null) {
            System.out.println("| Projet : " + projet.getId() + " | Nom : " + projet.getNom() +
                    " | Date début : " + projet.getDateDebut() + " |");
            System.out.println("| Liste des tâches: |");
            System.out.println("| Num Nom    | Date Début Réelle | Date Fin Réelle |");

            List<Tache> taches = getTachesRealiseesAvecDatesReelles(projetId);
            for (Tache tache : taches) {
                System.out.println("| " + tache.getId() + " " + tache.getNom() +
                        " | " + tache.getDateDebutReelle() + " | " +
                        tache.getDateFinReelle() + " |");
            }
        }
    }
}